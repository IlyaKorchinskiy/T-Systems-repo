package ru.korchinskiy.service;

import ru.korchinskiy.dto.*;
import ru.korchinskiy.entity.*;
import ru.korchinskiy.enums.OrderStatus;
import ru.korchinskiy.message.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface OrderService {
    OrderDto getOrderById(Long id);

    List<OrderDto> getAllOrders();

    List<OrderDto> getOrdersByUser(UserDto user);

    List<OrderProductDto> getOrderProductsByOrderId(Long id);

    List<OrderHistoryDto> getOrderHistoriesByOrderId(Long id);

    Message saveOrder(NewOrderDto order, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException;

    Message updateOrderStatus(Long orderId, OrderStatus orderStatus);

    Order createNewOrder(NewOrderDto newOrderDto, User user);

    boolean checkForAmounts(List<CartProductDto> cartProducts, List<Product> products);

    OrderHistory createOrderHistory(Order order);

    OrderProduct createOrderProduct(CartProductDto cartProductDto, Product product, Order order);
}
