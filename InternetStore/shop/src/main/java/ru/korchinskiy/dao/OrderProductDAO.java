package ru.korchinskiy.dao;

import ru.korchinskiy.entity.OrderProduct;

import java.util.List;

public interface OrderProductDAO {
    List<OrderProduct> getOrderProductsByOrderId(Long id);

    void saveOrderProduct(OrderProduct orderProduct);
}
