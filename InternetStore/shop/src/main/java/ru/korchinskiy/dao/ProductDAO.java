package ru.korchinskiy.dao;

import ru.korchinskiy.entity.Product;

import java.util.List;

public interface ProductDAO {
    Product getProductById(Long id);

    Product getProductByTitle(String title);

    Product getProductForUpdate(Long id);

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(Long categoryId);

    List<Product> getProductsByCategoryAndParams(Long categoryId, Double minCost, Double maxCost, String year);

    List<Product> findProductsBySearch(String search);

    List<Integer> getProductYears();

    void saveProduct(Product product);

}
