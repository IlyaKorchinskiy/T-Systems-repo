package ru.korchinskiy.dao;

import ru.korchinskiy.entity.ProductStats;

import java.util.Calendar;
import java.util.List;

public interface ProductStatsDAO {
    ProductStats getProductStatsByProductIdAndDate(Long id, Calendar calendar);

    List<ProductStats> getTopTenProductStatsByAmount(Integer month, Integer year);

    void saveProductStats(ProductStats productStats);
}
