package ru.korchinskiy.service;

import ru.korchinskiy.dto.ProductDto;
import ru.korchinskiy.dto.ProductWithCategoriesDto;

import java.util.List;

public interface ProductService {
    ProductWithCategoriesDto getProductById(Long id);

    List<ProductDto> getAllProducts();

    List<ProductDto> getProductsByCategory(Long categoryId);

}
