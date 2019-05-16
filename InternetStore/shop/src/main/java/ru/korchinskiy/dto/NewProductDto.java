package ru.korchinskiy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ru.korchinskiy.validation.File;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class NewProductDto {
    private Long id;

    @Size(min = 2, max = 60, message = "title should be 2-60 characters")
    private String title;

    @Size(min = 5, max = 100, message = "author name should be 5-100 symbols")
    private String author;

    @Min(value = 1900, message = "Year is not correct")
    private Integer year;

    @NotNull(message = "should not be empty")
    @Min(value = 1, message = "price can't be negative")
    private Double cost;

    @NotNull(message = "should not be empty")
    @Min(value = 0, message = "amount can't be negative")
    private Integer amount;

    @Size(min = 10, max = 1500, message = "description should be 10-1500 symbols")
    private String description;

    @File
    private MultipartFile photoMd;

    @File
    private MultipartFile photoSm;

    @Size(min = 1, message = "choose categories")
    private List<Long> categories = new ArrayList<>();

    public NewProductDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
