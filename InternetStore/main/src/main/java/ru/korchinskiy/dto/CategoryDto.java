package ru.korchinskiy.dto;

import lombok.Data;

@Data
public class CategoryDto {
    private Long id;
    private String title;
    private Long parentId;

}
