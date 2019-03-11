package ru.korchinskiy.service;

import ru.korchinskiy.dto.*;
import ru.korchinskiy.entity.Category;
import ru.korchinskiy.entity.Product;
import ru.korchinskiy.entity.Role;
import ru.korchinskiy.entity.User;

import java.util.Set;

public interface DTOMappingService {
    ProductDto convertToProductDto(Product product);

    Set<ProductDto> convertToProductDtoSet(Set<Product> products);

    CategoryDto convertToCategoryDto(Category category);

    CategoryWithProductsDto convertToCategoryWithProductsDto(Category category);

    Set<CategoryDto> convertToCategoryDtoSet(Set<Category> categories);

    UserDto convertToUserDto(User user);

    RoleDto convertToRoleDto(Role role);

    Set<RoleDto> convertToRoleDtoSet(Set<Role> roles);
}
