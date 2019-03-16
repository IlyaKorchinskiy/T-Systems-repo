package ru.korchinskiy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.korchinskiy.dto.CartDto;
import ru.korchinskiy.dto.CartProductDto;
import ru.korchinskiy.service.CartService;
import ru.korchinskiy.service.UtilsService;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Controller
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;
    private UtilsService utilsService;

    @GetMapping
    public String showCart(@CookieValue(value = "sessionId" ,required = false) String sessionCookie,
                           Model model) {
        Set<CartProductDto> cartProducts = cartService.getCartProductSetBySessionId(sessionCookie);
        Double sum = utilsService.getCartSum(cartProducts);
        model.addAttribute("cartProducts", cartProducts);
        model.addAttribute("sum", sum);
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
        Set<CartProductDto> cartProductSet = cartService.getCartProductSetBySessionId(sessionId);
        return cartProductSet;
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
