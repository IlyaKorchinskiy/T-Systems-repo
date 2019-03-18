package ru.korchinskiy.dto;

import lombok.Data;

@Data
public class OrderDto {
    private String name;
    private String phoneNumber;
    private String email;
    private Long deliveryType;
    private String address;
    private Long paymentType;
    private String pickupAddress;
}
