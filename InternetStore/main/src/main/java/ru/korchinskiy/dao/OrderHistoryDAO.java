package ru.korchinskiy.dao;

import ru.korchinskiy.entity.OrderHistory;

import java.util.List;

public interface OrderHistoryDAO {
    List<OrderHistory> getOrderHistoriesByOrderId(Long id);

    void saveOrderHistory(OrderHistory orderHistory);
}
