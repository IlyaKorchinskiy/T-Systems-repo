package ru.korchinskiy.service;

import ru.korchinskiy.dto.ProductDto;

import java.util.Set;

public interface ProductService {
    ProductDto getProductById(Long id);

    Set<ProductDto> getProductsByCategory(Long categoryId);
}
