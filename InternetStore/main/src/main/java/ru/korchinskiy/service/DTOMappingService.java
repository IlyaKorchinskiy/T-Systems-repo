package ru.korchinskiy.service;

import ru.korchinskiy.dto.*;
import ru.korchinskiy.entity.*;

import java.util.Set;

public interface DTOMappingService {
    ProductDto convertToProductDto(Product product);

    ProductWithCategoriesDto convertToProductWithCategoriesDto(Product product);

    Set<ProductDto> convertToProductDtoSet(Set<Product> products);

    CategoryDto convertToCategoryDto(Category category);

    CategoryWithProductsDto convertToCategoryWithProductsDto(Category category);

    CategoryWithProductsDto convertToCategoryWithProductsDto(Category category, Set<ProductDto> products);

    Set<CategoryDto> convertToCategoryDtoSet(Set<Category> categories);

    UserDto convertToUserDto(User user);

    RoleDto convertToRoleDto(Role role);

    Set<RoleDto> convertToRoleDtoSet(Set<Role> roles);

    CartDto convertToCartDto(Cart cart);

    CartProductDto convertToCartProductDto(CartProduct cartProduct);

    Set<CartProductDto> convertToCartProductDtoSet(Set<CartProduct> cartProducts);
}
