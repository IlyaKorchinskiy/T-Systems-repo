package ru.korchinskiy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.korchinskiy.dto.CartProductDto;
import ru.korchinskiy.dto.DeliveryTypeDto;
import ru.korchinskiy.dto.NewOrderDto;
import ru.korchinskiy.dto.PaymentTypeDto;
import ru.korchinskiy.message.Message;
import ru.korchinskiy.service.CartService;
import ru.korchinskiy.service.UtilsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;

    @GetMapping
    public String showCartPage(@CookieValue(value = "sessionId", required = false) String sessionCookie,
                               Model model) {
        List<CartProductDto> cartProducts = cartService.getCartProductsBySessionId(sessionCookie);
        List<PaymentTypeDto> paymentTypes = cartService.getPaymentTypes();
        List<DeliveryTypeDto> deliveryTypes = cartService.getDeliveryTypes();
        Double sum = UtilsService.getCartSum(cartProducts);
        model.addAttribute("cartProducts", cartProducts);
        model.addAttribute("paymentTypes", paymentTypes);
        model.addAttribute("deliveryTypes", deliveryTypes);
        model.addAttribute("sum", sum);
        model.addAttribute("order", new NewOrderDto());
        return "cart";
    }

    @GetMapping("/getCart")
    @ResponseBody
    public List<CartProductDto> getCart(@CookieValue(value = "sessionId", required = false) String sessionId) {
        return cartService.getCartProductsBySessionId(sessionId);
    }

    @PostMapping("/addToCart")
    @ResponseBody
    public Message addToCart(@RequestParam(name = "id") Long productId,
                                          @CookieValue(value = "sessionId", required = false) String sessionCookie,
                                          HttpServletRequest request) {
        Message message = cartService.addProductToCartBySessionId(sessionCookie, request.getSession().getId(), productId);
        return message;
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }


}
