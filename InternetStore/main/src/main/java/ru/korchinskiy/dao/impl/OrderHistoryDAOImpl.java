package ru.korchinskiy.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.korchinskiy.dao.OrderHistoryDAO;
import ru.korchinskiy.entity.OrderHistory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrderHistoryDAOImpl implements OrderHistoryDAO {
    private SessionFactory sessionFactory;

    @Override
    public List<OrderHistory> getOrderHistoriesByOrderId(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<OrderHistory> query = builder.createQuery(OrderHistory.class);
        Root<OrderHistory> root = query.from(OrderHistory.class);
        query.select(root).where(builder.equal(root.get("order").get("id"), id));
        return session.createQuery(query).getResultList();
    }

    @Override
    public void saveOrderHistory(OrderHistory orderHistory) {
        this.sessionFactory.getCurrentSession().persist(orderHistory);
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
