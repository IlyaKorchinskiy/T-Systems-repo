package ru.korchinskiy.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String title;
    private String author;
    private Double cost;
    private Integer amount;
    private String description;
    private String date;
    private String photoMd;
    private String photoSm;
}
