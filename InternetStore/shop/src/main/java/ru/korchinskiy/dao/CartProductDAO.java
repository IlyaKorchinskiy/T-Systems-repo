package ru.korchinskiy.dao;

import ru.korchinskiy.entity.CartProduct;

import java.util.List;

public interface CartProductDAO {
    CartProduct getCartProductById(Long id);

    CartProduct getCartProductByCartIdAndProductId(Long cartId, Long productId);

    List<CartProduct> getCartProductListByCartId(Long cartId);

    void saveCartProduct(CartProduct cartProduct);

    void removeCartProductById(Long id);

    void removerCartProductByCartIdAndProductId(Long cartId, Long productId);
}
