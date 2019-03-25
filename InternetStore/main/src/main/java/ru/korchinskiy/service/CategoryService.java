package ru.korchinskiy.service;

import ru.korchinskiy.dto.CategoryDto;
import ru.korchinskiy.dto.CategoryWithProductsDto;
import ru.korchinskiy.dto.CategoryWithSubcategoriesDto;

import java.util.List;

public interface CategoryService {
    CategoryDto getCategoryById(Long id);

    CategoryWithProductsDto getCategoryWithProductsByCost(Long id, Double minCost, Double maxCost);

    List<CategoryDto> getCategoriesByParentId(Long id);

    List<CategoryWithSubcategoriesDto> getCategoriesWithSubcategories();

    List<CategoryDto> getAllCategories();

}
