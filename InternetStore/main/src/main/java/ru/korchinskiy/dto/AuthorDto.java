package ru.korchinskiy.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class AuthorDto {

    @Size(min = 5, max = 100, message = "author name should be 5-100 symbols")
    private String author;
}
