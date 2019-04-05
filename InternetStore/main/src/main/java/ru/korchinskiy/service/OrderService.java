package ru.korchinskiy.service;

import ru.korchinskiy.dto.*;
import ru.korchinskiy.enums.OrderStatus;
import ru.korchinskiy.message.Message;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface OrderService {
    OrderDto getOrderById(Long id);

    List<OrderDto> getAllOrders();

    List<OrderDto> getOrdersByUser(UserDto user);

    List<OrderProductDto> getOrderProductsByOrderId(Long id);

    List<OrderHistoryDto> getOrderHistoriesByOrderId(Long id);

    Message saveOrder(NewOrderDto order, HttpSession session, String cookieSession);

    Message updateOrderStatus(Long orderId, OrderStatus orderStatus);
}
