package ru.korchinskiy.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class TitleDto {

    @Size(min = 2, max = 60, message = "title should be 2-60 characters")
    private String title;
}
