package ru.korchinskiy.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProductDto {
    private Long id;
    private String title;
    private Double cost;
    private Integer amount;
    private String description;
    private Date date;
    private String photoMd;
    private String photoSm;
}
