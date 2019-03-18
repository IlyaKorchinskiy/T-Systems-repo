package ru.korchinskiy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korchinskiy.dao.OrderDAO;
import ru.korchinskiy.dao.UserDAO;
import ru.korchinskiy.dto.OrderDto;
import ru.korchinskiy.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO;
    private UserDAO userDAO;


    @Override
    @Transactional
    public Long saveOrder(OrderDto order, String cookie) {
        return null;
    }

    @Autowired
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }
}
