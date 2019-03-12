package ru.korchinskiy.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.korchinskiy.dao.CategoryDAO;
import ru.korchinskiy.entity.Category;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
    private SessionFactory sessionFactory;

    @Override
    public Category getCategoryById(Long id) {
        return this.sessionFactory.getCurrentSession().get(Category.class, id);
    }

    @Override
    public Category getCategoryWithProductsById(Long id) {
        Category category = this.sessionFactory.getCurrentSession().get(Category.class, id);
        return category;
    }

    @Override
    public List<Category> getCategoriesByParentId(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Category> criteria = builder.createQuery(Category.class);
        Root<Category> root = criteria.from(Category.class);
        criteria.select(root).where(builder.equal(root.get("parentId"), id));
        Query<Category> query = session.createQuery(criteria);
        return query.getResultList();
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
