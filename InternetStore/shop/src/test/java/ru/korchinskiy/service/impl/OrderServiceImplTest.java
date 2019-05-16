package ru.korchinskiy.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.korchinskiy.dto.CartDto;
import ru.korchinskiy.dto.CartProductDto;
import ru.korchinskiy.dto.NewOrderDto;
import ru.korchinskiy.dto.ProductDto;
import ru.korchinskiy.entity.*;
import ru.korchinskiy.enums.DeliveryType;
import ru.korchinskiy.enums.OrderStatus;
import ru.korchinskiy.enums.PaymentStatus;
import ru.korchinskiy.enums.PaymentType;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImplTest {

    private User user;
    private Role role;
    private List<Role> roles;
    private Order order;
    private OrderHistory orderHistory;
    private NewOrderDto newOrderDto;
    private List<CartProductDto> cartProducts;
    private List<Product> products;
    private Product product;
    private CartDto cartDto;
    private ProductDto productDto;
    private CartProductDto cartProductDto;
    private OrderProduct orderProduct;

    private OrderServiceImpl orderService = new OrderServiceImpl();

    @Before
    public void setUp() {

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
        product = new Product(1L, "title1");
        cartDto = new CartDto(1L, null, null);
        productDto = new ProductDto(1L);
        cartProductDto = new CartProductDto(1L, productDto, 2, 858.0);
        orderProduct = new OrderProduct(order, product, null, 2);

        cartProducts = new ArrayList<>();
        cartProducts.add(cartProductDto);
        cartProducts.add(new CartProductDto());

        products = new ArrayList<>();
        products.add(product);
        products.add(new Product(2L, "title2"));
    }

    @Test
    public void createNewOrder() {
        Order expectedOrder = orderService.createNewOrder(newOrderDto, user);
        order.setDate(expectedOrder.getDate());
        Assert.assertEquals(expectedOrder, order);
    }

    @Test
    public void checkForAmountsSuccess() {
        cartProducts.get(0).setAmount(2);
        cartProducts.get(1).setAmount(1);
        products.get(0).setAmount(3);
        products.get(1).setAmount(2);
        Assert.assertTrue(orderService.checkForAmounts(cartProducts, products));
    }

    @Test
    public void checkForAmountsFailBoth() {
        cartProducts.get(0).setAmount(3);
        cartProducts.get(1).setAmount(3);
        products.get(0).setAmount(2);
        products.get(1).setAmount(2);
        Assert.assertFalse(orderService.checkForAmounts(cartProducts, products));
    }

    @Test
    public void checkForAmountsFailOne() {
        cartProducts.get(0).setAmount(3);
        cartProducts.get(1).setAmount(1);
        products.get(0).setAmount(2);
        products.get(1).setAmount(2);
        Assert.assertFalse(orderService.checkForAmounts(cartProducts, products));
    }

    @Test
    public void checkForAmountsFailOneAnother() {
        cartProducts.get(0).setAmount(1);
        cartProducts.get(1).setAmount(3);
        products.get(0).setAmount(2);
        products.get(1).setAmount(2);
        Assert.assertFalse(orderService.checkForAmounts(cartProducts, products));
    }

    @Test
    public void createOrderHistory() {
        OrderHistory actualOrderHistory = orderService.createOrderHistory(order);
        orderHistory.setDate(actualOrderHistory.getDate());
        Assert.assertEquals(orderHistory, actualOrderHistory);
    }

    @Test
    public void createOrderProduct() {
        OrderProduct actualOrderProduct = orderService.createOrderProduct(cartProductDto, product, order);
        Assert.assertEquals(orderProduct, actualOrderProduct);
    }
}
