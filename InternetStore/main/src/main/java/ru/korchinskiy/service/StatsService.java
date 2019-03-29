package ru.korchinskiy.service;

import ru.korchinskiy.dto.ProductStatsDto;

import java.util.List;

public interface StatsService {
    List<ProductStatsDto> getTopTenProducts(Integer month, Integer year);
}
