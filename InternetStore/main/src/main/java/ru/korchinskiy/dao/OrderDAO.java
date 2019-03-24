package ru.korchinskiy.dao;

import ru.korchinskiy.entity.Order;

import java.util.List;

public interface OrderDAO {
    List<Order> getAllOrders();

    List<Order> getOrderByUserId(Long id);

    void saveOrder(Order order);
}
