package ru.korchinskiy.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korchinskiy.dao.AddressDAO;
import ru.korchinskiy.dao.RoleDAO;
import ru.korchinskiy.dao.UserDAO;
import ru.korchinskiy.dto.OrderDto;
import ru.korchinskiy.dto.UserDto;
import ru.korchinskiy.entity.Address;
import ru.korchinskiy.entity.Role;
import ru.korchinskiy.entity.User;
import ru.korchinskiy.enums.AddressType;
import ru.korchinskiy.message.Message;
import ru.korchinskiy.service.CartService;
import ru.korchinskiy.service.DTOMappingService;
import ru.korchinskiy.service.OrderService;
import ru.korchinskiy.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Long ROLE_CLIENT = 1L;
    private static Logger logger = Logger.getLogger(UserServiceImpl.class);
    private BCryptPasswordEncoder passwordEncoder;

    private UserDAO userDAO;
    private RoleDAO roleDAO;
    private AddressDAO addressDAO;

    private DTOMappingService dtoMappingService;
    private OrderService orderService;
    private CartService cartService;

    @Override
    @Transactional
    public UserDto getUserById(Long id) {
        User user = userDAO.getUserById(id);
        UserDto userDto = dtoMappingService.convertToUserDto(user);
        List<OrderDto> orderDtos = orderService.getOrdersByUser(userDto);
        userDto.setOrders(orderDtos);
        return userDto;
    }

    @Override
    @Transactional
    public UserDto getUserByEmail(String email) {
        User user = userDAO.getUserByEmail(email);
        return dtoMappingService.convertToUserDto(user);
    }

    @Override
    @Transactional
    public Message addUser(UserDto userDto) {
        Message message = new Message();
        if (userDAO.getUserByEmail(userDto.getEmail()) != null) {
            message.getErrors().add(Message.USER_ALREADY_EXISTS);
            logger.info(Message.USER_ALREADY_EXISTS);
            return message;
        }
        User user = dtoMappingService.convertToUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(roleDAO.getRoleById(ROLE_CLIENT));
        user.setRoles(roles);
        userDAO.saveUser(user);
        cartService.addNewCart(user.getId());
        message.getConfirms().add(Message.USER_ADD_SUCCESS);
        logger.info(Message.USER_ADD_SUCCESS);
        return message;
    }

    @Override
    @Transactional
    public Message updateUser(UserDto userDto, HttpSession session) {
        Message message = new Message();
        User user = userDAO.getUserById(userDto.getId());
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setBirthday(userDto.getBirthday());
        user.setPhoneNumber(userDto.getPhoneNumber());
        if (!user.getPassword().equals(userDto.getPassword()))
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        message.getConfirms().add(Message.USER_UPDATE_SUCCESS);
        logger.info(Message.USER_UPDATE_SUCCESS);
        return message;
    }

    @Override
    @Transactional
    public Message addUserAddress(String addressString, UserDto userDto) {
        Message message = new Message();
        User user = userDAO.getUserById(userDto.getId());
        Address address = addressDAO.getAddressByNameAndType(addressString, AddressType.CLIENT);
        if (address == null) {
            address = new Address(addressString, AddressType.CLIENT);
            addressDAO.saveAddress(address);
        }
        List<Address> addresses = user.getAddresses();
        addresses.add(address);
        user.setAddresses(addresses);
        message.getConfirms().add(Message.USER_ADDRESS_ADD_SUCCESS);
        logger.info(Message.USER_ADDRESS_ADD_SUCCESS);
        return message;
    }

    @Override
    @Transactional
    public Message deleteUserAddress(Long addressId, UserDto userDto) {
        User user = userDAO.getUserById(userDto.getId());
        List<Address> addresses = user.getAddresses();
        for (Address address : addresses) {
            if (address.getId().equals(addressId)) {
                addresses.remove(address);
                break;
            }
        }
        user.setAddresses(addresses);
        Message message = new Message();
        message.getConfirms().add(Message.USER_ADDRESS_DELETE_SUCCESS);
        logger.info(Message.USER_ADDRESS_DELETE_SUCCESS);
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
                break;
            }
        }
        addresses.add(newAddress);
        user.setAddresses(addresses);
        Message message = new Message();
        message.getConfirms().add(Message.USER_ADDRESS_UPDATE_SUCCESS);
        logger.info(Message.USER_ADDRESS_UPDATE_SUCCESS);
        return message;
    }

    @Autowired
    public void setDtoMappingService(DTOMappingService dtoMappingService) {
        this.dtoMappingService = dtoMappingService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
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

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
