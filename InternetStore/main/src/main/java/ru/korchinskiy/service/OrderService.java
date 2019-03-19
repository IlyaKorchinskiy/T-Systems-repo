package ru.korchinskiy.service;

import ru.korchinskiy.dto.CartProductDto;
import ru.korchinskiy.dto.OrderDto;
import ru.korchinskiy.message.Message;

import java.util.List;
import java.util.Set;

public interface OrderService {
    Message saveOrder(OrderDto order, List<CartProductDto> cartProducts, String SessionId);
}
