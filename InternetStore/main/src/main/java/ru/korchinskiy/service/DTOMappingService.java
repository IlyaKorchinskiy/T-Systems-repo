package ru.korchinskiy.service;

import ru.korchinskiy.dto.*;
import ru.korchinskiy.entity.*;

import java.util.List;

public interface DTOMappingService {
    ProductDto convertToProductDto(Product product);

    ProductWithCategoriesDto convertToProductWithCategoriesDto(Product product);

    List<ProductDto> convertToProductDtoList(List<Product> products);

    CategoryDto convertToCategoryDto(Category category);

    CategoryWithProductsDto convertToCategoryWithProductsDto(Category category);

    CategoryWithProductsDto convertToCategoryWithProductsDto(Category category, List<ProductDto> products);

    List<CategoryDto> convertToCategoryDtoList(List<Category> categories);

    UserDto convertToUserDto(User user);

    User convertToUser(UserDto userDto);

    AddressDto convertToAddressDto(Address address);

    List<AddressDto> convertToAddressDtoList(List<Address> addresses);

    RoleDto convertToRoleDto(Role role);

    List<RoleDto> convertToRoleDtoList(List<Role> roles);

    CartDto convertToCartDto(Cart cart);

    CartProductDto convertToCartProductDto(CartProduct cartProduct);

    List<CartProductDto> convertToCartProductDtoList(List<CartProduct> cartProducts);

    PaymentTypeDto convertToPaymentTypeDto(PaymentType paymentType);

    List<PaymentTypeDto> convertToPaymentTypeDtoList(List<PaymentType> paymentTypes);

    DeliveryTypeDto convertToDeliverTypeDto(DeliveryType deliveryType);

    List<DeliveryTypeDto> convertToDeliveryTypeDtoList(List<DeliveryType> deliveryTypes);

    OrderDto convertToOrderDto(Order order);

    List<OrderDto> convertToOrderDtoList(List<Order> orders);

}
