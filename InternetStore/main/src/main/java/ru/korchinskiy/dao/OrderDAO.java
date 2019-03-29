package ru.korchinskiy.dao;

import ru.korchinskiy.entity.Order;

import java.util.List;

public interface OrderDAO {
    Order getOrderById(Long id);

    List<Order> getAllOrders();

    List<Order> getOrdersByUserId(Long id);

    void saveOrder(Order order);
}
