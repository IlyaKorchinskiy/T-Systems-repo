package ru.korchinskiy.dto;

import lombok.Data;
import ru.korchinskiy.entity.Product;

import java.util.HashSet;
import java.util.Set;

@Data
public class CategoryDto {
    private Long id;
    private String title;
    private Long parentId;
    private Set<Product> products = new HashSet<>();

}
