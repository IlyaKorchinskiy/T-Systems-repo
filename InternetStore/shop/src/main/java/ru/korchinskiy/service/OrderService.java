package ru.korchinskiy.service;

import ru.korchinskiy.dto.*;
import ru.korchinskiy.entity.*;
import ru.korchinskiy.enums.OrderStatus;
import ru.korchinskiy.message.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface OrderService {
    OrderDto getOrderById(Long id);

    List<OrderDto> getAllOrders();

    /**
     * Returns list of OrderDto by UserDto
     *
     * @param user UserDto current user
     * @return List<OrderDto> list of OrderDto
     */
    List<OrderDto> getOrdersByUser(UserDto user);

    /**
     * Returns list of OrderProductDto by order id
     *
     * @param id Long order id
     * @return List<OrderProductDto> list of OrderProductDto
     */
    List<OrderProductDto> getOrderProductsByOrderId(Long id);

    /**
     * Returns list of OrderHistoryDto by order id
     *
     * @param id Long order id
     * @return List<OrderHistoryDto> list of OrderHistoryDto
     */
    List<OrderHistoryDto> getOrderHistoriesByOrderId(Long id);

    Message saveOrder(NewOrderDto order, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException;

    Message updateOrderStatus(Long orderId, OrderStatus orderStatus);

    Order createNewOrder(NewOrderDto newOrderDto, User user);

    boolean checkForAmounts(List<CartProductDto> cartProducts, List<Product> products);

    OrderHistory createOrderHistory(Order order);

    OrderProduct createOrderProduct(CartProductDto cartProductDto, Product product, Order order);
}
