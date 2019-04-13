package ru.korchinskiy.service;

import ru.korchinskiy.dto.NewProductDto;
import ru.korchinskiy.dto.ProductDto;
import ru.korchinskiy.dto.ProductWithCategoriesDto;
import ru.korchinskiy.message.Message;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ProductService {
    ProductWithCategoriesDto getProductById(Long id);

    List<ProductDto> getAllProducts();

    List<ProductDto> getProductsByCategory(Long categoryId);

    List<ProductDto> findProductsBySearch(String search);

    Message saveProduct(NewProductDto productDto, HttpServletRequest request);

}
