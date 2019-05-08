package ru.korchinskiy.dao;

import ru.korchinskiy.entity.Category;

import java.util.List;

public interface CategoryDAO {

    Category getCategoryById(Long id);

    Category getCategoryByTitle(String title);

    List<Category> getCategoriesByParentId(Long Id);

    List<Category> getAllCategories();

    void saveCategory(Category category);

    void removeCategory(Category category);
}
