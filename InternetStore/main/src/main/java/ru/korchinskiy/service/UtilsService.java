package ru.korchinskiy.service;

import ru.korchinskiy.dto.CartProductDto;

import java.util.List;

public interface UtilsService {
    Double getCartSum(List<CartProductDto> cartProducts);
}
