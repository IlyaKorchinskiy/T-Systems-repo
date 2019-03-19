package ru.korchinskiy.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.korchinskiy.dao.UserDAO;
import ru.korchinskiy.entity.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    private SessionFactory sessionFactory;

    @Override
    public User getUserById(Long id) {
        return this.sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public User getUserByEmail(String email) {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(builder.equal(root.get("email"), email));
        List<User> users = session.createQuery(query).getResultList();
        if (users.size() == 0) return null;
        return users.get(0);
    }

    @Override
    public void saveUser(User user) {
        this.sessionFactory.getCurrentSession().persist(user);
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
