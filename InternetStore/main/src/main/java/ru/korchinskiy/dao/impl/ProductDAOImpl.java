package ru.korchinskiy.dao.impl;

import org.springframework.stereotype.Repository;
import ru.korchinskiy.dao.ProductDAO;
import ru.korchinskiy.entity.Category;
import ru.korchinskiy.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Product getProductById(Long id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public Product getProductByTitle(String title) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        query.select(root).where(builder.equal(root.get("title"), title));
        List<Product> products = entityManager.createQuery(query).getResultList();
        if (products.size() == 0) return null;
        return products.get(0);
    }

    @Override
    public Product getProductForUpdate(Long id) {
        return entityManager.find(Product.class, id, LockModeType.PESSIMISTIC_WRITE);
    }

    @Override
    public List<Product> getAllProducts() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Product> getProductsByCategory(Long categoryId) {
        Category category = this.entityManager.find(Category.class, categoryId);
        return category.getProducts();
    }

    @Override
    public List<Product> getProductsByCategoryAndCost(Long categoryId, Double minCost, Double maxCost) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> product = query.from(Product.class);
        ListJoin<Product, Category> categories = product.joinList("categories");
        query.select(product)
                .where(builder.equal(categories.get("id"), categoryId),
                        builder.greaterThanOrEqualTo(product.get("cost"), minCost),
                        builder.lessThanOrEqualTo(product.get("cost"), maxCost));
        TypedQuery<Product> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public List<Product> findProductsBySearch(String search) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        query.select(root).where(builder.or(
                builder.like(root.get("title"), '%' + search + '%'),
                builder.like(root.get("author"), '%' + search + '%')));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void saveProduct(Product product) {
        this.entityManager.persist(product);
    }

}
