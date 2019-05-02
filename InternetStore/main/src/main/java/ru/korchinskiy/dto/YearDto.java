package ru.korchinskiy.dto;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class YearDto {

    @Min(value = 1900, message = "Year is not correct")
    private Integer year;
}
