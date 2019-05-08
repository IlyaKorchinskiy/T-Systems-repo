package ru.korchinskiy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korchinskiy.dao.AddressDAO;
import ru.korchinskiy.dao.UserDAO;
import ru.korchinskiy.dto.AddressDto;
import ru.korchinskiy.entity.Address;
import ru.korchinskiy.enums.AddressType;
import ru.korchinskiy.service.AddressService;
import ru.korchinskiy.service.DTOMappingService;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    private AddressDAO addressDAO;
    private UserDAO userDAO;

    private DTOMappingService dtoMappingService;

    @Override
    @Transactional
    public List<AddressDto> getPickupAddressList() {
        List<Address> addressList = addressDAO.getAddressListByType(AddressType.PICKUP);
        return dtoMappingService.convertToAddressDtoList(addressList);
    }

    @Override
    @Transactional
    public List<AddressDto> getAddressListByUser(Long userId) {
        List<Address> addressList = userDAO.getUserById(userId).getAddresses();
        return dtoMappingService.convertToAddressDtoList(addressList);
    }

    @Autowired
    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    @Autowired
    public void setDtoMappingService(DTOMappingService dtoMappingService) {
        this.dtoMappingService = dtoMappingService;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
