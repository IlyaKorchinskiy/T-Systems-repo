package ru.korchinskiy.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CategoryWithProductsDto {
    private Long id;
    private String title;
    private Long parentId;
    private Set<ProductDto> products;
}
