package ru.korchinskiy.service;

import ru.korchinskiy.dto.*;
import ru.korchinskiy.entity.*;

import java.util.List;

public interface DTOMappingService {
    ProductDto convertToProductDto(Product product);

    Product convertToProduct(NewProductDto productDto);

    ProductWithCategoriesDto convertToProductWithCategoriesDto(Product product);

    List<ProductDto> convertToProductDtoList(List<Product> products);

    CategoryDto convertToCategoryDto(Category category);

    Category convertToCategory(CategoryDto categoryDto);

    CategoryWithProductsDto convertToCategoryWithProductsDto(Category category);

    CategoryWithProductsDto convertToCategoryWithProductsDto(Category category, List<ProductDto> products);

    List<CategoryDto> convertToCategoryDtoList(List<Category> categories);

    CategoryTreeDto convertToCategoryTreeDto(Category category);

    List<CategoryTreeDto> convertToCategoryTreeDtoList(List<Category> categories);

    UserDto convertToUserDto(User user);

    User convertToUser(UserDto userDto);

    AddressDto convertToAddressDto(Address address);

    List<AddressDto> convertToAddressDtoList(List<Address> addresses);

    RoleDto convertToRoleDto(Role role);

    List<RoleDto> convertToRoleDtoList(List<Role> roles);

    CartDto convertToCartDto(Cart cart);

    CartProductDto convertToCartProductDto(CartProduct cartProduct);

    List<CartProductDto> convertToCartProductDtoList(List<CartProduct> cartProducts);

    OrderDto convertToOrderDto(Order order);

    List<OrderDto> convertToOrderDtoList(List<Order> orders);

    OrderProductDto convertToOrderProductDto(OrderProduct orderProduct);

    List<OrderProductDto> convertToOrderProductDtoList(List<OrderProduct> orderProducts);

    OrderHistoryDto convertToOrderHistoryDto(OrderHistory orderHistory);

    List<OrderHistoryDto> convertToOrderHistoryDtoList(List<OrderHistory> orderHistories);

    ProductStatsDto convertToProductStatsDto(ProductStats productStats);

    List<ProductStatsDto> convertToProductStatsDtoList(List<ProductStats> productStatsList);

    UserStatsDto convertToUserStatsDto(UserStats userStats);

    List<UserStatsDto> convertToUserStatsDtoList(List<UserStats> userStatsList);

}
