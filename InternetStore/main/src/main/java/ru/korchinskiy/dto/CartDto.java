package ru.korchinskiy.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CartDto {
    private Long id;
    private String sessionId;
}
