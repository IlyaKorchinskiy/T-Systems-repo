package ru.korchinskiy.service;

import ru.korchinskiy.dto.CategoryDto;

import java.util.Set;

public interface CategoryService {
    CategoryDto getCategoryById(Long id);
    Set<CategoryDto> getMainCategories();
}
