package ru.korchinskiy.dao;

import ru.korchinskiy.entity.Category;

import java.util.List;

public interface CategoryDAO {
    Category getCategoryById(Long id);

    List<Category> getMainCategories();
}
