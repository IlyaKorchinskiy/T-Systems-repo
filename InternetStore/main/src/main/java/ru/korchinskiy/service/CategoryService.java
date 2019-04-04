package ru.korchinskiy.service;

import ru.korchinskiy.dto.CategoryDto;
import ru.korchinskiy.dto.CategoryTreeDto;
import ru.korchinskiy.dto.CategoryWithProductsDto;
import ru.korchinskiy.message.Message;

import java.util.List;

public interface CategoryService {
    CategoryDto getCategoryById(Long id);

    CategoryWithProductsDto getCategoryWithProductsByCost(Long id, Double minCost, Double maxCost);

    List<CategoryDto> getCategoriesByParentId(Long id);

    List<CategoryTreeDto> getCategoryTree();

    List<CategoryDto> getAllCategories();

    Message updateCategory(CategoryDto categoryDto);

    Message saveCategory(CategoryDto categoryDto);

    Message removeCategory(Long categoryId);

    List<CategoryTreeDto> buildCategoryTree(List<CategoryTreeDto> categoryTreeDtoList);
}
