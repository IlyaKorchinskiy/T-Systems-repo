package ru.korchinskiy.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.korchinskiy.dao.OrderStatusDAO;
import ru.korchinskiy.entity.OrderStatus;

@Repository
public class OrderStatusDAOImpl implements OrderStatusDAO {
    private SessionFactory sessionFactory;

    @Override
    public OrderStatus getOrderStatusById(Long id) {
        return this.sessionFactory.getCurrentSession().get(OrderStatus.class, id);
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
