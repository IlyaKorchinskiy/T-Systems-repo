package ru.korchinskiy.service;

import ru.korchinskiy.enums.OrderStatus;
import ru.korchinskiy.message.Message;

public interface AdminOrderService {
    Message updateOrderStatus(Long orderId, OrderStatus orderStatus);
}
