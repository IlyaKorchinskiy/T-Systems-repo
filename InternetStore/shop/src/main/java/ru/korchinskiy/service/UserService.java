package ru.korchinskiy.service;

import ru.korchinskiy.dto.UserDto;
import ru.korchinskiy.message.Message;

import javax.servlet.http.HttpSession;

public interface UserService {
    UserDto getUserById(Long id);

    UserDto getUserByEmail(String email);

    Message addUser(UserDto userDto);

    Message updateUser(UserDto userDto, HttpSession session);

    Message addUserAddress(String address, Long userId);

    Message deleteUserAddress(Long addressId, UserDto userDto);

    Message updateUserAddress(Long addressId, String address, UserDto userDto);

}
