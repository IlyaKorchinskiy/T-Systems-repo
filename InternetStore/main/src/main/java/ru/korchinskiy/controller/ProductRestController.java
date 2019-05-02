package ru.korchinskiy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.korchinskiy.dto.ProductStatsDto;
import ru.korchinskiy.service.StatsService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    private StatsService statsService;

    public ProductRestController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/top")
    public List<ProductStatsDto> getProductsTop() {
        List<ProductStatsDto> productStatsDtoList = statsService.getTopTenProducts(null, null);
        return productStatsDtoList;
    }
}
