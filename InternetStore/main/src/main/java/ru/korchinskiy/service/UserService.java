package ru.korchinskiy.service;

import ru.korchinskiy.dto.UserDto;

public interface UserService {
    UserDto getUserById(Long id);

    UserDto getUserByEmail(String email);
}
