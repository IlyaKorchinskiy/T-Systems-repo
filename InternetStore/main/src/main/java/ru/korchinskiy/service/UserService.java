package ru.korchinskiy.service;

import ru.korchinskiy.dto.UserDto;
import ru.korchinskiy.message.Message;

public interface UserService {
    UserDto getUserById(Long id);

    UserDto getUserByEmail(String email);

    Message addUser(UserDto user);

    UserDto addUserAddress(String address, UserDto user);

    Message deleteUserAddress(Long addressId, UserDto user);

    Message updateUserAddress(Long addressId, String address, UserDto userDto);
}
