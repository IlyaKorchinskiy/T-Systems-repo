package ru.korchinskiy.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryWithProductsDto {
    private Long id;
    private String title;
    private Long parentId;
    private List<ProductDto> products;
}
