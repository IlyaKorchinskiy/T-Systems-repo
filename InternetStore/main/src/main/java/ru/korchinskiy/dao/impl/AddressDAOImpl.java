package ru.korchinskiy.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.korchinskiy.dao.AddressDAO;
import ru.korchinskiy.entity.Address;
import ru.korchinskiy.enums.AddressType;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class AddressDAOImpl implements AddressDAO {
    private SessionFactory sessionFactory;

    @Override
    public Address getAddressById(Long id) {
        return this.sessionFactory.getCurrentSession().get(Address.class, id);
    }

    @Override
    public Address getAddressByNameAndType(String address, AddressType type) {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Address> query = builder.createQuery(Address.class);
        Root<Address> root = query.from(Address.class);
        query.select(root)
                .where(builder.equal(root.get("address"), address),
                        builder.equal(root.get("addressType"), type));
        List<Address> addresses = session.createQuery(query).getResultList();
        if (addresses.size() == 0) return null;
        return addresses.get(0);
    }

    @Override
    public Long saveAddress(Address address) {
        return (Long) this.sessionFactory.getCurrentSession().save(address);
    }

    @Override
    public void removeAddress(Address address) {
        this.sessionFactory.getCurrentSession().delete(address);
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
