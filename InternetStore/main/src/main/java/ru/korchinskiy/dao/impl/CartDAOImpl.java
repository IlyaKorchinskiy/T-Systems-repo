package ru.korchinskiy.dao.impl;

import org.springframework.stereotype.Repository;
import ru.korchinskiy.dao.CartDAO;
import ru.korchinskiy.entity.Cart;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class CartDAOImpl implements CartDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Cart getCartById(Long id) {
        return this.entityManager.find(Cart.class, id);
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cart> query = builder.createQuery(Cart.class);
        Root<Cart> root = query.from(Cart.class);
        query.select(root).where(builder.equal(root.get("user").get("id"), userId));
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public void saveCart(Cart cart) {
        this.entityManager.persist(cart);
    }

}
