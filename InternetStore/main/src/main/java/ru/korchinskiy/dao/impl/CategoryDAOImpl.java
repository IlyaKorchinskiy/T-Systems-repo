package ru.korchinskiy.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
    public Category getCategoryByTitle(String title) {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Category> query = builder.createQuery(Category.class);
        Root<Category> root = query.from(Category.class);
        query.select(root).where(builder.equal(root.get("title"), title));
        List<Category> categories = session.createQuery(query).getResultList();
        if (categories.size() == 0) return null;
        return categories.get(0);
    }

    @Override
    public List<Category> getCategoriesByParentId(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Category> query = builder.createQuery(Category.class);
        Root<Category> root = query.from(Category.class);
        query.select(root).where(builder.equal(root.get("parentId"), id));
        return session.createQuery(query).getResultList();
    }

    @Override
    public List<Category> getAllCategories() {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Category> query = builder.createQuery(Category.class);
        Root<Category> root = query.from(Category.class);
        query.select(root);
        return session.createQuery(query).getResultList();
    }

    @Override
    public void saveCategory(Category category) {
        this.sessionFactory.getCurrentSession().persist(category);
    }

    @Override
    public void removeCategory(Category category) {
        this.sessionFactory.getCurrentSession().delete(category);
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
