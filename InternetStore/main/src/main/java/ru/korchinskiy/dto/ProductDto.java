package ru.korchinskiy.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ProductDto {
    private Long id;
    private String title;
    private Double cost;
    private Integer amount;
    private String description;
    private String photoMd;
    private String photoSm;
    private Set<CategoryDto> categories;
}
