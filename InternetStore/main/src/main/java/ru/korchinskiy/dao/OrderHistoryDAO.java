package ru.korchinskiy.dao;

import ru.korchinskiy.entity.OrderHistory;

public interface OrderHistoryDAO {
    void saveOrderHistory(OrderHistory orderHistory);
}
