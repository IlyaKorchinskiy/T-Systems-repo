package ru.korchinskiy.dto;

import lombok.Data;
import ru.korchinskiy.entity.Category;

import java.util.Set;

@Data
public class ProductDto {
    private Long id;
    private String title;
    private Double cost;
    private Integer amount;
    private Set<Category> categories;
}
