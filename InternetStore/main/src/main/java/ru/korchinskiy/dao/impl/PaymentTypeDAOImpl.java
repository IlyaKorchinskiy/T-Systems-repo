package ru.korchinskiy.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.korchinskiy.dao.PaymentTypeDAO;
import ru.korchinskiy.entity.PaymentType;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class PaymentTypeDAOImpl implements PaymentTypeDAO {
    private SessionFactory sessionFactory;

    @Override
    public List<PaymentType> getAllPaymentTypes() {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PaymentType> query = builder.createQuery(PaymentType.class);
        Root<PaymentType> root = query.from(PaymentType.class);
        query.select(root);
        return session.createQuery(query).getResultList();
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
