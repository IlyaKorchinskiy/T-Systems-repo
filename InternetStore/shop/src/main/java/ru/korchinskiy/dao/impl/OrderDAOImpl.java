package ru.korchinskiy.dao.impl;

import org.springframework.stereotype.Repository;
import ru.korchinskiy.dao.OrderDAO;
import ru.korchinskiy.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Order getOrderById(Long id) {
        return this.entityManager.find(Order.class, id);
    }

    @Override
    public List<Order> getAllOrders() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Order> getOrdersByUserId(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);
        query.select(root).where(builder.equal(root.get("user").get("id"), id));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void saveOrder(Order order) {
        this.entityManager.persist(order);
    }


}
