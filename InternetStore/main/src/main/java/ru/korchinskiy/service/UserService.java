package ru.korchinskiy.service;

import ru.korchinskiy.dto.UserDto;
import ru.korchinskiy.message.Message;

public interface UserService {
    UserDto getUserById(Long id);

    UserDto getUserByEmail(String email);

    Message addUser(UserDto user);
}
