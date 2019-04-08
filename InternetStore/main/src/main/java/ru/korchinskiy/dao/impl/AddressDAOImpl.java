package ru.korchinskiy.dao.impl;

import org.springframework.stereotype.Repository;
import ru.korchinskiy.dao.AddressDAO;
import ru.korchinskiy.entity.Address;
import ru.korchinskiy.enums.AddressType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class AddressDAOImpl implements AddressDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Address getAddressById(Long id) {
        return this.entityManager.find(Address.class, id);
    }

    @Override
    public Address getAddressByNameAndType(String address, AddressType type) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Address> query = builder.createQuery(Address.class);
        Root<Address> root = query.from(Address.class);
        query.select(root)
                .where(builder.equal(root.get("address"), address),
                        builder.equal(root.get("addressType"), type));
        List<Address> addresses = entityManager.createQuery(query).getResultList();
        if (addresses.size() == 0) return null;
        return addresses.get(0);
    }

    @Override
    public void saveAddress(Address address) {
        this.entityManager.persist(address);
    }

    @Override
    public void removeAddress(Address address) {
        this.entityManager.remove(address);
    }

}
