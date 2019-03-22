package ru.korchinskiy.dao;

import ru.korchinskiy.entity.Address;
import ru.korchinskiy.enums.AddressType;

public interface AddressDAO {
    Address getAddressById(Long id);
    Address getAddressByNameAndType(String address, AddressType type);
    Long saveAddress(Address address);
    void removeAddress(Address address);
}
