package ru.korchinskiy.dao.impl;

import org.springframework.stereotype.Repository;
import ru.korchinskiy.dao.UserDAO;
import ru.korchinskiy.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserById(Long id) {
        User user = this.entityManager.find(User.class, id);
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(builder.equal(root.get("email"), email));
        List<User> users = entityManager.createQuery(query).getResultList();
        if (users.size() == 0) return null;
        return users.get(0);
    }

    @Override
    public void saveUser(User user) {
        this.entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        this.entityManager.merge(user);
    }
}
