package ru.korchinskiy.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class DescriptionDto {

    @Size(min = 10, max = 1500, message = "description should be 10-1500 symbols")
    private String description;
}
