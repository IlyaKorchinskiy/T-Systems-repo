package ru.korchinskiy.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.korchinskiy.dto.CartDto;
import ru.korchinskiy.dto.CartProductDto;

import java.util.ArrayList;
import java.util.List;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {WebConfig.class})
//@WebAppConfiguration
public class UtilsServiceTest {

    private CartDto cartDto;
    private List<CartProductDto> cartProducts;
    private CartProductDto cartProductDto1;
    private CartProductDto cartProductDto2;
    private CartProductDto cartProductDto3;

    @Before
    public void setUp() {
        cartDto = new CartDto();
        cartProducts = new ArrayList<>();
        cartProductDto1 = new CartProductDto();
        cartProductDto2 = new CartProductDto();
        cartProductDto3 = new CartProductDto();
    }

    @Test
    public void getCartSumOneProduct() {
        cartProductDto1.setSum(429.0);
        cartProducts.add(cartProductDto1);
        cartDto.setCartProducts(cartProducts);
        double expectedSum = UtilsService.getCartSum(cartDto);
        Assert.assertEquals(429.0, expectedSum, 0.0000001);
    }

    @Test
    public void getCartSumTwoProducts() {
        cartProducts.clear();
        cartProductDto1.setSum(429.0);
        cartProductDto2.setSum(449.0);
        cartProducts.add(cartProductDto1);
        cartProducts.add(cartProductDto2);
        cartDto.setCartProducts(cartProducts);
        double expectedSum = UtilsService.getCartSum(cartDto);
        Assert.assertEquals(878.0, expectedSum, 0.0000001);
    }

    @Test
    public void getCartSumEmpty() {
        cartDto = null;
        Double expectedSum = UtilsService.getCartSum(cartDto);
        Assert.assertNull(expectedSum);
    }
}
