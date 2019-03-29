package ru.korchinskiy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korchinskiy.dao.CategoryDAO;
import ru.korchinskiy.dao.ProductDAO;
import ru.korchinskiy.dto.CategoryDto;
import ru.korchinskiy.dto.CategoryTreeDto;
import ru.korchinskiy.dto.CategoryWithProductsDto;
import ru.korchinskiy.dto.ProductDto;
import ru.korchinskiy.entity.Category;
import ru.korchinskiy.entity.Product;
import ru.korchinskiy.message.Message;
import ru.korchinskiy.service.CategoryService;
import ru.korchinskiy.service.DTOMappingService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    public static final Long ROOT_CATEGORY = 0L;
    private static final String CATEGORY_UPDATE_SUCCESS = "Category successfully updated";
    private static final String CATEGORY_ALREADY_EXISTS = "Category with the name already exists";
    private static final String CATEGORY_ADD_SUCCESS = "Category successfully added";
    private static final String CATEGORY_DELETE_SUCCESS = "Category successfully removed";

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
    public List<CategoryTreeDto> getCategoriesWithSubcategories() {
        List<Category> categories = categoryDAO.getAllCategories();
        return dtoMappingService.convertToCategoryWithSubcategoriesDtoList(categories);
    }

    @Override
    @Transactional
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryDAO.getAllCategories();
        return dtoMappingService.convertToCategoryDtoList(categories);
    }

    @Override
    @Transactional
    public Message updateCategory(CategoryDto categoryDto) {
        Category category = categoryDAO.getCategoryById(categoryDto.getId());
        category.setTitle(categoryDto.getTitle());
        category.setParentId(categoryDto.getParentId());
        Message message = new Message();
        message.getConfirms().add(CATEGORY_UPDATE_SUCCESS);
        return message;
    }

    @Override
    @Transactional
    public Message saveCategory(CategoryDto categoryDto) {
        Message message = new Message();
        Category category = categoryDAO.getCategoryByTitle(categoryDto.getTitle());
        if (category != null) {
            message.getErrors().add(CATEGORY_ALREADY_EXISTS);
            return message;
        }
        category = dtoMappingService.convertToCategory(categoryDto);
        categoryDAO.saveCategory(category);
        message.getConfirms().add(CATEGORY_ADD_SUCCESS);
        return message;
    }

    @Override
    @Transactional
    public Message removeCategory(Long categoryId) {
        Category category = categoryDAO.getCategoryById(categoryId);
        return removeCategory(category);
    }

    private Message removeCategory(Category category) {
        Message message = new Message();
        categoryDAO.removeCategory(category);
        message.getConfirms().add(CATEGORY_DELETE_SUCCESS);
        return message;
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
