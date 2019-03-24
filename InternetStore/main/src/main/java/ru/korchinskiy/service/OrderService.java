package ru.korchinskiy.service;

import ru.korchinskiy.dto.NewOrderDto;
import ru.korchinskiy.dto.OrderDto;
import ru.korchinskiy.dto.UserDto;
import ru.korchinskiy.message.Message;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders();

    List<OrderDto> getOrdersByUser(UserDto user);

    Message saveOrder(NewOrderDto order, HttpSession session, String cookieSession);
}
