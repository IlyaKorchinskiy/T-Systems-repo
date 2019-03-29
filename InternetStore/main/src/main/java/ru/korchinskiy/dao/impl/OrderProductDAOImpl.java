package ru.korchinskiy.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.korchinskiy.dao.OrderProductDAO;
import ru.korchinskiy.entity.OrderProduct;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrderProductDAOImpl implements OrderProductDAO {
    private SessionFactory sessionFactory;

    @Override
    public List<OrderProduct> getOrderProductsByOrderId(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<OrderProduct> query = builder.createQuery(OrderProduct.class);
        Root<OrderProduct> root = query.from(OrderProduct.class);
        query.select(root).where(builder.equal(root.get("order").get("id"), id));
        return session.createQuery(query).getResultList();
    }

    @Override
    public void saveOrderProduct(OrderProduct orderProduct) {
        this.sessionFactory.getCurrentSession().persist(orderProduct);
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
