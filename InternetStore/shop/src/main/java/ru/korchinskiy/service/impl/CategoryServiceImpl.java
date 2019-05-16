package ru.korchinskiy.service.impl;

import org.apache.log4j.Logger;
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
    private static Logger logger = Logger.getLogger(CategoryServiceImpl.class);
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
    public CategoryWithProductsDto getCategoryWithProductsByParams(Long id, Double minCost, Double maxCost, String year) {
        Category category = categoryDAO.getCategoryById(id);
        if (minCost == null) minCost = 0.0;
        if (maxCost == null) maxCost = Double.MAX_VALUE;
        if (year == null || year.equals("...")) year = "____";
        List<Product> products = productDAO.getProductsByCategoryAndParams(id, minCost, maxCost, year);
        List<ProductDto> productDtos = dtoMappingService.convertToProductDtoList(products);
        return dtoMappingService.convertToCategoryWithProductsDto(category, productDtos);
    }

    @Override
    @Transactional
    public List<CategoryDto> getCategoriesByParentId(Long id) {
        List<Category> categories = categoryDAO.getCategoriesByParentId(id);
        if (categories.size() == 0) {
            Category category = categoryDAO.getCategoryById(id);
            if (!category.getParentId().equals(ROOT_CATEGORY))
                categories.addAll(categoryDAO.getCategoriesByParentId(category.getParentId()));
        }
        return dtoMappingService.convertToCategoryDtoList(categories);
    }

    @Override
    @Transactional
    public List<CategoryTreeDto> getCategoryTree() {
        List<Category> categories = categoryDAO.getAllCategories();
        List<CategoryTreeDto> categoryTreeDtoList = dtoMappingService.convertToCategoryTreeDtoList(categories);
        return buildCategoryTree(categoryTreeDtoList);
    }

    List<CategoryTreeDto> buildCategoryTree(List<CategoryTreeDto> categoryDtos) {
        for (int i = 0; i < categoryDtos.size(); i++) {
            if (!categoryDtos.get(i).getParentId().equals(ROOT_CATEGORY)) {
                for (int j = 0; j < categoryDtos.size(); j++) {
                    if (categoryDtos.get(i).getParentId().equals(categoryDtos.get(j).getId())) {
                        categoryDtos.get(j).getSubcategories().add(categoryDtos.get(i));
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < categoryDtos.size(); i++) {
            if (!categoryDtos.get(i).getParentId().equals(ROOT_CATEGORY)) categoryDtos.remove(i--);
        }
        return categoryDtos;
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
        message.getConfirms().add(Message.CATEGORY_UPDATE_SUCCESS);
        logger.info(Message.CATEGORY_UPDATE_SUCCESS);
        return message;
    }

    @Override
    @Transactional
    public Message saveCategory(CategoryDto categoryDto) {
        Message message = new Message();
        Category category = categoryDAO.getCategoryByTitle(categoryDto.getTitle());
        if (category != null) {
            message.getErrors().add(Message.CATEGORY_ALREADY_EXISTS);
            logger.info(Message.CATEGORY_ALREADY_EXISTS);
            return message;
        }
        category = dtoMappingService.convertToCategory(categoryDto);
        categoryDAO.saveCategory(category);
        message.getConfirms().add(Message.CATEGORY_ADD_SUCCESS);
        logger.info(Message.CATEGORY_ADD_SUCCESS);
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
        if (category.getProducts().size() != 0) {
            message.getErrors().add(Message.CATEGORY_DELETE_FAIL);
            logger.info(Message.CATEGORY_DELETE_FAIL);
            return message;
        }
        categoryDAO.removeCategory(category);
        message.getConfirms().add(Message.CATEGORY_DELETE_SUCCESS);
        logger.info(Message.CATEGORY_DELETE_SUCCESS);
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
