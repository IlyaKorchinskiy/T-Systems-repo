package ru.korchinskiy.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korchinskiy.dao.CartDAO;
import ru.korchinskiy.dao.CartProductDAO;
import ru.korchinskiy.dao.ProductDAO;
import ru.korchinskiy.dto.CartDto;
import ru.korchinskiy.dto.CartProductDto;
import ru.korchinskiy.entity.Cart;
import ru.korchinskiy.entity.CartProduct;
import ru.korchinskiy.entity.Product;
import ru.korchinskiy.message.Message;
import ru.korchinskiy.service.CartService;
import ru.korchinskiy.service.DTOMappingService;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private static Logger logger = Logger.getLogger(CartServiceImpl.class);

    private CartDAO cartDAO;
    private ProductDAO productDAO;
    private CartProductDAO cartProductDAO;
    private DTOMappingService dtoMappingService;

    @Override
    @Transactional
    public CartDto getCartById(Long id) {
        Cart cart = cartDAO.getCartById(id);
        return dtoMappingService.convertToCartDto(cart);
    }

    @Override
    @Transactional
    public CartDto getCartBySessionId(String cookieSession) {
        Cart cart = cartDAO.getCartBySessionId(cookieSession);
        return dtoMappingService.convertToCartDto(cart);
    }

    @Override
    @Transactional
    public List<CartProductDto> getCartProductsBySessionId(String cookieSession) {
        if (cookieSession == null) return null;
        Cart cart = cartDAO.getCartBySessionId(cookieSession);
        List<CartProduct> cartProducts = cartProductDAO.getCartProductListByCartId(cart.getId());
        return dtoMappingService.convertToCartProductDtoList(cartProducts);
    }

    @Override
    @Transactional
    public Message addProductToCart(String cookieSession, String sessionId, Long productId) {
        Message message = new Message();
        Product product = productDAO.getProductById(productId);
        if (product.getAmount() == 0) {
            message.getErrors().add(Message.PRODUCT_NOT_ENOUGH);
            logger.info(Message.PRODUCT_NOT_ENOUGH);
            return message;
        }
        Cart cart;
        if (cookieSession == null) {
            cart = new Cart();
            cart.setSessionId(sessionId);
            cartDAO.saveCart(cart);
            CartProduct cartProduct = new CartProduct();
            cartProduct.setCart(cart);
            cartProduct.setProduct(product);
            cartProduct.setAmount(1);
            cartProductDAO.saveCartProduct(cartProduct);
        } else {
            cart = cartDAO.getCartBySessionId(cookieSession);
            CartProduct cartProduct = cartProductDAO.getCartProductByCartIdAndProductId(cart.getId(), productId);
            if (cartProduct != null) {
                cartProduct.setAmount(cartProduct.getAmount() + 1);
            } else {
                cartProduct = new CartProduct();
                cartProduct.setCart(cart);
                cartProduct.setProduct(product);
                cartProduct.setAmount(1);
                cartProductDAO.saveCartProduct(cartProduct);
            }
        }
        message.getConfirms().add(Message.PRODUCT_ADD_TO_CART_SUCCESS);
        logger.info(Message.PRODUCT_ADD_TO_CART_SUCCESS);
        return message;
    }

    @Override
    @Transactional
    public void cleanCart(String cookieSession) {
        Cart cart = cartDAO.getCartBySessionId(cookieSession);
        List<CartProduct> cartProducts = cartProductDAO.getCartProductListByCartId(cart.getId());
        for (CartProduct cartProduct : cartProducts) {
            cartProductDAO.removeCartProductById(cartProduct.getId());
        }
    }

    @Autowired
    public void setCartDAO(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Autowired
    public void setCartProductDAO(CartProductDAO cartProductDAO) {
        this.cartProductDAO = cartProductDAO;
    }

    @Autowired
    public void setDtoMappingService(DTOMappingService dtoMappingService) {
        this.dtoMappingService = dtoMappingService;
    }
}
