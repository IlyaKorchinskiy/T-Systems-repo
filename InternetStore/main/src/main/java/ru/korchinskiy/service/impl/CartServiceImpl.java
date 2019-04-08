package ru.korchinskiy.service.impl;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.WebUtils;
import ru.korchinskiy.dao.CartDAO;
import ru.korchinskiy.dao.CartProductDAO;
import ru.korchinskiy.dao.ProductDAO;
import ru.korchinskiy.dao.UserDAO;
import ru.korchinskiy.dto.CartDto;
import ru.korchinskiy.dto.CartProductDto;
import ru.korchinskiy.dto.UserDto;
import ru.korchinskiy.entity.Cart;
import ru.korchinskiy.entity.CartProduct;
import ru.korchinskiy.entity.Product;
import ru.korchinskiy.message.Message;
import ru.korchinskiy.service.CartService;
import ru.korchinskiy.service.DTOMappingService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private static final int COOKIE_MAX_AGE = 24 * 60 * 60;
    private static Logger logger = Logger.getLogger(CartServiceImpl.class);
    private CartDAO cartDAO;
    private ProductDAO productDAO;
    private CartProductDAO cartProductDAO;
    private UserDAO userDAO;
    private DTOMappingService dtoMappingService;

    @Override
    @Transactional
    public CartDto getCartById(Long id) {
        Cart cart = cartDAO.getCartById(id);
        return dtoMappingService.convertToCartDto(cart);
    }

    @Override
    public CartDto getCartByUserId(Long id) {
        Cart cart = cartDAO.getCartByUserId(id);
        return dtoMappingService.convertToCartDto(cart);
    }

    @Override
    public CartDto getCookieCart(Cookie cookieCart) throws UnsupportedEncodingException {
        if (cookieCart == null) return null;
        Gson gson = new Gson();
        return gson.fromJson(URLDecoder.decode(cookieCart.getValue(), "UTF-8"), CartDto.class);
    }

    @Override
    @Transactional
    public Message addProductToCart(HttpServletRequest request, HttpServletResponse response, Long productId) throws UnsupportedEncodingException {
        Message message = new Message();
        Product product = productDAO.getProductById(productId);
        if (product.getAmount() == 0) {
            message.getErrors().add(Message.PRODUCT_NOT_ENOUGH);
            logger.info(Message.PRODUCT_NOT_ENOUGH);
            return message;
        }
        Cookie cookieCart = WebUtils.getCookie(request, "cart");
        CartDto cartDto;
        Gson gson = new Gson();
        if (cookieCart == null) {
            cookieCart = new Cookie("cart", null);
            cartDto = new CartDto();
        } else {
            cartDto = gson.fromJson(URLDecoder.decode(cookieCart.getValue(), "UTF-8"), CartDto.class);
        }
        cartDto = addProductToCartDto(cartDto, product);
        cookieCart.setValue(URLEncoder.encode(gson.toJson(cartDto), "UTF-8"));
        cookieCart.setMaxAge(COOKIE_MAX_AGE);
        cookieCart.setPath("/");
        response.addCookie(cookieCart);

        UserDto userDto = (UserDto) request.getSession().getAttribute("user");
        if (userDto != null) {
            Cart cart = cartDAO.getCartByUserId(userDto.getId());
            addProductToDbCart(cart, product);
        }
        message.getConfirms().add(Message.PRODUCT_ADD_TO_CART_SUCCESS);
        logger.info(Message.PRODUCT_ADD_TO_CART_SUCCESS);
        return message;
    }

    private CartDto addProductToCartDto(CartDto cartDto, Product product) {
        List<CartProductDto> cartProductDtoList = cartDto.getCartProducts();
        for (CartProductDto cartProductDto : cartProductDtoList) {
            if (cartProductDto.getProduct().getId().equals(product.getId())) {
                cartProductDto.setAmount(cartProductDto.getAmount() + 1);
                cartProductDto.setSum(cartProductDto.getSum() + product.getCost());
                return cartDto;
            }
        }
        CartProductDto cartProductDto = new CartProductDto();
        cartProductDto.setProduct(dtoMappingService.convertToProductDto(product));
        cartProductDto.setAmount(1);
        cartProductDto.setSum(product.getCost());
        cartDto.getCartProducts().add(cartProductDto);
        return cartDto;
    }

    private void addProductToDbCart(Cart cart, Product product) {
        CartProduct cartProduct = cartProductDAO.getCartProductByCartIdAndProductId(cart.getId(), product.getId());
        if (cartProduct != null) {
            cartProduct.setAmount(cartProduct.getAmount() + 1);
        } else {
            cartProduct = new CartProduct();
            cartProduct.setCart(cart);
            cartProduct.setProduct(product);
            cartProduct.setAmount(1);
            cartProductDAO.saveCartProduct(cartProduct);
        }
        logger.info(Message.PRODUCT_ADD_TO_DB_CART_SUCCESS);
    }

    @Override
    @Transactional
    public void mergeCarts(HttpServletRequest request, HttpServletResponse response, UserDto userDto) throws UnsupportedEncodingException {
        Gson gson = new Gson();
        Cart cart = cartDAO.getCartByUserId(userDto.getId());
        Cookie cookieCart = WebUtils.getCookie(request, "cart");
        CartDto mergeCart;
        if (cookieCart == null) {
            cookieCart = new Cookie("cart", null);
        } else {
            CartDto cartFromCookie = gson.fromJson(URLDecoder.decode(cookieCart.getValue(), "UTF-8"), CartDto.class);
            for (CartProductDto cartProductDto : cartFromCookie.getCartProducts()) {
                Product product = productDAO.getProductById(cartProductDto.getProduct().getId());
                addProductToDbCart(cart, product);
            }
        }
        mergeCart = dtoMappingService.convertToCartDto(cart);
        cookieCart.setValue(URLEncoder.encode(gson.toJson(mergeCart), "UTF-8"));
        cookieCart.setMaxAge(COOKIE_MAX_AGE);
        cookieCart.setPath("/");
        response.addCookie(cookieCart);
    }

    @Override
    @Transactional
    public void cleanCarts(HttpServletResponse response, Long userId) {
        Cookie cookieCart = new Cookie("cart", null);
        cookieCart.setMaxAge(0);
        cookieCart.setPath("/");
        response.addCookie(cookieCart);
        Cart cart = cartDAO.getCartByUserId(userId);
        List<CartProduct> cartProducts = cart.getCartProducts();
        for (CartProduct cartProduct : cartProducts) {
            cartProductDAO.removeCartProductById(cartProduct.getId());
        }
    }

    @Override
    @Transactional
    public void addNewCart(Long userId) {
        Cart cart = new Cart();
        cart.setUser(userDAO.getUserById(userId));
        cartDAO.saveCart(cart);
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
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setDtoMappingService(DTOMappingService dtoMappingService) {
        this.dtoMappingService = dtoMappingService;
    }
}
