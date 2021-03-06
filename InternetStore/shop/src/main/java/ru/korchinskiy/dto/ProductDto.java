package ru.korchinskiy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private String author;
    private String year;
    private Double cost;
    private Integer amount;
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
    private String photoMd;
    private String photoSm;

    public ProductDto(Long id) {
        this.id = id;
    }
}
