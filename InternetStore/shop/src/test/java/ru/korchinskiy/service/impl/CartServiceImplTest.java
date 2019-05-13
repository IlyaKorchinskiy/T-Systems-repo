package ru.korchinskiy.service.impl;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.korchinskiy.config.PersistenceJpaConfig;
import ru.korchinskiy.config.WebConfig;
import ru.korchinskiy.dao.UserDAO;
import ru.korchinskiy.dao.impl.UserDAOImpl;
import ru.korchinskiy.dto.CartDto;
import ru.korchinskiy.dto.CartProductDto;
import ru.korchinskiy.dto.UserDto;
import ru.korchinskiy.entity.User;
import ru.korchinskiy.service.CartService;
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
        cartProductDto1 = new CartProductDto(1L, null, 2, 858.0);
        cartProductDto2 = new CartProductDto(2L, null, 1, 365.0);
        userDto = new UserDto(1L);
        cartDto = new CartDto(1L, userDto, null);
        cookieCart = new Cookie("cart", null);
        cookieCart.setMaxAge(24 * 60 * 60);
        cookieCart.setPath("/");
        cookieCart.setHttpOnly(true);

        when(userDAO.getUserById(1L)).thenReturn(user);
        when(dtoMappingService.convertToUserDto(user)).thenReturn(new UserDto(1L));
    }

    @Test
    public void getCookieCart() throws UnsupportedEncodingException {
        Gson gson = new Gson();
        List<CartProductDto> cartProductDtoList = new ArrayList<>();
        cartProductDtoList.add(cartProductDto1);
        cartProductDtoList.add(cartProductDto2);
        cartDto.setCartProducts(cartProductDtoList);
        cookieCart.setValue(URLEncoder.encode(gson.toJson(cartDto), "UTF-8"));

        Assert.assertEquals(1L, cartService.getCookieCart(cookieCart).getCartProducts().get(0).getId(), 0);
        Assert.assertEquals(2L, cartService.getCookieCart(cookieCart).getCartProducts().get(1).getId(), 0);

    }

    @Test
    public void addProductToCart() {
    }
}
