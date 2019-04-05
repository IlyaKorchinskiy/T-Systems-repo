package ru.korchinskiy.service;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.korchinskiy.config.WebConfig;
import ru.korchinskiy.dto.CartDto;
import ru.korchinskiy.dto.CartProductDto;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class UtilsServiceTest {

    private static CartDto cartDto;
    private static List<CartProductDto> cartProducts;
    private static CartProductDto cartProductDto1;
    private static CartProductDto cartProductDto2;
    private static CartProductDto cartProductDto3;

    @BeforeClass
    public static void setUp() {
        cartDto = new CartDto();
        cartProducts = new ArrayList<>();
        cartProductDto1 = new CartProductDto();
        cartProductDto2 = new CartProductDto();
        cartProductDto3 = new CartProductDto();
    }

    @Test
    public void getCartSum1() {
        cartProductDto1.setSum(429.0);
        cartProducts.add(cartProductDto1);
        cartDto.setCartProducts(cartProducts);
        double expectedSum = UtilsService.getCartSum(cartDto);
        Assert.assertEquals(expectedSum, 429.0, 0.0000001);
    }

    @Test
    public void getCartSum2() {
        cartProducts.clear();
        cartProductDto1.setSum(429.0);
        cartProductDto2.setSum(449.0);
        cartProducts.add(cartProductDto1);
        cartProducts.add(cartProductDto2);
        cartDto.setCartProducts(cartProducts);
        double expectedSum = UtilsService.getCartSum(cartDto);
        Assert.assertEquals(expectedSum, 878.0, 0.0000001);
    }

    @Test
    public void getCartSum3() {
        cartDto = null;
        Double expectedSum = UtilsService.getCartSum(cartDto);
        Assert.assertNull(expectedSum);
    }
}
