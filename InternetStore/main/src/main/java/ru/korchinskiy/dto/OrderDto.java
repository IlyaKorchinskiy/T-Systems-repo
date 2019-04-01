package ru.korchinskiy.dto;

import lombok.Data;
import ru.korchinskiy.entity.User;
import ru.korchinskiy.enums.OrderStatus;
import ru.korchinskiy.enums.PaymentStatus;

import java.time.Instant;
import java.util.Date;

@Data
public class OrderDto {
    private Long id;
    private User user;
    private PaymentTypeDto paymentType;
    private DeliveryTypeDto deliveryType;
    private PaymentStatus paymentStatus;
    private OrderStatus orderStatus;
    private String address;
    private Double sum;
    private Instant date;
}
