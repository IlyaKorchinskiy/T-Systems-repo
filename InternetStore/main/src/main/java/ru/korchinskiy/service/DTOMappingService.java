package ru.korchinskiy.service;

import ru.korchinskiy.dto.*;
import ru.korchinskiy.entity.*;

import java.util.List;
import java.util.Set;

public interface DTOMappingService {
    ProductDto convertToProductDto(Product product);

    ProductWithCategoriesDto convertToProductWithCategoriesDto(Product product);

    CategoryDto convertToCategoryDto(Category category);

    CategoryWithProductsDto convertToCategoryWithProductsDto(Category category);

    CategoryWithProductsDto convertToCategoryWithProductsDto(Category category, Set<ProductDto> products);

    UserDto convertToUserDto(User user);

    User convertToUser(UserDto userDto);

    RoleDto convertToRoleDto(Role role);

    CartDto convertToCartDto(Cart cart);

    CartProductDto convertToCartProductDto(CartProduct cartProduct);

    PaymentTypeDto convertToPaymentTypeDto(PaymentType paymentType);

    DeliveryTypeDto convertToDeliverTypeDto(DeliveryType deliveryType);

    Set<ProductDto> convertToProductDtoSet(Set<Product> products);

    Set<CategoryDto> convertToCategoryDtoSet(Set<Category> categories);

    Set<RoleDto> convertToRoleDtoSet(Set<Role> roles);

    Set<CartProductDto> convertToCartProductDtoSet(Set<CartProduct> cartProducts);

    List<PaymentTypeDto> convertToPaymentTypeDtoList(List<PaymentType> paymentTypes);

    List<DeliveryTypeDto> convertToDeliveryTypeDtoList(List<DeliveryType> deliveryTypes);
}
