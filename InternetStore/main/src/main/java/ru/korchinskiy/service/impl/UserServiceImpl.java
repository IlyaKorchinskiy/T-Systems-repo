package ru.korchinskiy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korchinskiy.dao.RoleDAO;
import ru.korchinskiy.dao.UserDAO;
import ru.korchinskiy.dto.UserDto;
import ru.korchinskiy.entity.Role;
import ru.korchinskiy.entity.User;
import ru.korchinskiy.message.Message;
import ru.korchinskiy.service.DTOMappingService;
import ru.korchinskiy.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private static final String USER_ALREADY_EXISTS = "Пользователь с таким e-mail уже существует";
    private static final String USER_ADD_SUCCESS = "Пользователь успешно зарегистрирован";
    private static final Long ROLE_CLIENT = 1L;

    private UserDAO userDAO;
    private DTOMappingService dtoMappingService;
    private RoleDAO roleDAO;

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
}
