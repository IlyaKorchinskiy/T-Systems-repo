package ru.korchinskiy.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.korchinskiy.dao.RoleDAO;
import ru.korchinskiy.entity.Role;

@Repository
public class RoleDAOImpl implements RoleDAO {
    private SessionFactory sessionFactory;

    @Override
    public Role getRoleById(Long id) {
        return this.sessionFactory.getCurrentSession().get(Role.class, id);
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
