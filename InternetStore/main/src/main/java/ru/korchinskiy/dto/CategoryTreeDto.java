package ru.korchinskiy.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryTreeDto {
    private Long id;
    private String title;
    private Long parentId;
    private List<CategoryTreeDto> subcategories;
}
