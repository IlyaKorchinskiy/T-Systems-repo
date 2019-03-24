package ru.korchinskiy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korchinskiy.dao.*;
import ru.korchinskiy.dto.CartProductDto;
import ru.korchinskiy.dto.NewOrderDto;
import ru.korchinskiy.dto.OrderDto;
import ru.korchinskiy.dto.UserDto;
import ru.korchinskiy.entity.Order;
import ru.korchinskiy.entity.OrderHistory;
import ru.korchinskiy.entity.OrderProduct;
import ru.korchinskiy.entity.Product;
import ru.korchinskiy.message.Message;
import ru.korchinskiy.service.CartService;
import ru.korchinskiy.service.DTOMappingService;
import ru.korchinskiy.service.OrderService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private static final String NOT_ENOUGH_PRODUCT = "Недостаточное количество товара";
    private static final Long STATUS_NOT_PAID = 1L;
    private static final Long ORDER_NEW = 1L;
    private static final String ORDER_SAVE_SUCCESS = "Заказ успешно сохранен";
    private static final String CART_IS_EMPTY = "Корзина пуста";

    private ProductDAO productDAO;
    private OrderDAO orderDAO;
    private DTOMappingService dtoMappingService;
    private PaymentTypeDAO paymentTypeDAO;
    private DeliveryTypeDAO deliveryTypeDAO;
    private PaymentStatusDAO paymentStatusDAO;
    private OrderStatusDAO orderStatusDAO;
    private UserDAO userDAO;
    private OrderProductDAO orderProductDAO;
    private OrderHistoryDAO orderHistoryDAO;

    private CartService cartService;

    @Override
    @Transactional
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderDAO.getAllOrders();
        return dtoMappingService.convertToOrderDtoList(orders);
    }

    @Override
    @Transactional
    public List<OrderDto> getOrdersByUser(UserDto userDto) {
        List<Order> orders = orderDAO.getOrderByUserId(userDto.getId());
        return dtoMappingService.convertToOrderDtoList(orders);
    }

    @Override
    @Transactional
    public Message saveOrder(NewOrderDto orderDto, HttpSession session,
                             String cookieSession) {
        Message message = new Message();
//        get cart
        List<CartProductDto> cartProducts = cartService.getCartProductsBySessionId(cookieSession);
        if (cartProducts.size() == 0) {
            message.getErrors().add(CART_IS_EMPTY);
            return message;
        }
//        get products for update
        List<Product> products = new ArrayList<>();
        for (CartProductDto cartProductDto : cartProducts) {
            Product product = productDAO.getProductForUpdate(cartProductDto.getProduct().getId());
            products.add(product);
        }
        if (checkForAmounts(message, cartProducts, products)) return message;
//        reduce product amounts
        for (int i = 0; i < products.size(); i++) {
            int productAmount = products.get(i).getAmount();
            products.get(i).setAmount(productAmount - cartProducts.get(i).getAmount());
        }
//        new order
        Order order = new Order();
        order.setUser(userDAO.getUserById(((UserDto) session.getAttribute("user")).getId()));
        order.setPaymentType(paymentTypeDAO.getPaymentTypeById(orderDto.getPaymentTypeId()));
        order.setDeliveryType(deliveryTypeDAO.getDeliveryTypeById(orderDto.getDeliveryTypeId()));
        order.setPaymentStatus(paymentStatusDAO.getPaymentStatusById(STATUS_NOT_PAID));
        order.setOrderStatus(orderStatusDAO.getOrderStatusById(ORDER_NEW));
        order.setAddress((orderDto.getDeliveryTypeId() == 1L) ? orderDto.getAddress() : orderDto.getPickupAddress());
        order.setDate(new Date());
        order.setSum(cartProducts.stream().map(CartProductDto::getSum).reduce(0.0, (acc, x) -> acc + x));
        orderDAO.saveOrder(order);
        saveOrderHistory(order);
        saveOrderProducts(cartProducts, products, order);
        cartService.cleanCart(cookieSession);
        message.getConfirms().add(ORDER_SAVE_SUCCESS + " ID " + order.getId());
        return message;
    }

    private void saveOrderProducts(List<CartProductDto> cartProducts, List<Product> products, Order order) {
        for (int i = 0; i < products.size(); i++) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setProduct(products.get(i));
            orderProduct.setCost(cartProducts.get(i).getProduct().getCost());
            orderProduct.setAmount(cartProducts.get(i).getAmount());
            orderProductDAO.saveOrderProduct(orderProduct);
        }
    }

    private void saveOrderHistory(Order order) {
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setOrder(order);
        orderHistory.setPaymentType(order.getPaymentType());
        orderHistory.setDeliveryType(order.getDeliveryType());
        orderHistory.setPaymentStatus(order.getPaymentStatus());
        orderHistory.setOrderStatus(order.getOrderStatus());
        orderHistory.setAddress(order.getAddress());
        orderHistory.setDate(order.getDate());
        orderHistory.setSum(order.getSum());
        orderHistoryDAO.saveOrderHistory(orderHistory);
    }

    private boolean checkForAmounts(Message message, List<CartProductDto> cartProducts, List<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            if (cartProducts.get(i).getAmount() > products.get(i).getAmount()) {
                message.getErrors().add(NOT_ENOUGH_PRODUCT + " " + cartProducts.get(i).getProduct().getTitle());
                return true;
            }
        }
        return false;
    }

    @Autowired
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Autowired
    public void setDtoMappingService(DTOMappingService dtoMappingService) {
        this.dtoMappingService = dtoMappingService;
    }

    @Autowired
    public void setPaymentTypeDAO(PaymentTypeDAO paymentTypeDAO) {
        this.paymentTypeDAO = paymentTypeDAO;
    }

    @Autowired
    public void setDeliveryTypeDAO(DeliveryTypeDAO deliveryTypeDAO) {
        this.deliveryTypeDAO = deliveryTypeDAO;
    }

    @Autowired
    public void setPaymentStatusDAO(PaymentStatusDAO paymentStatusDAO) {
        this.paymentStatusDAO = paymentStatusDAO;
    }

    @Autowired
    public void setOrderStatusDAO(OrderStatusDAO orderStatusDAO) {
        this.orderStatusDAO = orderStatusDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setOrderProductDAO(OrderProductDAO orderProductDAO) {
        this.orderProductDAO = orderProductDAO;
    }

    @Autowired
    public void setOrderHistoryDAO(OrderHistoryDAO orderHistoryDAO) {
        this.orderHistoryDAO = orderHistoryDAO;
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }


}
