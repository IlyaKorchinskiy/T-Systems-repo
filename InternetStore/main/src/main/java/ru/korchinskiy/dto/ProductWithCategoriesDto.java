package ru.korchinskiy.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProductWithCategoriesDto {
    private Long id;
    private String title;
    private String author;
    private String year;
    private Double cost;
    private Integer amount;
    private String description;
    private Date date;
    private String photoMd;
    private String photoSm;
    private List<CategoryDto> categories;
}
