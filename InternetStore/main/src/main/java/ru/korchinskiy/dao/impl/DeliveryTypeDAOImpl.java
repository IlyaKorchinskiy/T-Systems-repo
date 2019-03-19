package ru.korchinskiy.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.korchinskiy.dao.DeliveryTypeDAO;
import ru.korchinskiy.entity.DeliveryType;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class DeliveryTypeDAOImpl implements DeliveryTypeDAO {
    private SessionFactory sessionFactory;

    @Override
    public DeliveryType getDeliveryTypeById(Long id) {
        return this.sessionFactory.getCurrentSession().get(DeliveryType.class, id);
    }

    @Override
    public List<DeliveryType> getAllDeliveryTypes() {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DeliveryType> query = builder.createQuery(DeliveryType.class);
        Root<DeliveryType> root = query.from(DeliveryType.class);
        query.select(root);
        return session.createQuery(query).getResultList();
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
