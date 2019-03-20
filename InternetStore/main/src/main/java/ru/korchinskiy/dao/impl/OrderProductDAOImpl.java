package ru.korchinskiy.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.korchinskiy.dao.OrderProductDAO;
import ru.korchinskiy.entity.OrderProduct;

@Repository
public class OrderProductDAOImpl implements OrderProductDAO {
    private SessionFactory sessionFactory;

    @Override
    public Long saveOrderProduct(OrderProduct orderProduct) {
        return (Long) this.sessionFactory.getCurrentSession().save(orderProduct);
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
