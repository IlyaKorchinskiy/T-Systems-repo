package ru.korchinskiy.service;

import ru.korchinskiy.dto.IndicatorDto;
import ru.korchinskiy.dto.ProductStatsDto;
import ru.korchinskiy.dto.UserStatsDto;

import java.util.List;

public interface StatsService {
    List<ProductStatsDto> getTopTenProducts(Integer month, Integer year);

    List<UserStatsDto> getTopTenUsers(Integer month, Integer year);

    List<IndicatorDto> getMainStats(Integer month, Integer year);
}
