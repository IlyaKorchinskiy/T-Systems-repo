package ru.korchinskiy.service;

import ru.korchinskiy.dto.CartDto;
import ru.korchinskiy.dto.CartProductDto;
import ru.korchinskiy.message.Message;

import java.util.List;

public interface CartService {
    CartDto getCartById(Long id);

    CartDto getCartBySessionId(String cookieSession);

    List<CartProductDto> getCartProductsBySessionId(String cookieSession);

    Message addProductToCart(String cookieSession, String sessionId, Long productId);

    void cleanCart(String cookieSession);
}
