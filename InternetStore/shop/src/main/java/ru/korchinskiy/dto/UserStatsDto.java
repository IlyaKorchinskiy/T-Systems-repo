package ru.korchinskiy.dto;

import lombok.Data;

@Data
public class UserStatsDto {
    private Long id;
    private UserDto user;
    private Double sum;
    private Integer month;
    private Integer year;
}
