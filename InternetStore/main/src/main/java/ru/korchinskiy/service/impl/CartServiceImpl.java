package ru.korchinskiy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korchinskiy.dao.*;
import ru.korchinskiy.dto.CartDto;
import ru.korchinskiy.dto.CartProductDto;
import ru.korchinskiy.dto.DeliveryTypeDto;
import ru.korchinskiy.dto.PaymentTypeDto;
import ru.korchinskiy.entity.*;
import ru.korchinskiy.exception.NotEnoughProductException;
import ru.korchinskiy.service.CartService;
import ru.korchinskiy.service.DTOMappingService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService {
    private CartDAO cartDAO;
    private ProductDAO productDAO;
    private CartProductDAO cartProductDAO;
    private PaymentTypeDAO paymentTypeDAO;
    private DeliveryTypeDAO deliveryTypeDAO;
    private DTOMappingService dtoMappingService;

    @Override
    @Transactional
    public CartDto getCartById(Long id) {
        Cart cart = cartDAO.getCartById(id);
        return dtoMappingService.convertToCartDto(cart);
    }

    @Override
    @Transactional
    public CartDto getCartBySessionId(String sessionId) {
        Cart cart = cartDAO.getCartBySessionId(sessionId);
        return dtoMappingService.convertToCartDto(cart);
    }

    @Override
    @Transactional
    public Set<CartProductDto> getCartProductSetBySessionId(String sessionId) {
        if (sessionId == null) return null;
        Cart cart = cartDAO.getCartBySessionId(sessionId);
        Set<CartProduct> cartProducts = new HashSet<>(cartProductDAO.getCartProductListByCartId(cart.getId()));
        Set<CartProductDto> cartProductDtos = dtoMappingService.convertToCartProductDtoSet(cartProducts);
        return cartProductDtos;
    }

    @Override
    @Transactional
    public String addProductToCartBySessionId(String cookie, String sessionId, Long productId) {
        Product product = productDAO.getProductById(productId);
        if (product.getAmount() == 0) throw new NotEnoughProductException();
        Cart cart;
        if (cookie == null) {
            cart = new Cart();
            cart.setSessionId(sessionId);
            cartDAO.saveCart(cart);
            CartProduct cartProduct = new CartProduct();
            cartProduct.setCart(cart);
            cartProduct.setProduct(product);
            cartProduct.setAmount(1);
            cartProductDAO.saveCartProduct(cartProduct);
        } else {
            cart = cartDAO.getCartBySessionId(cookie);
            CartProduct cartProduct = cartProductDAO.getCartProductByCartIdAndProductId(cart.getId(), productId);
            if (cartProduct != null) {
                cartProduct.setAmount(cartProduct.getAmount() + 1);
                return cart.getSessionId();
            }
            cartProduct = new CartProduct();
            cartProduct.setCart(cart);
            cartProduct.setProduct(product);
            cartProduct.setAmount(1);
            cartProductDAO.saveCartProduct(cartProduct);
        }
        return cart.getSessionId();
    }

    @Override
    @Transactional
    public List<PaymentTypeDto> getPaymentTypes() {
        List<PaymentType> paymentTypes = paymentTypeDAO.getAllPaymentTypes();
        return dtoMappingService.convertToPaymentTypeDtoList(paymentTypes);
    }

    @Override
    @Transactional
    public List<DeliveryTypeDto> getDeliveryTypes() {
        List<DeliveryType> deliveryTypes = deliveryTypeDAO.getAllDeliveryTypes();
        return dtoMappingService.convertToDeliveryTypeDtoList(deliveryTypes);
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
    public void setPaymentTypeDAO(PaymentTypeDAO paymentTypeDAO) {
        this.paymentTypeDAO = paymentTypeDAO;
    }

    @Autowired
    public void setDeliveryTypeDAO(DeliveryTypeDAO deliveryTypeDAO) {
        this.deliveryTypeDAO = deliveryTypeDAO;
    }

    @Autowired
    public void setDtoMappingService(DTOMappingService dtoMappingService) {
        this.dtoMappingService = dtoMappingService;
    }
}
