package ru.korchinskiy.service;

import org.springframework.web.multipart.MultipartFile;
import ru.korchinskiy.dto.NewProductDto;
import ru.korchinskiy.dto.ProductDto;
import ru.korchinskiy.dto.ProductWithCategoriesDto;
import ru.korchinskiy.message.Message;

import java.util.List;

public interface ProductService {
    ProductWithCategoriesDto getProductById(Long id);

    List<ProductDto> getAllProducts();

    List<ProductDto> getProductsByCategory(Long categoryId);

    List<ProductDto> findProductsBySearch(String search);

    Message saveProduct(NewProductDto productDto, MultipartFile smPhoto, MultipartFile mdPhoto);

}
