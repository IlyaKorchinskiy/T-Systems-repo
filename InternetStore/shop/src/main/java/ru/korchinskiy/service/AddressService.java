package ru.korchinskiy.service;

import ru.korchinskiy.dto.AddressDto;

import java.util.List;

public interface AddressService {
    List<AddressDto> getPickupAddressList();

    List<AddressDto> getAddressListByUser(Long userId);
}
