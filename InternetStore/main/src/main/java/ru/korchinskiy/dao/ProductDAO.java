package ru.korchinskiy.dao;

import ru.korchinskiy.entity.Product;

import java.util.List;

public interface ProductDAO {
    Product getProductById(Long id);

    Product getProductForUpdate(Long id);

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(Long categoryId);

    List<Product> getProductsByCategoryAndCost(Long categoryId, Double minCost, Double maxCost);

}
