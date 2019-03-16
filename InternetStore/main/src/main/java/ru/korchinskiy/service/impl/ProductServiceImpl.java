package ru.korchinskiy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korchinskiy.dao.ProductDAO;
import ru.korchinskiy.dto.ProductDto;
import ru.korchinskiy.dto.ProductWithCategoriesDto;
import ru.korchinskiy.entity.Product;
import ru.korchinskiy.service.DTOMappingService;
import ru.korchinskiy.service.ProductService;

import java.util.Set;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    private ProductDAO productDAO;
    private DTOMappingService dtoMappingService;

    @Override
    @Transactional
    public ProductWithCategoriesDto getProductById(Long id) {
        Product product = productDAO.getProductById(id);
        return dtoMappingService.convertToProductWithCategoriesDto(product);
    }

    @Override
    @Transactional
    public Set<ProductDto> getProductsByCategory(Long categoryId) {
        Set<Product> products = productDAO.getProductsByCategory(categoryId);
        return dtoMappingService.convertToProductDtoSet(products);
    }

    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Autowired
    public void setDtoMappingService(DTOMappingService dtoMappingService) {
        this.dtoMappingService = dtoMappingService;
    }
}
