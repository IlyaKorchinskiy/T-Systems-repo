package ru.korchinskiy.service;

import ru.korchinskiy.dto.CartDto;
import ru.korchinskiy.dto.CartProductDto;
import ru.korchinskiy.dto.UserDto;
import ru.korchinskiy.entity.Cart;
import ru.korchinskiy.entity.Product;
import ru.korchinskiy.message.Message;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface CartService {
    CartDto getCartById(Long id);

    CartDto getCartByUserId(Long id);

    /**
     * Parses CartDto object from Cookie object
     * @param cookieCart Cookie cart from user cookie
     * @return CartDto object
     * @throws UnsupportedEncodingException
     */
    CartDto getCookieCart(Cookie cookieCart) throws UnsupportedEncodingException;

    /**
     * Adds product to cookie cart using Gson and if user is authenticated adds product to database cart
     * returns confirm message if success or error message if fail
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param productId Long id of product
     * @return Message object wich contains 3 lists of errors, warnings and confirms
     * @throws UnsupportedEncodingException
     */
    Message addProductToCart(HttpServletRequest request, HttpServletResponse response, Long productId) throws UnsupportedEncodingException;

    /**
     * Merges cookie cart and database cart of the user.
     * Writes products from cookie cart to database cart and after writes cookie cart from db cart
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param userDto UserDto of the user
     * @throws UnsupportedEncodingException
     */
    void mergeCarts(HttpServletRequest request, HttpServletResponse response, UserDto userDto) throws UnsupportedEncodingException;

    /**
     * cleans cookie cart and database cart of the user
     * @param response HttpServletResponse
     * @param userId Long of the user
     */
    void cleanCarts(HttpServletResponse response, Long userId);

    /**
     * creates cart for new user
     * @param userId Long of new user
     */
    void addNewCart(Long userId);
}
