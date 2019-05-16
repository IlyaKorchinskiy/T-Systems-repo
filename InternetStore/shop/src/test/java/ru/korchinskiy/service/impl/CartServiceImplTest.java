package ru.korchinskiy.service.impl;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.korchinskiy.dao.UserDAO;
import ru.korchinskiy.dto.CartDto;
import ru.korchinskiy.dto.CartProductDto;
import ru.korchinskiy.dto.ProductDto;
import ru.korchinskiy.dto.UserDto;
import ru.korchinskiy.entity.Product;
import ru.korchinskiy.entity.User;
import ru.korchinskiy.service.DTOMappingService;

import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceImplTest {
    private Cookie cookieCart;
    private CartDto cartDto;
    private CartProductDto cartProductDto1;
    private CartProductDto cartProductDto2;
    private UserDto userDto;
    private User user;
    private Product product;

    @Mock
    private UserDAO userDAO;

    @Mock
    private DTOMappingService dtoMappingService;

    @InjectMocks
    private CartServiceImpl cartService;

    @Before
    public void init() throws Exception {
        user = new User(1L, "Name", "Lastname", "01-01-1999", "email@gmail.com",
                "password", "+71234567890", null, null);
        cartProductDto1 = new CartProductDto(1L, new ProductDto(1L), 2, 858.0);
        cartProductDto2 = new CartProductDto(2L, new ProductDto(2L), 1, 365.0);
        userDto = new UserDto(1L);
        cartDto = new CartDto(1L, userDto, null);
        cookieCart = new Cookie("cart", null);
        cookieCart.setMaxAge(24 * 60 * 60);
        cookieCart.setPath("/");
        cookieCart.setHttpOnly(true);
        product = new Product(3L, "title3");
    }

    @Test
    public void getCookieCart() throws UnsupportedEncodingException {
        when(userDAO.getUserById(1L)).thenReturn(user);
        Gson gson = new Gson();
        List<CartProductDto> cartProductDtoList = new ArrayList<>();
        cartProductDtoList.add(cartProductDto1);
        cartProductDtoList.add(cartProductDto2);
        cartDto.setCartProducts(cartProductDtoList);
        cookieCart.setValue(URLEncoder.encode(gson.toJson(cartDto), "UTF-8"));

        Assert.assertEquals(2, cartService.getCookieCart(cookieCart).getCartProducts().size());
    }

    @Test
    public void addProductToCartDto() {
        List<CartProductDto> cartProductDtoList = new ArrayList<>();
        cartProductDtoList.add(cartProductDto1);
        cartProductDtoList.add(cartProductDto2);
        cartDto.setCartProducts(cartProductDtoList);
        CartDto actualCartDto = cartService.addProductToCartDto(cartDto, product);

        Assert.assertEquals(3, actualCartDto.getCartProducts().size());
    }


}
