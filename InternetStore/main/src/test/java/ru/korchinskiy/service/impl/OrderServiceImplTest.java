package ru.korchinskiy.service.impl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.korchinskiy.config.WebConfig;
import ru.korchinskiy.dto.CartDto;
import ru.korchinskiy.dto.CartProductDto;
import ru.korchinskiy.dto.NewOrderDto;
import ru.korchinskiy.dto.ProductDto;
import ru.korchinskiy.entity.*;
import ru.korchinskiy.enums.DeliveryType;
import ru.korchinskiy.enums.OrderStatus;
import ru.korchinskiy.enums.PaymentStatus;
import ru.korchinskiy.enums.PaymentType;
import ru.korchinskiy.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
public class OrderServiceImplTest {

    private static User user;
    private static Role role;
    private static List<Role> roles;
    private static Order order;
    private static OrderHistory orderHistory;
    private static NewOrderDto newOrderDto;
    private static List<CartProductDto> cartProducts;
    private static List<Product> products;
    private static Product product;
    private static CartDto cartDto;
    private static ProductDto productDto;
    private static CartProductDto cartProductDto;
    private static OrderProduct orderProduct;
    @Autowired
    private OrderService orderService;

    @BeforeClass
    public static void setUp() {
        role = new Role(1L, "ROLE_CLIENT");
        roles = new ArrayList<>();
        roles.add(role);
        user = new User(1L, "Name", "Lastname", "01-01-1999", "email@gmail.com",
                "password", "+71234567890", null, roles);
        order = new Order(user, PaymentType.CASH, DeliveryType.DELIVERY, PaymentStatus.WAITING_FOR_PAYMENT,
                OrderStatus.NEW, "Saint-Petersburg", 1299.0, null);
        newOrderDto = new NewOrderDto(DeliveryType.DELIVERY, PaymentType.CASH, "Saint-Petersburg",
                null, 1299.0);
        orderHistory = new OrderHistory(order, PaymentType.CASH, DeliveryType.DELIVERY, PaymentStatus.WAITING_FOR_PAYMENT,
                OrderStatus.NEW, "Saint-Petersburg", 1299.0, null);
        product = new Product("title", "authorName", 429.0, 5,
                "description", null, "photo1", "photo2");
        cartDto = new CartDto(1L, "sessionId");
        productDto = new ProductDto(1L, "title", "authorName", 429.0, 5,
                "description", null, "photo1", "photo2");
        cartProductDto = new CartProductDto(1L, cartDto, productDto, 2, 858.0);
        orderProduct = new OrderProduct(order, product, 429.0, 2);

        cartProducts = new ArrayList<>();
        cartProducts.add(cartProductDto);
        cartProducts.add(new CartProductDto());

        products = new ArrayList<>();
        products.add(product);
        products.add(new Product());
    }

    @Test
    public void createNewOrder() {
        Order expectedOrder = orderService.createNewOrder(newOrderDto, user);
        order.setDate(expectedOrder.getDate());
        Assert.assertEquals(expectedOrder, order);
    }

    @Test
    public void checkForAmounts1() {
        cartProducts.get(0).setAmount(2);
        cartProducts.get(1).setAmount(1);
        products.get(0).setAmount(3);
        products.get(1).setAmount(2);
        Assert.assertTrue(orderService.checkForAmounts(cartProducts, products));
    }

    @Test
    public void checkForAmounts2() {
        cartProducts.get(0).setAmount(3);
        cartProducts.get(1).setAmount(3);
        products.get(0).setAmount(2);
        products.get(1).setAmount(2);
        Assert.assertFalse(orderService.checkForAmounts(cartProducts, products));
    }

    @Test
    public void checkForAmounts3() {
        cartProducts.get(0).setAmount(3);
        cartProducts.get(1).setAmount(1);
        products.get(0).setAmount(2);
        products.get(1).setAmount(2);
        Assert.assertFalse(orderService.checkForAmounts(cartProducts, products));
    }

    @Test
    public void checkForAmounts4() {
        cartProducts.get(0).setAmount(1);
        cartProducts.get(1).setAmount(3);
        products.get(0).setAmount(2);
        products.get(1).setAmount(2);
        Assert.assertFalse(orderService.checkForAmounts(cartProducts, products));
    }

    @Test
    public void createOrderHistory() {
        OrderHistory expectedOrderHistory = orderService.createOrderHistory(order);
        orderHistory.setDate(expectedOrderHistory.getDate());
        Assert.assertEquals(expectedOrderHistory, orderHistory);
    }

    @Test
    public void createOrderProduct() {
        OrderProduct expectedOrderProduct = orderService.createOrderProduct(cartProductDto, product, order);
        Assert.assertEquals(expectedOrderProduct, orderProduct);
    }
}
