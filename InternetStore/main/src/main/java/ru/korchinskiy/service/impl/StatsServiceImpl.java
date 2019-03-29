package ru.korchinskiy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korchinskiy.dao.ProductStatsDAO;
import ru.korchinskiy.dto.ProductStatsDto;
import ru.korchinskiy.entity.ProductStats;
import ru.korchinskiy.service.DTOMappingService;
import ru.korchinskiy.service.StatsService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class StatsServiceImpl implements StatsService {
    private ProductStatsDAO productStatsDAO;
    private DTOMappingService dtoMappingService;

    @Override
    @Transactional
    public List<ProductStatsDto> getTopTenProducts(Integer month, Integer year) {
        if (month == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            month = calendar.get(Calendar.MONTH) + 1;
            year = calendar.get(Calendar.YEAR);
        }
        List<ProductStats> productStatsList = productStatsDAO.getTopTenProductStatsByAmount(month, year);
        return dtoMappingService.convertToProductStatsDtoList(productStatsList);
    }

    @Autowired
    public void setProductStatsDAO(ProductStatsDAO productStatsDAO) {
        this.productStatsDAO = productStatsDAO;
    }

    @Autowired
    public void setDtoMappingService(DTOMappingService dtoMappingService) {
        this.dtoMappingService = dtoMappingService;
    }
}
