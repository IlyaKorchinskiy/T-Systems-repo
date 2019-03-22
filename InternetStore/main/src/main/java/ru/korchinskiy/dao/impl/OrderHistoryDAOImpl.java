package ru.korchinskiy.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.korchinskiy.dao.OrderHistoryDAO;
import ru.korchinskiy.entity.OrderHistory;

@Repository
public class OrderHistoryDAOImpl implements OrderHistoryDAO {
    private SessionFactory sessionFactory;

    @Override
    public void saveOrderHistory(OrderHistory orderHistory) {
        this.sessionFactory.getCurrentSession().persist(orderHistory);
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
