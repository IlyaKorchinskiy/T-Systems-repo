package ru.korchinskiy.service;

import ru.korchinskiy.dto.CategoryDto;
import ru.korchinskiy.dto.CategoryWithProductsDto;
import ru.korchinskiy.dto.ProductDto;
import ru.korchinskiy.entity.Category;
import ru.korchinskiy.entity.Product;

import java.util.Set;

public interface DTOMappingService {
    ProductDto convertToProductDto(Product product);

    Set<ProductDto> convertToProductDtoSet(Set<Product> products);

    CategoryDto convertToCategoryDto(Category category);

    CategoryWithProductsDto convertToCategoryWithProductsDto(Category category);

    Set<CategoryDto> convertToCategoryDtoSet(Set<Category> categories);
}
