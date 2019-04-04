package ru.korchinskiy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDto {
    private Long id;
    private String sessionId;

    public CartDto(Long id, String sessionId) {
        this.id = id;
        this.sessionId = sessionId;
    }
}
