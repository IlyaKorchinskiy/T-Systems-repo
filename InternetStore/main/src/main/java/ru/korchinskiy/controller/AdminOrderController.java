package ru.korchinskiy.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.korchinskiy.dto.OrderDto;
import ru.korchinskiy.dto.OrderHistoryDto;
import ru.korchinskiy.dto.OrderProductDto;
import ru.korchinskiy.enums.DeliveryType;
import ru.korchinskiy.enums.OrderStatus;
import ru.korchinskiy.enums.PaymentStatus;
import ru.korchinskiy.enums.PaymentType;
import ru.korchinskiy.message.Message;
import ru.korchinskiy.service.CartService;
import ru.korchinskiy.service.OrderService;

import java.util.List;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController {
    private OrderService orderService;
    private CartService cartService;

    @GetMapping
    public String orderList(Model model) {
        List<OrderDto> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "adminOrders";
    }

    @GetMapping("/{id}")
    public String orderInfo(Model model,
                            @PathVariable("id") Long orderId) {
        OrderDto order = orderService.getOrderById(orderId);
        List<OrderProductDto> orderProducts = orderService.getOrderProductsByOrderId(orderId);
        List<OrderHistoryDto> orderHistories = orderService.getOrderHistoriesByOrderId(orderId);
        model.addAttribute("order", order);
        model.addAttribute("orderProducts", orderProducts);
        model.addAttribute("orderHistories", orderHistories);
        model.addAttribute("paymentTypes", PaymentType.values());
        model.addAttribute("deliveryTypes", DeliveryType.values());
        model.addAttribute("paymentStatuses", PaymentStatus.values());
        model.addAttribute("orderStatuses", OrderStatus.values());
        return "adminOrderPage";
    }

    @PostMapping("/{id}/updateOrderStatus")
    @ResponseBody
    public Message updateOrderStatus(@RequestBody OrderStatus orderStatus,
                                     @PathVariable("id") Long orderId) {
        return orderService.updateOrderStatus(orderId, orderStatus);
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }
}
