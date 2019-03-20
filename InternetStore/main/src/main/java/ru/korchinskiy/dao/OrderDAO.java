package ru.korchinskiy.dao;

import ru.korchinskiy.entity.Order;

public interface OrderDAO {
    void saveOrder(Order order);
}
