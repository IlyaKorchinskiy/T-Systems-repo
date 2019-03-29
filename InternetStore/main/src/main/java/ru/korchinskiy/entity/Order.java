package ru.korchinskiy.entity;

import lombok.Data;
import ru.korchinskiy.enums.OrderStatus;
import ru.korchinskiy.enums.PaymentStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "payment_type_id", referencedColumnName = "id")
    private PaymentType paymentType;

    @ManyToOne
    @JoinColumn(name = "delivery_type_id", referencedColumnName = "id")
    private DeliveryType deliveryType;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    private String address;

    private Double sum;

    private Date date;

}
