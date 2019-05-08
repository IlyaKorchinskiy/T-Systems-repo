package ru.korchinskiy.dto;

import lombok.Data;
import ru.korchinskiy.enums.DeliveryType;
import ru.korchinskiy.enums.OrderStatus;
import ru.korchinskiy.enums.PaymentStatus;
import ru.korchinskiy.enums.PaymentType;

import java.time.Instant;

@Data
public class OrderDto {
    private Long id;
    private UserDto user;
    private PaymentType paymentType;
    private DeliveryType deliveryType;
    private PaymentStatus paymentStatus;
    private OrderStatus orderStatus;
    private String address;
    private Double sum;
    private Instant date;
}
