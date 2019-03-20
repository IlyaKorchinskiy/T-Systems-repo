package ru.korchinskiy.dao;

import ru.korchinskiy.entity.OrderStatus;

public interface OrderStatusDAO {
    OrderStatus getOrderStatusById(Long id);
}
