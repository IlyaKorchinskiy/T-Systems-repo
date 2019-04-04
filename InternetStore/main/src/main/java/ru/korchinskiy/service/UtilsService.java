package ru.korchinskiy.service;

import org.springframework.stereotype.Service;
import ru.korchinskiy.dto.CartProductDto;

import java.util.List;

@Service
public class UtilsService {

    public static Double getCartSum(List<CartProductDto> cartProducts) {
        if (cartProducts == null) return null;
        double sum = 0;
        for (CartProductDto cartProduct : cartProducts) {
            sum += cartProduct.getSum();
        }
        return sum;
    }
}
