package ru.korchinskiy.bean;

import ru.korchinskiy.dto.ProductStatsDto;

import java.util.List;

public interface ProductEJB {

    List<ProductStatsDto> getProductsTopList();
}
