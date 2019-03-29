package ru.korchinskiy.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NewProductDto {
    private Long id;
    private String title;
    private String author;
    private Double cost;
    private Integer amount;
    private String description;
    private Date date;
    private Long categoryId;
}
