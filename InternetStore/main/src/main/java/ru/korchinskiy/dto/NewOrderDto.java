package ru.korchinskiy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.korchinskiy.enums.DeliveryType;
import ru.korchinskiy.enums.PaymentType;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class NewOrderDto {
    private DeliveryType deliveryType;
    private PaymentType paymentType;
    private String address;
    private String pickupAddress;
    private Double sum;

    public NewOrderDto(DeliveryType deliveryType, PaymentType paymentType, String address, String pickupAddress, Double sum) {
        this.deliveryType = deliveryType;
        this.paymentType = paymentType;
        this.address = address;
        this.pickupAddress = pickupAddress;
        this.sum = sum;
    }
}
