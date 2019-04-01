package ru.korchinskiy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korchinskiy.dao.*;
import ru.korchinskiy.dto.*;
import ru.korchinskiy.entity.*;
import ru.korchinskiy.enums.OrderStatus;
import ru.korchinskiy.enums.PaymentStatus;
import ru.korchinskiy.message.Message;
import ru.korchinskiy.service.CartService;
import ru.korchinskiy.service.DTOMappingService;
import ru.korchinskiy.service.OrderService;

import javax.servlet.http.HttpSession;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private ProductDAO productDAO;
    private OrderDAO orderDAO;
    private DTOMappingService dtoMappingService;
    private PaymentTypeDAO paymentTypeDAO;
    private DeliveryTypeDAO deliveryTypeDAO;
    private UserDAO userDAO;
    private OrderProductDAO orderProductDAO;
    private OrderHistoryDAO orderHistoryDAO;
    private ProductStatsDAO productStatsDAO;
    private UserStatsDAO userStatsDAO;
    private CartService cartService;

    @Override
    @Transactional
    public OrderDto getOrderById(Long id) {
        Order order = orderDAO.getOrderById(id);
        return dtoMappingService.convertToOrderDto(order);
    }

    @Override
    @Transactional
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderDAO.getAllOrders();
        return dtoMappingService.convertToOrderDtoList(orders);
    }

    @Override
    @Transactional
    public List<OrderDto> getOrdersByUser(UserDto userDto) {
        List<Order> orders = orderDAO.getOrdersByUserId(userDto.getId());
        return dtoMappingService.convertToOrderDtoList(orders);
    }

    @Override
    @Transactional
    public List<OrderProductDto> getOrderProductsByOrderId(Long id) {
        List<OrderProduct> orderProducts = orderProductDAO.getOrderProductsByOrderId(id);
        return dtoMappingService.convertToOrderProductDtoList(orderProducts);
    }

    @Override
    @Transactional
    public List<OrderHistoryDto> getOrderHistoriesByOrderId(Long id) {
        List<OrderHistory> orderHistories = orderHistoryDAO.getOrderHistoriesByOrderId(id);
        return dtoMappingService.convertToOrderHistoryDtoList(orderHistories);
    }

    @Override
    @Transactional
    public Message saveOrder(NewOrderDto orderDto, HttpSession session,
                             String cookieSession) {
        Message message = new Message();
//        get cart
        List<CartProductDto> cartProducts = cartService.getCartProductsBySessionId(cookieSession);
        if (cartProducts.size() == 0) {
            message.getErrors().add(Message.CART_IS_EMPTY);
            return message;
        }
//        reduce product amounts
        List<Product> products = new ArrayList<>();
        for (CartProductDto cartProductDto : cartProducts) {
            Product product = productDAO.getProductForUpdate(cartProductDto.getProduct().getId());
            if (cartProductDto.getAmount() > product.getAmount()) {
                message.getErrors().add(Message.PRODUCT_NOT_ENOUGH + " " + cartProductDto.getProduct().getTitle());
                return message;
            }
            product.setAmount(product.getAmount() - cartProductDto.getAmount());
            products.add(product);
        }
        Order order = createNewOrder(orderDto, session, cartProducts);
        orderDAO.saveOrder(order);
        saveOrderHistory(order);
        saveOrderProducts(cartProducts, products, order);
        cartService.cleanCart(cookieSession);
        message.getConfirms().add(Message.ORDER_SAVE_SUCCESS + " ID " + order.getId());
        return message;
    }

    private Order createNewOrder(NewOrderDto orderDto, HttpSession session, List<CartProductDto> cartProducts) {
        Order order = new Order();
        order.setUser(userDAO.getUserById(((UserDto) session.getAttribute("user")).getId()));
        order.setPaymentType(paymentTypeDAO.getPaymentTypeById(orderDto.getPaymentTypeId()));
        order.setDeliveryType(deliveryTypeDAO.getDeliveryTypeById(orderDto.getDeliveryTypeId()));
        order.setPaymentStatus(PaymentStatus.WAITING_FOR_PAYMENT);
        order.setOrderStatus(OrderStatus.NEW);
        order.setAddress((orderDto.getDeliveryTypeId() == 1L) ? orderDto.getAddress() : orderDto.getPickupAddress());
        order.setDate(Clock.systemUTC().instant());
        order.setSum(cartProducts.stream().map(CartProductDto::getSum).reduce(0.0, (acc, x) -> acc + x));
        return order;
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
        orderHistory.setDate(Clock.systemUTC().instant());
        orderHistory.setSum(order.getSum());
        orderHistoryDAO.saveOrderHistory(orderHistory);
    }

    @Override
    @Transactional
    public Message updateOrderStatus(Long orderId, OrderStatus orderStatus) {
        Order order = orderDAO.getOrderById(orderId);
        order.setOrderStatus(orderStatus);
        saveOrderHistory(order);
        if (orderStatus.equals(OrderStatus.DELIVERED)) {
            List<OrderProduct> orderProducts = orderProductDAO.getOrderProductsByOrderId(orderId);
            saveProductStats(orderProducts);
            saveUserStats(order);
        }
        Message message = new Message();
        message.getConfirms().add(Message.ORDER_STATUS_UPDATE_SUCCESS);
        return message;
    }

    private void saveUserStats(Order order) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        UserStats userStats = userStatsDAO.getUserStatsByUserIdAndDate(order.getUser().getId(), calendar);
        if (userStats == null) {
            userStats = new UserStats();
            userStats.setUser(order.getUser());
            userStats.setSum(order.getSum());
            userStats.setMonth(calendar.get(Calendar.MONTH) + 1);
            userStats.setYear(calendar.get(Calendar.YEAR));
            userStatsDAO.saveUserStats(userStats);
        } else {
            userStats.setSum(userStats.getSum() + order.getSum());
        }
    }

    private void saveProductStats(List<OrderProduct> orderProducts) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        for (OrderProduct orderProduct : orderProducts) {
            ProductStats productStats = productStatsDAO.getProductStatsByProductIdAndDate(orderProduct.getProduct().getId(), calendar);
            if (productStats == null) {
                productStats = new ProductStats();
                productStats.setProduct(orderProduct.getProduct());
                productStats.setAmount(orderProduct.getAmount());
                productStats.setMonth(calendar.get(Calendar.MONTH) + 1);
                productStats.setYear(calendar.get(Calendar.YEAR));
                productStatsDAO.saveProductStats(productStats);
            } else {
                productStats.setAmount(productStats.getAmount() + orderProduct.getAmount());
            }
        }
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
    public void setProductStatsDAO(ProductStatsDAO productStatsDAO) {
        this.productStatsDAO = productStatsDAO;
    }

    @Autowired
    public void setUserStatsDAO(UserStatsDAO userStatsDAO) {
        this.userStatsDAO = userStatsDAO;
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }


}
