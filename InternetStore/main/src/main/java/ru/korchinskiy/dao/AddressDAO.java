package ru.korchinskiy.dao;

import ru.korchinskiy.entity.Address;
import ru.korchinskiy.enums.AddressType;

import java.util.List;

public interface AddressDAO {
    Address getAddressById(Long id);

    Address getAddressByNameAndType(String address, AddressType type);

    List<Address> getAddressListByType(AddressType type);

    void saveAddress(Address address);

    void removeAddress(Address address);
}
