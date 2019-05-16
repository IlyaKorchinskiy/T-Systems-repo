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

    /**
     * Searches products in DB by "search" String. Firstly searches full "search",
     * then splits "search" to array of String words and searches vy every word.
     * Returns list of products
     *
     * @param search String user request
     * @return List<ProductDto> list of ProductDto
     */
    List<ProductDto> findProductsBySearch(String search);

    List<Integer> getProductYears();

    Message saveProduct(NewProductDto productDto);

    Message updateProductTitle(String title, Long productId);

    Message updateProductAuthor(String author, Long productId);

    Message updateProductYear(Integer year, Long productId);

    Message updateProductDescription(String description, Long productId);

    Message updateProductCategories(List<Long> ids, Long productId);

    Message updateProductPhotoMd(MultipartFile photoMd, Long productId);

    Message updateProductPhotoSm(MultipartFile photoSm, Long productId);

}
