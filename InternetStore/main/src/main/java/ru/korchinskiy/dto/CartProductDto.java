package ru.korchinskiy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartProductDto {
    private Long id;
    private ProductDto product;
    private Integer amount;
    private Double sum;

    public CartProductDto(Long id, ProductDto product, Integer amount, Double sum) {
        this.id = id;
        this.product = product;
        this.amount = amount;
        this.sum = sum;
    }
}
