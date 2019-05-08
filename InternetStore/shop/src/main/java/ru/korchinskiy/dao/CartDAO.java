package ru.korchinskiy.dao;

import ru.korchinskiy.entity.Cart;

public interface CartDAO {
    Cart getCartById(Long id);

    Cart getCartByUserId(Long userId);

    void saveCart(Cart cart);
}
