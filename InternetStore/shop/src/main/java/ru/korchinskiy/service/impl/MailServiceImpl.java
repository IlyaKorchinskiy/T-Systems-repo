package ru.korchinskiy.service.impl;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.korchinskiy.dto.MailDto;
import ru.korchinskiy.dto.UserDto;
import ru.korchinskiy.message.Message;
import ru.korchinskiy.service.MailService;

@Service
public class MailServiceImpl implements MailService {
    private static final Logger logger = Logger.getLogger(MailServiceImpl.class);
    private static final String EMAIL_URL = "http://email:3000/send-email";

    @Override
    public void sendEmail(UserDto userDto, Long orderId) {
        MailDto mail = new MailDto(userDto.getName() + " " + userDto.getLastname(), orderId);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MailDto> request = new HttpEntity<>(mail);
        ResponseEntity<String> response = restTemplate.exchange(EMAIL_URL, HttpMethod.POST, request, String.class);
        if (response.getStatusCodeValue() != 200) {
            logger.warn(response.getStatusCode());
        }
        String message = response.getBody();
        if (message.equals("error")) logger.warn(Message.EMAIL_SEND_FAIL);
        else logger.info(Message.EMAIL_SEND_SUCCESS);

    }
}
