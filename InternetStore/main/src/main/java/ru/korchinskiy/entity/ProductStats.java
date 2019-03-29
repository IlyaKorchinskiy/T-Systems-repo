package ru.korchinskiy.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product_stats")
@Data
public class ProductStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    private Integer amount;
    private Integer month;
    private Integer year;

}
