package ru.korchinskiy.dto;

import lombok.Data;
import ru.korchinskiy.enums.OrderStatus;
import ru.korchinskiy.enums.PaymentStatus;

import java.util.Date;

@Data
public class OrderHistoryDto {
    private Long id;
    private PaymentTypeDto paymentType;
    private DeliveryTypeDto deliveryType;
    private PaymentStatus paymentStatus;
    private OrderStatus orderStatus;
    private String address;
    private Double sum;
    private Date date;
}
