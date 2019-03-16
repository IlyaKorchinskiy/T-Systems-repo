package ru.korchinskiy.dto;

import lombok.Data;
import ru.korchinskiy.entity.Cart;
import ru.korchinskiy.entity.Product;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class CartProductDto {
    private Long id;
    private CartDto cart;
    private ProductDto product;
    private Integer amount;
    private Double sum;
}
