package ru.korchinskiy.service;

import ru.korchinskiy.dto.OrderDto;
import ru.korchinskiy.message.Message;

import javax.servlet.http.HttpSession;

public interface OrderService {
    Message saveOrder(OrderDto order, HttpSession session, String cookieSession);
}
