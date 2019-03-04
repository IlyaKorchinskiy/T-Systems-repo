package ru.korchinskiy.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.korchinskiy.entity.Category;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Category getCategoryById(Long id) {
        Category category = this.sessionFactory.getCurrentSession().get(Category.class, id);
        Hibernate.initialize(category.getProducts());
        return category;
    }

    @Override
    public List<Category> getMainCategories() {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Category> criteria = builder.createQuery(Category.class);
        Root<Category> root = criteria.from(Category.class);
        criteria.select(root).where(builder.equal(root.get("parent_id"), null));
        Query<Category> query = session.createQuery(criteria);
        return query.getResultList();
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
