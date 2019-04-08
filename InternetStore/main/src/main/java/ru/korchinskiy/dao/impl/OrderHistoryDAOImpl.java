package ru.korchinskiy.dao.impl;

import org.springframework.stereotype.Repository;
import ru.korchinskiy.dao.OrderHistoryDAO;
import ru.korchinskiy.entity.OrderHistory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrderHistoryDAOImpl implements OrderHistoryDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<OrderHistory> getOrderHistoriesByOrderId(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrderHistory> query = builder.createQuery(OrderHistory.class);
        Root<OrderHistory> root = query.from(OrderHistory.class);
        query.select(root).where(builder.equal(root.get("order").get("id"), id));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void saveOrderHistory(OrderHistory orderHistory) {
        this.entityManager.persist(orderHistory);
    }

}
