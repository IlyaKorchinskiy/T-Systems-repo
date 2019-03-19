package ru.korchinskiy.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "payment_status")
@Data
public class PaymentStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_status")
    private String paymentStatus;
}
