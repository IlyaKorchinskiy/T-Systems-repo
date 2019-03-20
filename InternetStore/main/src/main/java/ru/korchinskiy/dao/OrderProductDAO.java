package ru.korchinskiy.dao;

import ru.korchinskiy.entity.OrderProduct;

public interface OrderProductDAO {
    Long saveOrderProduct(OrderProduct orderProduct);
}
