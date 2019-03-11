package ru.korchinskiy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.korchinskiy.dao.UserDAO;
import ru.korchinskiy.dto.UserDto;
import ru.korchinskiy.entity.User;

@Service
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private DTOMappingService dtoMappingService;

    @Override
    public UserDto getUserById(Long id) {
        User user = userDAO.getUserById(id);
        return dtoMappingService.convertToUserDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userDAO.getUserByEmail(email);
        return dtoMappingService.convertToUserDto(user);
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
