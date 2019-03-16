package ru.korchinskiy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korchinskiy.dao.CategoryDAO;
import ru.korchinskiy.dao.ProductDAO;
import ru.korchinskiy.dto.CategoryDto;
import ru.korchinskiy.dto.CategoryWithProductsDto;
import ru.korchinskiy.dto.ProductDto;
import ru.korchinskiy.entity.Category;
import ru.korchinskiy.entity.Product;
import ru.korchinskiy.service.CategoryService;
import ru.korchinskiy.service.DTOMappingService;

import java.util.HashSet;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryDAO categoryDAO;
    private ProductDAO productDAO;
    private DTOMappingService dtoMappingService;

    @Override
    @Transactional
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryDAO.getCategoryById(id);
        return dtoMappingService.convertToCategoryDto(category);
    }

    @Override
    @Transactional
    public CategoryWithProductsDto getCategoryWithProductsByCost(Long id, Double minCost, Double maxCost) {
        Category category = categoryDAO.getCategoryById(id);
        if (minCost == null) minCost = 0.0;
        if (maxCost == null) maxCost = Double.MAX_VALUE;
        Set<Product> products = new HashSet<>(productDAO.getProductsByCategoryAndCost(id, minCost, maxCost));
        Set<ProductDto> productDtos = dtoMappingService.convertToProductDtoSet(products);
        return dtoMappingService.convertToCategoryWithProductsDto(category, productDtos);
    }

    @Override
    @Transactional
    public Set<CategoryDto> getCategoriesByParentId(Long id) {
        Set<Category> categories = new HashSet<>(categoryDAO.getCategoriesByParentId(id));
        if (categories.size() == 0) {
            Category category = categoryDAO.getCategoryById(id);
            if (category.getParentId() != 0)
                categories.addAll(categoryDAO.getCategoriesByParentId(category.getParentId()));
        }
        return dtoMappingService.convertToCategoryDtoSet(categories);
    }

    @Autowired
    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
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
