package ru.korchinskiy.dto;

import lombok.Data;
import ru.korchinskiy.entity.*;

import java.util.Date;

@Data
public class OrderDto {
    private Long id;
    private User user;
    private PaymentType paymentType;
    private DeliveryType deliveryType;
    private PaymentStatus paymentStatus;
    private OrderStatus orderStatus;
    private String address;
    private Double sum;
    private Date date;
}
