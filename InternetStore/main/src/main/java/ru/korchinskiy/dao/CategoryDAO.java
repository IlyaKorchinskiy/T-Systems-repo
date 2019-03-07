package ru.korchinskiy.dao;

import ru.korchinskiy.entity.Category;

import java.util.List;

public interface CategoryDAO {

    Category getCategoryById(Long id);
    Category getCategoryWithProductsById(Long id);

    List<Category> getCategoriesByParentId(Long Id);
}
