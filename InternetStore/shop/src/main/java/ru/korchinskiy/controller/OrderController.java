package ru.korchinskiy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;
import ru.korchinskiy.dto.AddressDto;
import ru.korchinskiy.dto.CartDto;
import ru.korchinskiy.dto.NewOrderDto;
import ru.korchinskiy.enums.DeliveryType;
import ru.korchinskiy.enums.PaymentType;
import ru.korchinskiy.message.Message;
import ru.korchinskiy.service.AddressService;
import ru.korchinskiy.service.CartService;
import ru.korchinskiy.service.OrderService;
import ru.korchinskiy.service.UtilsService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;
    private CartService cartService;
    private AddressService addressService;

    @PostMapping
    public String saveOrder(Model model,
                            @ModelAttribute("order") NewOrderDto order,
                            HttpServletRequest request,
                            HttpServletResponse response) throws UnsupportedEncodingException {
        Message message = orderService.saveOrder(order, request, response);
        model.addAttribute("message", message);
        if (message.getConfirms().size() != 0) {
            return "orderSuccess";
        } else {
            Cookie cookieCart = WebUtils.getCookie(request, "cart");
            CartDto cart = cartService.getCookieCart(cookieCart);
            List<AddressDto> userAddresses = addressService.getAddressListByUser(cart.getUser().getId());
            List<AddressDto> pickupAddressList = addressService.getPickupAddressList();
            model.addAttribute("cart", cart);
            model.addAttribute("paymentTypes", PaymentType.values());
            model.addAttribute("deliveryTypes", DeliveryType.values());
            model.addAttribute("userAddresses", userAddresses);
            model.addAttribute("pickupAddresses", pickupAddressList);
            model.addAttribute("sum", UtilsService.getCartSum(cart));
            model.addAttribute("order", new NewOrderDto());
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
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }
}
