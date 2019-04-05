package ru.korchinskiy.dto;

import lombok.Data;

@Data
public class CartProductDto {
    private Long id;
    private CartDto cart;
    private ProductDto product;
    private Integer amount;
    private Double sum;
}
