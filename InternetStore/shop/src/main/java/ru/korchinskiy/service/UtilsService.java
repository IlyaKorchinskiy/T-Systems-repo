package ru.korchinskiy.service;

import org.springframework.stereotype.Service;
import ru.korchinskiy.dto.CartDto;
import ru.korchinskiy.dto.CartProductDto;

@Service
public class UtilsService {

    public static Double getCartSum(CartDto cartDto) {
        if (cartDto == null) return null;
        double sum = 0;
        for (CartProductDto cartProduct : cartDto.getCartProducts()) {
            sum += cartProduct.getSum();
        }
        return sum;
    }
}
