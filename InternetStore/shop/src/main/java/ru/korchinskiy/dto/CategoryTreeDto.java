package ru.korchinskiy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CategoryTreeDto {
    private Long id;
    private String title;
    private Long parentId;
    private List<CategoryTreeDto> subcategories;

    public CategoryTreeDto(Long id, String title, Long parentId, List<CategoryTreeDto> subcategories) {
        this.id = id;
        this.title = title;
        this.parentId = parentId;
        this.subcategories = subcategories;
    }
}
