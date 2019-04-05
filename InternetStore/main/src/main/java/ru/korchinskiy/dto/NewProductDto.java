package ru.korchinskiy.dto;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;
import ru.korchinskiy.validation.File;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class NewProductDto {
    private Long id;

    @Size(min = 2, max = 45, message = "title should be 2-45 characters")
    private String title;

    @Size(min = 5, max = 100, message = "author name should be 5-100 symbols")
    private String author;

    @NotNull(message = "should be not empty")
    private Double cost;

    @NotNull(message = "should be not empty")
    private Integer amount;

    @Size(min = 10, max = 1500, message = "description should be 10-1500 symbols")
    private String description;

    @Min(value = 1, message = "choose category")
    private Long categoryId;

    @File
    private MultipartFile smPhotoFile;

    @File
    private MultipartFile mdPhotoFile;
}
