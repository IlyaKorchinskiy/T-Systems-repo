package ru.korchinskiy.service.impl;

import org.springframework.stereotype.Service;
import ru.korchinskiy.dto.CartProductDto;
import ru.korchinskiy.service.UtilsService;

import java.util.List;

@Service
public class UtilsServiceImpl implements UtilsService {
    @Override
    public Double getCartSum(List<CartProductDto> cartProducts) {
        double sum = 0;
        for (CartProductDto cartProduct : cartProducts) {
            sum += cartProduct.getSum();
        }
        return sum;
    }
}
