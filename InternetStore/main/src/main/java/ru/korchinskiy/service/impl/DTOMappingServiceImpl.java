package ru.korchinskiy.service.impl;

import org.springframework.stereotype.Service;
import ru.korchinskiy.dto.*;
import ru.korchinskiy.entity.*;
import ru.korchinskiy.service.DTOMappingService;

import java.util.HashSet;
import java.util.Set;

@Service
public class DTOMappingServiceImpl implements DTOMappingService {
    @Override
    public ProductDto convertToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setCost(product.getCost());
        productDto.setTitle(product.getTitle());
        productDto.setAmount(product.getAmount());
        productDto.setDescription(product.getDescription());
        productDto.setPhotoMd(product.getPhotoMd());
        productDto.setPhotoSm(product.getPhotoSm());
        return productDto;
    }

    @Override
    public ProductWithCategoriesDto convertToProductWithCategoriesDto(Product product) {
        ProductWithCategoriesDto productDto = new ProductWithCategoriesDto();
        productDto.setId(product.getId());
        productDto.setCost(product.getCost());
        productDto.setTitle(product.getTitle());
        productDto.setAmount(product.getAmount());
        productDto.setDescription(product.getDescription());
        productDto.setPhotoMd(product.getPhotoMd());
        productDto.setPhotoSm(product.getPhotoSm());
        productDto.setCategories(convertToCategoryDtoSet(product.getCategories()));
        return productDto;
    }

    @Override
    public Set<ProductDto> convertToProductDtoSet(Set<Product> products) {
        Set<ProductDto> productDtos = new HashSet<>();
        for (Product product : products) {
            ProductDto productDto = convertToProductDto(product);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    @Override
    public CategoryDto convertToCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setParentId(category.getParentId());
        return categoryDto;
    }

    @Override
    public CategoryWithProductsDto convertToCategoryWithProductsDto(Category category) {
        CategoryWithProductsDto categoryDto = new CategoryWithProductsDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setParentId(category.getParentId());
        categoryDto.setProducts(convertToProductDtoSet(category.getProducts()));
        return categoryDto;
    }

    @Override
    public CategoryWithProductsDto convertToCategoryWithProductsDto(Category category, Set<ProductDto> products) {
        CategoryWithProductsDto categoryDto = new CategoryWithProductsDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setParentId(category.getParentId());
        categoryDto.setProducts(products);
        return categoryDto;
    }

    @Override
    public Set<CategoryDto> convertToCategoryDtoSet(Set<Category> categories) {
        Set<CategoryDto> categoryDtos = new HashSet<>();
        for (Category category : categories) {
            CategoryDto categoryDto = convertToCategoryDto(category);
            categoryDtos.add(categoryDto);
        }
        return categoryDtos;
    }

    @Override
    public UserDto convertToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setLastname(user.getLastname());
        userDto.setBirthday(user.getBirthday());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setRoles(convertToRoleDtoSet(user.getRoles()));
        return userDto;
    }

    @Override
    public RoleDto convertToRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setRole(role.getRole());
        return roleDto;
    }

    @Override
    public Set<RoleDto> convertToRoleDtoSet(Set<Role> roles) {
        Set<RoleDto> roleDtos = new HashSet<>();
        for (Role role : roles) {
            RoleDto roleDto = convertToRoleDto(role);
            roleDtos.add(roleDto);
        }
        return roleDtos;
    }

    @Override
    public CartDto convertToCartDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setSessionId(cart.getSessionId());
        return cartDto;
    }

    @Override
    public CartProductDto convertToCartProductDto(CartProduct cartProduct) {
        CartProductDto cartProductDto = new CartProductDto();
        cartProductDto.setId(cartProduct.getId());
        cartProductDto.setCart(convertToCartDto(cartProduct.getCart()));
        cartProductDto.setProduct(convertToProductDto(cartProduct.getProduct()));
        cartProductDto.setAmount(cartProduct.getAmount());
        cartProductDto.setSum(cartProductDto.getAmount() * cartProductDto.getProduct().getCost());
        return cartProductDto;
    }

    @Override
    public Set<CartProductDto> convertToCartProductDtoSet(Set<CartProduct> cartProducts) {
        Set<CartProductDto> cartProductDtos = new HashSet<>();
        for (CartProduct cartProduct : cartProducts) {
            cartProductDtos.add(convertToCartProductDto(cartProduct));
        }
        return cartProductDtos;
    }
}
