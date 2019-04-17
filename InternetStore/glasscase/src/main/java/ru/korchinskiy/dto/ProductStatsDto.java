package ru.korchinskiy.dto;

import lombok.Data;

@Data
public class ProductStatsDto {
    private Long id;
    private ProductDto product;
    private Integer amount;
    private Integer month;
    private Integer year;
}
