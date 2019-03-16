package ru.korchinskiy.service;

import ru.korchinskiy.dto.ProductDto;
import ru.korchinskiy.dto.ProductWithCategoriesDto;

import java.util.Set;

public interface ProductService {
    ProductWithCategoriesDto getProductById(Long id);

    Set<ProductDto> getProductsByCategory(Long categoryId);

}
