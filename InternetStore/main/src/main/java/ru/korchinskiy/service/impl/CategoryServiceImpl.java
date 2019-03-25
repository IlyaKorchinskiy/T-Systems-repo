package ru.korchinskiy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korchinskiy.dao.CategoryDAO;
import ru.korchinskiy.dao.ProductDAO;
import ru.korchinskiy.dto.CategoryDto;
import ru.korchinskiy.dto.CategoryWithProductsDto;
import ru.korchinskiy.dto.CategoryWithSubcategoriesDto;
import ru.korchinskiy.dto.ProductDto;
import ru.korchinskiy.entity.Category;
import ru.korchinskiy.entity.Product;
import ru.korchinskiy.service.CategoryService;
import ru.korchinskiy.service.DTOMappingService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    public static final Long ROOT_CATEGORY = 0L;

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
        List<Product> products = productDAO.getProductsByCategoryAndCost(id, minCost, maxCost);
        List<ProductDto> productDtos = dtoMappingService.convertToProductDtoList(products);
        return dtoMappingService.convertToCategoryWithProductsDto(category, productDtos);
    }

    @Override
    @Transactional
    public List<CategoryDto> getCategoriesByParentId(Long id) {
        List<Category> categories = categoryDAO.getCategoriesByParentId(id);
        if (categories.size() == 0) {
            Category category = categoryDAO.getCategoryById(id);
            if (category.getParentId() != 0)
                categories.addAll(categoryDAO.getCategoriesByParentId(category.getParentId()));
        }
        return dtoMappingService.convertToCategoryDtoList(categories);
    }

    @Override
    @Transactional
    public List<CategoryWithSubcategoriesDto> getCategoriesWithSubcategories() {
        List<Category> categories = categoryDAO.getAllCategories();
        return dtoMappingService.convertToCategoryWithSubcategoriesDtoList(categories);
    }

    @Override
    @Transactional
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryDAO.getAllCategories();
        return dtoMappingService.convertToCategoryDtoList(categories);
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
