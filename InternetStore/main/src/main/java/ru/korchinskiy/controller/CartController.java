package ru.korchinskiy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.korchinskiy.dto.AddressDto;
import ru.korchinskiy.dto.CartDto;
import ru.korchinskiy.dto.NewOrderDto;
import ru.korchinskiy.enums.DeliveryType;
import ru.korchinskiy.enums.PaymentType;
import ru.korchinskiy.message.Message;
import ru.korchinskiy.service.AddressService;
import ru.korchinskiy.service.CartService;
import ru.korchinskiy.service.UtilsService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;
    private AddressService addressService;

    @GetMapping
    public String showCartPage(@CookieValue(value = "cart", required = false) Cookie cookieCart,
                               Model model) throws UnsupportedEncodingException {
        CartDto cart = cartService.getCookieCart(cookieCart);
        List<AddressDto> pickupAddressList = addressService.getPickupAddressList();
        model.addAttribute("cart", cart);
        model.addAttribute("paymentTypes", PaymentType.values());
        model.addAttribute("deliveryTypes", DeliveryType.values());
        model.addAttribute("pickupAddresses", pickupAddressList);
        model.addAttribute("sum", UtilsService.getCartSum(cart));
        model.addAttribute("order", new NewOrderDto());
        return "cart";
    }

    @GetMapping("/getCart")
    @ResponseBody
    public CartDto getCart(@CookieValue(value = "cart", required = false) Cookie cookieCart) throws UnsupportedEncodingException {
        return cartService.getCookieCart(cookieCart);
    }

    @PostMapping("/addToCart")
    @ResponseBody
    public Message addToCart(@RequestParam(name = "id") Long productId,
                             HttpServletRequest request,
                             HttpServletResponse response) throws UnsupportedEncodingException {
        return cartService.addProductToCart(request, response, productId, 1);
    }

    @GetMapping("/removeFromCart")
    public String removeFromCart(@RequestParam(name = "id") Long productId,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws UnsupportedEncodingException {
        cartService.removeProductFromCart(request, response, productId);
        return "redirect:/cart";
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

}
