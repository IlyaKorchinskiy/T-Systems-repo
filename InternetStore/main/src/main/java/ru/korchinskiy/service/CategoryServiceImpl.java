package ru.korchinskiy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.korchinskiy.dao.CategoryDAO;
import ru.korchinskiy.dto.CategoryDto;
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
    public Set<CategoryDto> getMainCategories() {
        Set<Category> categories = new HashSet<>(categoryDAO.getMainCategories());
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
