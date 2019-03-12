package ru.korchinskiy.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ProductWithCategoriesDto {
    private Long id;
    private String title;
    private Double cost;
    private Integer amount;
    private String description;
    private String photoMd;
    private String photoSm;
    Set<CategoryDto> categories;
}
