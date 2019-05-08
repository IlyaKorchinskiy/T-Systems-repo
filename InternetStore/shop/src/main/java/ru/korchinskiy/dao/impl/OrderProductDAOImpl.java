package ru.korchinskiy.dao.impl;

import org.springframework.stereotype.Repository;
import ru.korchinskiy.dao.OrderProductDAO;
import ru.korchinskiy.entity.OrderProduct;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrderProductDAOImpl implements OrderProductDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<OrderProduct> getOrderProductsByOrderId(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrderProduct> query = builder.createQuery(OrderProduct.class);
        Root<OrderProduct> root = query.from(OrderProduct.class);
        query.select(root).where(builder.equal(root.get("order").get("id"), id));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void saveOrderProduct(OrderProduct orderProduct) {
        this.entityManager.persist(orderProduct);
    }

}
