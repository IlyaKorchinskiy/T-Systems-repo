package ru.korchinskiy.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.korchinskiy.dao.PaymentStatusDAO;
import ru.korchinskiy.entity.PaymentStatus;

@Repository
public class PaymentStatusDAOImpl implements PaymentStatusDAO {
    private SessionFactory sessionFactory;

    @Override
    public PaymentStatus getPaymentStatusById(Long id) {
        return this.sessionFactory.getCurrentSession().get(PaymentStatus.class, id);
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
