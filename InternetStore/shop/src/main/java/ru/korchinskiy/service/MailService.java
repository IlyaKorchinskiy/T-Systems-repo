package ru.korchinskiy.service;

import ru.korchinskiy.dto.UserDto;

public interface MailService {
    void sendEmail(UserDto userDto, Long orderId);
}
