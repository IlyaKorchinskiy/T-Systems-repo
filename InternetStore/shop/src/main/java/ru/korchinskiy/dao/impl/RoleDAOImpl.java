package ru.korchinskiy.dao.impl;

import org.springframework.stereotype.Repository;
import ru.korchinskiy.dao.RoleDAO;
import ru.korchinskiy.entity.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDAOImpl implements RoleDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getRoleById(Long id) {
        return this.entityManager.find(Role.class, id);
    }

}
