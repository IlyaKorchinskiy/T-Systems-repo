package ru.korchinskiy.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.korchinskiy.dao.CartProductDAO;
import ru.korchinskiy.entity.CartProduct;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CartProductDAOImpl implements CartProductDAO {
    private SessionFactory sessionFactory;

    @Override
    public CartProduct getCartProductByCartIdAndProductId(Long cartId, Long productId) {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CartProduct> query = builder.createQuery(CartProduct.class);
        Root<CartProduct> root = query.from(CartProduct.class);
        query.select(root).where(builder.equal(root.get("cart").get("id"), cartId),
                builder.equal(root.get("product").get("id"), productId));
        List<CartProduct> cartProductList = session.createQuery(query).getResultList();
        if (cartProductList.size() == 0) return null;
        return cartProductList.get(0);
    }

    @Override
    public List<CartProduct> getCartProductListByCartId(Long cartId) {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CartProduct> query = builder.createQuery(CartProduct.class);
        Root<CartProduct> root = query.from(CartProduct.class);
        query.select(root).where(builder.equal(root.get("cart").get("id"), cartId));
        return session.createQuery(query).getResultList();
    }

    @Override
    public void saveCartProduct(CartProduct cartProduct) {
        this.sessionFactory.getCurrentSession().save(cartProduct);
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
