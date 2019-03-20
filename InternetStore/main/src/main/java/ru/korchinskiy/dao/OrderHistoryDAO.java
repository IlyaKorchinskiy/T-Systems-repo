package ru.korchinskiy.dao;

import ru.korchinskiy.entity.OrderHistory;

public interface OrderHistoryDAO {
    Long saveOrderHistory(OrderHistory orderHistory);
}
