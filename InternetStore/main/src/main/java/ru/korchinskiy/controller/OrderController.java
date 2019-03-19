package ru.korchinskiy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.korchinskiy.dto.CartProductDto;
import ru.korchinskiy.dto.OrderDto;
import ru.korchinskiy.entity.CartProduct;
import ru.korchinskiy.service.OrderService;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;

    @PostMapping
    public String saveOrder(Model model,
                            @ModelAttribute("order") OrderDto order,
                            @ModelAttribute("cartProducts") List<CartProductDto> cartProducts,
                            @CookieValue(value = "sessionId") String sessionId) {
        System.out.println(order);
        return "orderSuccess";
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
