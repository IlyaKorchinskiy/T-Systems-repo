package ru.korchinskiy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.korchinskiy.dto.CartProductDto;
import ru.korchinskiy.dto.DeliveryTypeDto;
import ru.korchinskiy.dto.NewOrderDto;
import ru.korchinskiy.dto.PaymentTypeDto;
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
    private UtilsService utilsService;

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
            List<PaymentTypeDto> paymentTypes = cartService.getPaymentTypes();
            List<DeliveryTypeDto> deliveryTypes = cartService.getDeliveryTypes();
            Double sum = utilsService.getCartSum(cartProducts);
            model.addAttribute("cartProducts", cartProducts);
            model.addAttribute("paymentTypes", paymentTypes);
            model.addAttribute("deliveryTypes", deliveryTypes);
            model.addAttribute("sum", sum);
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

    @Autowired
    public void setUtilsService(UtilsService utilsService) {
        this.utilsService = utilsService;
    }
}
