package ru.korchinskiy.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.korchinskiy.entity.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class UserDAOImpl implements UserDAO {
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public User getUserById(Long id) {
        User user = this.sessionFactory.getCurrentSession().get(User.class, id);
        Hibernate.initialize(user.getRoles());
        return user;
    }

    @Override
    @Transactional
    public User getUserByEmail(String email) {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root).where(builder.equal(root.get("email"), email));
        Query<User> query = session.createQuery(criteria);
        User user = query.getSingleResult();
        Hibernate.initialize(user.getRoles());
        return user;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
