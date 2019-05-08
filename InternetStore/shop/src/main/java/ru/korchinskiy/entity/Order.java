package ru.korchinskiy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.korchinskiy.enums.DeliveryType;
import ru.korchinskiy.enums.OrderStatus;
import ru.korchinskiy.enums.PaymentStatus;
import ru.korchinskiy.enums.PaymentType;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_type")
    private DeliveryType deliveryType;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    private String address;

    private Double sum;

    private Instant date;

    public Order(User user, PaymentType paymentType, DeliveryType deliveryType, PaymentStatus paymentStatus, OrderStatus orderStatus, String address, Double sum, Instant date) {
        this.user = user;
        this.paymentType = paymentType;
        this.deliveryType = deliveryType;
        this.paymentStatus = paymentStatus;
        this.orderStatus = orderStatus;
        this.address = address;
        this.sum = sum;
        this.date = date;
    }
}
