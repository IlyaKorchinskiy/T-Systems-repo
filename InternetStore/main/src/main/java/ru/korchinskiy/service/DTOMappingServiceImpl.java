package ru.korchinskiy.service;

import org.springframework.stereotype.Service;
import ru.korchinskiy.dto.*;
import ru.korchinskiy.entity.Category;
import ru.korchinskiy.entity.Product;
import ru.korchinskiy.entity.Role;
import ru.korchinskiy.entity.User;

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
    public Set<ProductDto> convertToProductDtoSet(Set<Product> products) {
        Set<ProductDto> productDtos = new HashSet<>();
        for (Product product : products) {
            ProductDto productDto = convertToProductDto(product);
            productDtos.add(productDto);
        }
        return productDtos;
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
    public CategoryDto convertToCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setParentId(category.getParentId());
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
}
