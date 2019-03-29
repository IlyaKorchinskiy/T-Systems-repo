package ru.korchinskiy.dto;

import lombok.Data;

@Data
public class OrderProductDto {
    private Long id;
    private OrderDto order;
    private ProductDto product;
    private Double cost;
    private Integer amount;
}
