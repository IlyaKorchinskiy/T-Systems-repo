package ru.korchinskiy.service;

import ru.korchinskiy.dto.CartProductDto;

import java.util.Set;

public interface UtilsService {
    Double getCartSum(Set<CartProductDto> cartProducts);
}
