package ru.korchinskiy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_product")
@Data
@NoArgsConstructor
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    private Double cost;
    private Integer amount;

    public OrderProduct(Order order, Product product, Double cost, Integer amount) {
        this.order = order;
        this.product = product;
        this.cost = cost;
        this.amount = amount;
    }
}
