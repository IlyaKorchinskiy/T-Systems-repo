package ru.korchinskiy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.korchinskiy.dto.OrderDto;
import ru.korchinskiy.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;

    @PostMapping
    public String saveOrder(Model model,
                            @ModelAttribute("order") OrderDto order,
                            @CookieValue(value = "sessionId") String sessionCookie) {
        System.out.println(order);
        return "orderSuccess";
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
