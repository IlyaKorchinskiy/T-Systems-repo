package ru.korchinskiy.service;

import org.springframework.stereotype.Service;
import ru.korchinskiy.dto.CategoryDto;
import ru.korchinskiy.dto.ProductDto;
import ru.korchinskiy.entity.Category;
import ru.korchinskiy.entity.Product;

import java.util.HashSet;
import java.util.Set;

@Service
public class DTOMappingServiceImpl implements DTOMappingService {
    @Override
    public ProductDto convertToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setCost(product.getCost());
        productDto.setTitle(product.getTitle());
        productDto.setAmount(product.getAmount());
        productDto.setCategories(product.getCategories());
        return productDto;
    }

    @Override
    public Set<ProductDto> convertToProductDtoSet(Set<Product> products) {
        Set<ProductDto> productDtos = new HashSet<>();
        for (Product product : products) {
            ProductDto productDto = convertToProductDto(product);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    @Override
    public CategoryDto convertToCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setParentId(category.getParentId());
        categoryDto.setProducts(category.getProducts());
        return categoryDto;
    }

    @Override
    public Set<CategoryDto> convertToCategoryDtoSet(Set<Category> categories) {
        Set<CategoryDto> categoryDtos = new HashSet<>();
        for (Category category : categories) {
            CategoryDto categoryDto = convertToCategoryDto(category);
            categoryDtos.add(categoryDto);
        }
        return categoryDtos;
    }
}
