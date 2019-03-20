package ru.korchinskiy.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.korchinskiy.dao.OrderDAO;
import ru.korchinskiy.entity.Order;

@Repository
public class OrderDAOImpl implements OrderDAO {
    private SessionFactory sessionFactory;

    @Override
    public void saveOrder(Order order) {
        this.sessionFactory.getCurrentSession().persist(order);
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
