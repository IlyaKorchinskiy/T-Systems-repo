package ru.korchinskiy.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "order_status")
@Data
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_status")
    private String orderStatus;
}
