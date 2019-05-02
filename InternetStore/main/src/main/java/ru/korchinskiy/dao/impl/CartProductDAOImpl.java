package ru.korchinskiy.dao.impl;

import org.springframework.stereotype.Repository;
import ru.korchinskiy.dao.CartProductDAO;
import ru.korchinskiy.entity.CartProduct;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CartProductDAOImpl implements CartProductDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CartProduct getCartProductById(Long id) {
        return this.entityManager.find(CartProduct.class, id);
    }

    @Override
    public CartProduct getCartProductByCartIdAndProductId(Long cartId, Long productId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CartProduct> query = builder.createQuery(CartProduct.class);
        Root<CartProduct> root = query.from(CartProduct.class);
        query.select(root).where(builder.equal(root.get("cart").get("id"), cartId),
                builder.equal(root.get("product").get("id"), productId));
        List<CartProduct> cartProductList = entityManager.createQuery(query).getResultList();
        if (cartProductList.size() == 0) return null;
        return cartProductList.get(0);
    }

    @Override
    public List<CartProduct> getCartProductListByCartId(Long cartId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CartProduct> query = builder.createQuery(CartProduct.class);
        Root<CartProduct> root = query.from(CartProduct.class);
        query.select(root).where(builder.equal(root.get("cart").get("id"), cartId));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void saveCartProduct(CartProduct cartProduct) {
        this.entityManager.persist(cartProduct);
    }

    @Override
    public void removeCartProductById(Long id) {
        CartProduct cartProduct = getCartProductById(id);
        this.entityManager.remove(cartProduct);
    }

    @Override
    public void removerCartProductByCartIdAndProductId(Long cartId, Long productId) {
        CartProduct cartProduct = getCartProductByCartIdAndProductId(cartId, productId);
        this.entityManager.remove(cartProduct);
    }

}
