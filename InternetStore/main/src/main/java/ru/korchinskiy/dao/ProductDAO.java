package ru.korchinskiy.dao;

import ru.korchinskiy.entity.Product;

import java.util.Set;

public interface ProductDAO {
    Product getProductById(Long id);

    Set<Product> getProductsByCategory(Long categoryId);

}
