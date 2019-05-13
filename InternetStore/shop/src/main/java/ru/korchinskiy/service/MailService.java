package ru.korchinskiy.service;

import ru.korchinskiy.dto.UserDto;

public interface MailService {
    /**
     * Sends http-request to e-mail microservice for sending e-mail to User
     *
     * @param userDto UserDto who created order
     * @param orderId Long new order
     */
    void sendEmail(UserDto userDto, Long orderId);
}
