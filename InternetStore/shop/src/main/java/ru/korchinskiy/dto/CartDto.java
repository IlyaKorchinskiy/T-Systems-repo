package ru.korchinskiy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CartDto {
    private Long id;
    private UserDto user;
    private List<CartProductDto> cartProducts = new ArrayList<>();

    public CartDto(Long id, UserDto user, List<CartProductDto> cartProducts) {
        this.id = id;
        this.user = user;
        this.cartProducts = cartProducts;
    }
}
