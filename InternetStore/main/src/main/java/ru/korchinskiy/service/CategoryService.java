package ru.korchinskiy.service;

import ru.korchinskiy.dto.CategoryDto;
import ru.korchinskiy.dto.CategoryWithProductsDto;

import java.util.List;

public interface CategoryService {
    CategoryDto getCategoryById(Long id);

    CategoryWithProductsDto getCategoryWithProductsByCost(Long id, Double minCost, Double maxCost);

    List<CategoryDto> getCategoriesByParentId(Long id);

}
