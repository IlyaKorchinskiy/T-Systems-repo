package ru.korchinskiy.service;

import ru.korchinskiy.dto.*;
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

    /**
     * Checks for amounts of cartProducts. If amounts are ok creates new order, returns object Message confirm.
     * If amounts are not ok or cart is empty, returns object Message error.
     *
     * @param order    NewOrderDto from UI
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return Message message with confirm or errors lists
     * @throws UnsupportedEncodingException
     */
    Message saveOrder(NewOrderDto order, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException;

    /**
     * Updates order status and returns Message object.
     *
     * @param orderId     Long order id
     * @param orderStatus OrderStatus
     * @return Message message with confirm or errors list
     */
    Message updateOrderStatus(Long orderId, OrderStatus orderStatus);

}
