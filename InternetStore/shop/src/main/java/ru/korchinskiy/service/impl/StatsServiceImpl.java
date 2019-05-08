package ru.korchinskiy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korchinskiy.dao.ProductStatsDAO;
import ru.korchinskiy.dao.UserStatsDAO;
import ru.korchinskiy.dto.IndicatorDto;
import ru.korchinskiy.dto.ProductStatsDto;
import ru.korchinskiy.dto.UserStatsDto;
import ru.korchinskiy.entity.ProductStats;
import ru.korchinskiy.entity.UserStats;
import ru.korchinskiy.service.DTOMappingService;
import ru.korchinskiy.service.StatsService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class StatsServiceImpl implements StatsService {
    private ProductStatsDAO productStatsDAO;
    private UserStatsDAO userStatsDAO;
    private DTOMappingService dtoMappingService;

    @Override
    @Transactional
    public List<ProductStatsDto> getTopTenProducts(Integer month, Integer year) {
        if (month == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);
        }
        List<ProductStats> productStatsList = productStatsDAO.getTopTenProductStatsByAmount(month + 1, year);
        return dtoMappingService.convertToProductStatsDtoList(productStatsList);
    }

    @Override
    @Transactional
    public List<UserStatsDto> getTopTenUsers(Integer month, Integer year) {
        if (month == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);
        }
        List<UserStats> userStatsList = userStatsDAO.getTopTenUserStatsBySum(month + 1, year);
        return dtoMappingService.convertToUserStatsDtoList(userStatsList);
    }

    @Override
    @Transactional
    public List<IndicatorDto> getMainStats(Integer month, Integer year) {
        if (month == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);
        }
        List<IndicatorDto> indicators = new ArrayList<>();
        IndicatorDto revenue = new IndicatorDto();
        revenue.setName("Revenue");
        revenue.setValue(userStatsDAO.getRevenueByMonth(month + 1, year));
        indicators.add(revenue);
        return indicators;
    }

    @Autowired
    public void setProductStatsDAO(ProductStatsDAO productStatsDAO) {
        this.productStatsDAO = productStatsDAO;
    }

    @Autowired
    public void setUserStatsDAO(UserStatsDAO userStatsDAO) {
        this.userStatsDAO = userStatsDAO;
    }

    @Autowired
    public void setDtoMappingService(DTOMappingService dtoMappingService) {
        this.dtoMappingService = dtoMappingService;
    }
}
