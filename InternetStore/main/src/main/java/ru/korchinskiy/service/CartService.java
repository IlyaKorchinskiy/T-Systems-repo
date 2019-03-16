package ru.korchinskiy.service;

import ru.korchinskiy.dto.CartDto;
import ru.korchinskiy.dto.CartProductDto;

import java.util.Set;

public interface CartService {
    CartDto getCartById(Long id);

    CartDto getCartBySessionId(String sessionId);

    Set<CartProductDto> getCartProductSetBySessionId(String sessionId);

    String addProductToCartBySessionId(String cookie, String sessionId, Long productId);
}
