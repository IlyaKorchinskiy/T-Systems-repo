package ru.korchinskiy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korchinskiy.dao.CategoryDAO;
import ru.korchinskiy.dto.CategoryDto;
import ru.korchinskiy.dto.CategoryWithProductsDto;
import ru.korchinskiy.entity.Category;

import java.util.HashSet;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryDAO categoryDAO;
    private DTOMappingService dtoMappingService;

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryDAO.getCategoryById(id);
        return dtoMappingService.convertToCategoryDto(category);
    }

    @Override
    public CategoryWithProductsDto getCategoryWithProductsById(Long id) {
        Category category = categoryDAO.getCategoryWithProductsById(id);
        return dtoMappingService.convertToCategoryWithProductsDto(category);
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
    public void setDtoMappingService(DTOMappingService dtoMappingService) {
        this.dtoMappingService = dtoMappingService;
    }
}
