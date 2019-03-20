package ru.korchinskiy.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_history")
@Data
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "payment_type_id", referencedColumnName = "id")
    private PaymentType paymentType;

    @ManyToOne
    @JoinColumn(name = "delivery_type_id", referencedColumnName = "id")
    private DeliveryType deliveryType;

    @ManyToOne
    @JoinColumn(name = "payment_status_id", referencedColumnName = "id")
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "order_status_id", referencedColumnName = "id")
    private OrderStatus orderStatus;

    private String address;

    private Double sum;

    private Date date;

}
