package ru.korchinskiy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.korchinskiy.dao.ProductDAO;
import ru.korchinskiy.dto.ProductDto;
import ru.korchinskiy.entity.Product;

import java.util.Set;
import java.util.stream.Collectors;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    private ProductDAO productDAO;
    private DTOMappingService dtoMappingService;

    @Override
    public ProductDto getProductById(Long id) {
        return null;
    }

    @Override
    public Set<ProductDto> getProductsByCategory(Long categoryId) {
        Set<Product> products = productDAO.getProductsByCategory(categoryId);
        return dtoMappingService.convertToProductDtoSet(products);
    }

    @Override
    public Set<ProductDto> getProductsByCategoryAndCost(Long categoryId, Double minCost, Double maxCost) {
        Set<Product> allProducts = productDAO.getProductsByCategory(categoryId);
        if (minCost == null) minCost = 0.0;
        if (maxCost == null) maxCost = Double.MAX_VALUE;
        Double finalMinCost = minCost;
        Double finalMaxCost = maxCost;
        Set<Product> products = allProducts.stream()
                .filter(a -> a.getCost() >= finalMinCost && a.getCost() <= finalMaxCost)
                .collect(Collectors.toSet());
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