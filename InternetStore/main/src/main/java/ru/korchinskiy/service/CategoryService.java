package ru.korchinskiy.service;

import ru.korchinskiy.dto.CategoryDto;
import ru.korchinskiy.dto.CategoryWithProductsDto;

import java.util.Set;

public interface CategoryService {
    CategoryDto getCategoryById(Long id);
    CategoryWithProductsDto getCategoryWithProductsById(Long id);

    Set<CategoryDto> getCategoriesByParentId(Long id);

}
