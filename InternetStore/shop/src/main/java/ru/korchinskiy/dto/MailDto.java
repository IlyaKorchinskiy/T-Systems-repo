package ru.korchinskiy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MailDto {
    private String userName;
    private Long orderId;

    public MailDto(String userName, Long orderId) {
        this.userName = userName;
        this.orderId = orderId;
    }
}
