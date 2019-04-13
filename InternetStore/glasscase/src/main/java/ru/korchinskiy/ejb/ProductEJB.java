package ru.korchinskiy.ejb;

import ru.korchinskiy.dto.ProductDto;

import java.util.List;

public interface ProductEJB {

    List<ProductDto> getTopProducts();
}
