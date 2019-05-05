package ru.korchinskiy.service.impl;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.WebUtils;
import ru.korchinskiy.dao.*;
import ru.korchinskiy.dto.*;
import ru.korchinskiy.entity.*;
import ru.korchinskiy.enums.DeliveryType;
import ru.korchinskiy.enums.OrderStatus;
import ru.korchinskiy.enums.PaymentStatus;
import ru.korchinskiy.message.Message;
import ru.korchinskiy.service.CartService;
import ru.korchinskiy.service.DTOMappingService;
import ru.korchinskiy.service.OrderService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private static Logger logger = Logger.getLogger(OrderServiceImpl.class);

    private ProductDAO productDAO;
    private OrderDAO orderDAO;
    private DTOMappingService dtoMappingService;
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
    public Message saveOrder(NewOrderDto orderDto, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Message message = new Message();
        Gson gson = new Gson();
        Cookie cookieCart = WebUtils.getCookie(request, "cart");
        CartDto cartDto = gson.fromJson(URLDecoder.decode(cookieCart.getValue(), "UTF-8"), CartDto.class);
        List<CartProductDto> cartProducts = cartDto.getCartProducts();
        if (cartProducts.size() == 0) {
            message.getErrors().add(Message.CART_IS_EMPTY);
            logger.info(Message.CART_IS_EMPTY);
            return message;
        }
        List<Product> products = new ArrayList<>();
        for (CartProductDto cartProductDto : cartProducts) {
            Product product = productDAO.getProductForUpdate(cartProductDto.getProduct().getId());
            products.add(product);
        }
        if (!checkForAmounts(cartProducts, products)) {
            message.getErrors().add(Message.PRODUCT_NOT_ENOUGH);
            logger.info(Message.PRODUCT_NOT_ENOUGH);
            return message;
        }
        reduceProductAmounts(cartProducts, products);
        User user = userDAO.getUserById(((UserDto) request.getSession().getAttribute("user")).getId());
        Order order = createNewOrder(orderDto, user);
        orderDAO.saveOrder(order);
        OrderHistory orderHistory = createOrderHistory(order);
        orderHistoryDAO.saveOrderHistory(orderHistory);
        for (int i = 0; i < cartProducts.size(); i++) {
            OrderProduct orderProduct = createOrderProduct(cartProducts.get(i), products.get(i), order);
            orderProductDAO.saveOrderProduct(orderProduct);
        }
        cartService.cleanCarts(request, response, user.getId());
        message.getConfirms().add(Message.ORDER_SAVE_SUCCESS + " ID " + order.getId());
        logger.info(Message.ORDER_SAVE_SUCCESS);
        return message;
    }

    private void reduceProductAmounts(List<CartProductDto> cartProducts, List<Product> products) {
        for (int i = 0; i < cartProducts.size(); i++) {
            products.get(i).setAmount(products.get(i).getAmount() - cartProducts.get(i).getAmount());
        }
    }

    public boolean checkForAmounts(List<CartProductDto> cartProducts, List<Product> products) {
        for (int i = 0; i < cartProducts.size(); i++) {
            if (cartProducts.get(i).getAmount() > products.get(i).getAmount()) return false;
        }
        return true;
    }

    public Order createNewOrder(NewOrderDto orderDto, User user) {
        Order order = new Order();
        order.setUser(user);
        order.setPaymentType(orderDto.getPaymentType());
        order.setDeliveryType(orderDto.getDeliveryType());
        order.setPaymentStatus(PaymentStatus.WAITING_FOR_PAYMENT);
        order.setOrderStatus(OrderStatus.NEW);
        order.setAddress((orderDto.getDeliveryType() == DeliveryType.DELIVERY) ? orderDto.getAddress() : orderDto.getPickupAddress());
        order.setDate(Clock.systemUTC().instant());
        order.setSum(orderDto.getSum());
        return order;
    }

    public OrderProduct createOrderProduct(CartProductDto cartProduct, Product product, Order order) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrder(order);
        orderProduct.setProduct(product);
        orderProduct.setCost(cartProduct.getProduct().getCost());
        orderProduct.setAmount(cartProduct.getAmount());
        return orderProduct;
    }

    public OrderHistory createOrderHistory(Order order) {
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setOrder(order);
        orderHistory.setPaymentType(order.getPaymentType());
        orderHistory.setDeliveryType(order.getDeliveryType());
        orderHistory.setPaymentStatus(order.getPaymentStatus());
        orderHistory.setOrderStatus(order.getOrderStatus());
        orderHistory.setAddress(order.getAddress());
        orderHistory.setDate(Clock.systemUTC().instant());
        orderHistory.setSum(order.getSum());
        return orderHistory;
    }

    @Override
    @Transactional
    public Message updateOrderStatus(Long orderId, OrderStatus orderStatus) {
        Message message = new Message();
        Order order = orderDAO.getOrderById(orderId);
        if (order.getOrderStatus().equals(orderStatus)) {
            message.getErrors().add(Message.ORDER_STATUS_UPDATE_FAIL);
            logger.info(Message.ORDER_STATUS_UPDATE_FAIL);
            return message;
        }
        order.setOrderStatus(orderStatus);
        OrderHistory orderHistory = createOrderHistory(order);
        orderHistoryDAO.saveOrderHistory(orderHistory);
        if (orderStatus.equals(OrderStatus.DELIVERED)) {
            List<OrderProduct> orderProducts = orderProductDAO.getOrderProductsByOrderId(orderId);
            saveProductStats(orderProducts);
            saveUserStats(order);
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        message.getConfirms().add(Message.ORDER_STATUS_UPDATE_SUCCESS);
        logger.info(Message.ORDER_STATUS_UPDATE_SUCCESS);
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
