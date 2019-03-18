package ru.korchinskiy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.korchinskiy.dto.CartProductDto;
import ru.korchinskiy.dto.DeliveryTypeDto;
import ru.korchinskiy.dto.OrderDto;
import ru.korchinskiy.dto.PaymentTypeDto;
import ru.korchinskiy.service.CartService;
import ru.korchinskiy.service.UtilsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;
    private UtilsService utilsService;

    @GetMapping
    public String showCartPage(@CookieValue(value = "sessionId", required = false) String sessionCookie,
                               Model model) {
        Set<CartProductDto> cartProducts = cartService.getCartProductSetBySessionId(sessionCookie);
        List<PaymentTypeDto> paymentTypes = cartService.getPaymentTypes();
        List<DeliveryTypeDto> deliveryTypes = cartService.getDeliveryTypes();
        Double sum = utilsService.getCartSum(cartProducts);
        model.addAttribute("cartProducts", cartProducts);
        model.addAttribute("paymentTypes", paymentTypes);
        model.addAttribute("deliveryTypes", deliveryTypes);
        model.addAttribute("sum", sum);
        model.addAttribute("order", new OrderDto());
        return "cart";
    }

    @GetMapping("/getCart")
    @ResponseBody
    public Set<CartProductDto> getCart(@CookieValue(value = "sessionId", required = false) String sessionCookie) {
        return cartService.getCartProductSetBySessionId(sessionCookie);
    }

    @PostMapping("/addToCart")
    @ResponseBody
    public Set<CartProductDto> addToCart(@RequestParam(name = "id") Long productId,
                                         @CookieValue(value = "sessionId", required = false) String sessionCookie,
                                         HttpServletRequest request) {
        String sessionId = cartService.addProductToCartBySessionId(sessionCookie, request.getSession().getId(), productId);
        return cartService.getCartProductSetBySessionId(sessionId);
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
