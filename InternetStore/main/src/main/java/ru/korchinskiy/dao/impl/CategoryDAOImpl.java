package ru.korchinskiy.dao.impl;

import org.springframework.stereotype.Repository;
import ru.korchinskiy.dao.CategoryDAO;
import ru.korchinskiy.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Category getCategoryById(Long id) {
        return this.entityManager.find(Category.class, id);
    }

    @Override
    public Category getCategoryByTitle(String title) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> query = builder.createQuery(Category.class);
        Root<Category> root = query.from(Category.class);
        query.select(root).where(builder.equal(root.get("title"), title));
        List<Category> categories = entityManager.createQuery(query).getResultList();
        if (categories.size() == 0) return null;
        return categories.get(0);
    }

    @Override
    public List<Category> getCategoriesByParentId(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> query = builder.createQuery(Category.class);
        Root<Category> root = query.from(Category.class);
        query.select(root).where(builder.equal(root.get("parentId"), id));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Category> getAllCategories() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> query = builder.createQuery(Category.class);
        Root<Category> root = query.from(Category.class);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void saveCategory(Category category) {
        this.entityManager.persist(category);
    }

    @Override
    public void removeCategory(Category category) {
        this.entityManager.remove(category);
    }

}
