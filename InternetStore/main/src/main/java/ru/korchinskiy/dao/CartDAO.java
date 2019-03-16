package ru.korchinskiy.dao;

import ru.korchinskiy.entity.Cart;

public interface CartDAO {
    Cart getCartById(Long id);

    Cart getCartBySessionId(String sessionId);

    void saveCart(Cart cart);
}
