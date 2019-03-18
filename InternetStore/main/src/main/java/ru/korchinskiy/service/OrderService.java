package ru.korchinskiy.service;

import ru.korchinskiy.dto.OrderDto;

public interface OrderService {
    Long saveOrder(OrderDto order, String Cookie);
}
