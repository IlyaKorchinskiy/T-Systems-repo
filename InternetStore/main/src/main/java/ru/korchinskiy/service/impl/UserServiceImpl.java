package ru.korchinskiy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korchinskiy.dao.AddressDAO;
import ru.korchinskiy.dao.RoleDAO;
import ru.korchinskiy.dao.UserDAO;
import ru.korchinskiy.dto.UserDto;
import ru.korchinskiy.entity.Address;
import ru.korchinskiy.entity.Role;
import ru.korchinskiy.entity.User;
import ru.korchinskiy.enums.AddressType;
import ru.korchinskiy.message.Message;
import ru.korchinskiy.service.DTOMappingService;
import ru.korchinskiy.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final String USER_ALREADY_EXISTS = "Пользователь с таким e-mail уже существует";
    private static final String USER_ADD_SUCCESS = "Пользователь успешно зарегистрирован";
    private static final Long ROLE_CLIENT = 1L;
    private static final String USER_ADDRESS_DELETE_SUCCESS = "Адрес удален успешно";
    private static final String USER_ADDRESS_UPDATE_SUCCESS = "Адрес изменен успешно";

    private UserDAO userDAO;
    private DTOMappingService dtoMappingService;
    private RoleDAO roleDAO;
    private AddressDAO addressDAO;

    @Override
    @Transactional
    public UserDto getUserById(Long id) {
        User user = userDAO.getUserById(id);
        return dtoMappingService.convertToUserDto(user);
    }

    @Override
    @Transactional
    public UserDto getUserByEmail(String email) {
        User user = userDAO.getUserByEmail(email);
        return dtoMappingService.convertToUserDto(user);
    }

    @Override
    @Transactional
    public Message addUser(UserDto user) {
        Message message = new Message();
//        Валидация

        if (userDAO.getUserByEmail(user.getEmail()) != null) {
            message.getErrors().add(USER_ALREADY_EXISTS);
            return message;
        }
        User newUser = dtoMappingService.convertToUser(user);
        List<Role> roles = new ArrayList<>();
        roles.add(roleDAO.getRoleById(ROLE_CLIENT));
        newUser.setRoles(roles);
        userDAO.saveUser(newUser);
        message.getConfirms().add(USER_ADD_SUCCESS);
        return message;
    }

    @Override
    @Transactional
    public UserDto addUserAddress(String addressString, UserDto userDto) {
        User user = userDAO.getUserById(userDto.getId());
        Address address = addressDAO.getAddressByNameAndType(addressString, AddressType.CLIENT);
        if (address == null) {
            address = new Address(addressString, AddressType.CLIENT);
            addressDAO.saveAddress(address);
        }
        List<Address> addresses = user.getAddresses();
        addresses.add(address);
        user.setAddresses(addresses);
        return dtoMappingService.convertToUserDto(user);
    }

    @Override
    @Transactional
    public Message deleteUserAddress(Long addressId, UserDto userDto) {
        User user = userDAO.getUserById(userDto.getId());
        List<Address> addresses = user.getAddresses();
        for (Address address : addresses) {
            if (address.getId().equals(addressId)) addresses.remove(address);
        }
        user.setAddresses(addresses);
        Message message = new Message();
        message.getConfirms().add(USER_ADDRESS_DELETE_SUCCESS);
        return message;
    }

    @Override
    @Transactional
    public Message updateUserAddress(Long addressId, String newAddressString, UserDto userDto) {
        Address newAddress = addressDAO.getAddressByNameAndType(newAddressString, AddressType.CLIENT);
        if (newAddress == null) {
            Long newAddressID = addressDAO.saveAddress(new Address(newAddressString, AddressType.CLIENT));
            newAddress = addressDAO.getAddressById(newAddressID);
        }
        User user = userDAO.getUserById(userDto.getId());
        List<Address> addresses = user.getAddresses();
        for (Address address : addresses) {
            if (address.getId().equals(addressId)) {
                addresses.remove(address);
            }
        }
        addresses.add(newAddress);
        user.setAddresses(addresses);
        Message message = new Message();
        message.getConfirms().add(USER_ADDRESS_UPDATE_SUCCESS);
        return message;
    }

    @Autowired
    public void setDtoMappingService(DTOMappingService dtoMappingService) {
        this.dtoMappingService = dtoMappingService;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Autowired
    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }
}
