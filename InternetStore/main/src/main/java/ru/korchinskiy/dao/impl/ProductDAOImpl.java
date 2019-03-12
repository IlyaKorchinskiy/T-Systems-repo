package ru.korchinskiy.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.korchinskiy.dao.ProductDAO;
import ru.korchinskiy.entity.Category;
import ru.korchinskiy.entity.Product;

import java.util.Set;

@Repository
public class ProductDAOImpl implements ProductDAO {
    private SessionFactory sessionFactory;

    @Override
    public Product getProductById(Long id) {
        return sessionFactory.getCurrentSession().get(Product.class, id);
    }

    @Override
    public Set<Product> getProductsByCategory(Long categoryId) {
        Category category = this.sessionFactory.getCurrentSession().get(Category.class, categoryId);
        return category.getProducts();
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
