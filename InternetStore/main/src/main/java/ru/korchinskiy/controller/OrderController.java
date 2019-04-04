package ru.korchinskiy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.korchinskiy.dto.CartProductDto;
import ru.korchinskiy.dto.NewOrderDto;
import ru.korchinskiy.enums.DeliveryType;
import ru.korchinskiy.enums.PaymentType;
import ru.korchinskiy.message.Message;
import ru.korchinskiy.service.CartService;
import ru.korchinskiy.service.OrderService;
import ru.korchinskiy.service.UtilsService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;
    private CartService cartService;

    @PostMapping
    public String saveOrder(Model model,
                            @ModelAttribute("order") NewOrderDto order,
                            @CookieValue(value = "sessionId") String cookieSession,
                            HttpSession session) {
        Message message = orderService.saveOrder(order, session, cookieSession);
        model.addAttribute("message", message);
        if (message.getConfirms().size() != 0) {
            return "orderSuccess";
        } else {
            List<CartProductDto> cartProducts = cartService.getCartProductsBySessionId(cookieSession);
            model.addAttribute("cartProducts", cartProducts);
            model.addAttribute("paymentTypes", PaymentType.values());
            model.addAttribute("deliveryTypes", DeliveryType.values());
            model.addAttribute("sum", UtilsService.getCartSum(cartProducts));
            model.addAttribute("order", order);
            return "cart";
        }
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
