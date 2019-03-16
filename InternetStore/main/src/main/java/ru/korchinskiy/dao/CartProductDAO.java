package ru.korchinskiy.dao;

import ru.korchinskiy.entity.CartProduct;

import java.util.List;

public interface CartProductDAO {
    CartProduct getCartProductByCartIdAndProductId(Long cartId, Long productId);
    List<CartProduct> getCartProductListByCartId(Long cartId);
    void saveCartProduct(CartProduct cartProduct);
}
