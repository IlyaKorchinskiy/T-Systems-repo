package ru.korchinskiy.dto;

import lombok.Data;

@Data
public class OrderDto {
    private Long deliveryTypeId;
    private Long paymentTypeId;
    private String address;
    private String pickupAddress;
}
