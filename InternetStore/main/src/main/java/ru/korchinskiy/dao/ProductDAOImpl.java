package ru.korchinskiy.dao;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.korchinskiy.entity.Category;
import ru.korchinskiy.entity.Product;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ProductDAOImpl implements ProductDAO {
    private SessionFactory sessionFactory;

    @Override
    public Product getProductById(Long id) {
        return sessionFactory.getCurrentSession().get(Product.class, id);
    }

    @Override
    @Transactional
    public Set<Product> getProductsByCategory(Long categoryId) {
        Category category = this.sessionFactory.getCurrentSession().get(Category.class, categoryId);
        Hibernate.initialize(category.getProducts());
        return category.getProducts();
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
