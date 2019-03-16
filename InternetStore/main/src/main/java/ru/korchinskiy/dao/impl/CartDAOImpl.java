package ru.korchinskiy.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.korchinskiy.dao.CartDAO;
import ru.korchinskiy.entity.Cart;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class CartDAOImpl implements CartDAO {
    private SessionFactory sessionFactory;

    @Override
    public Cart getCartById(Long id) {
        return this.sessionFactory.getCurrentSession().get(Cart.class, id);
    }

    @Override
    public Cart getCartBySessionId(String sessionId) {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Cart> criteria = builder.createQuery(Cart.class);
        Root<Cart> root = criteria.from(Cart.class);
        criteria.select(root).where(builder.equal(root.get("sessionId"), sessionId));
        Query<Cart> query = session.createQuery(criteria);
        return query.getSingleResult();
    }

    @Override
    public void saveCart(Cart cart) {
        this.sessionFactory.getCurrentSession().persist(cart);
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
