package ru.korchinskiy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CartDto {
    private Long id;
    private List<CartProductDto> cartProducts = new ArrayList<>();

    public CartDto(Long id, List<CartProductDto> cartProducts) {
        this.id = id;
        this.cartProducts = cartProducts;
    }
}
