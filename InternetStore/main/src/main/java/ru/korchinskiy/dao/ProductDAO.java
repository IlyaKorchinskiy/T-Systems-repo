package ru.korchinskiy.dao;

import ru.korchinskiy.entity.Product;

import java.util.List;
import java.util.Set;

public interface ProductDAO {
    Product getProductById(Long id);

    Product getProductForUpdate(Long id);

    Set<Product> getProductsByCategory(Long categoryId);

    List<Product> getProductsByCategoryAndCost(Long categoryId, Double minCost, Double maxCost);

}
