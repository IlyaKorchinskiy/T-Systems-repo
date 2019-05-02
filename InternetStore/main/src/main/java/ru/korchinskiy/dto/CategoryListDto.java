package ru.korchinskiy.dto;

import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CategoryListDto {

    @Size(min = 1, message = "choose categories")
    private List<Long> categories;
}
