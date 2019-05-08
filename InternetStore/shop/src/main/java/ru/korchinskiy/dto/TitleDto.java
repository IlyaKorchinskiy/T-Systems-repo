package ru.korchinskiy.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class TitleDto {

    @Size(min = 2, max = 45, message = "title should be 2-45 characters")
    private String title;
}
