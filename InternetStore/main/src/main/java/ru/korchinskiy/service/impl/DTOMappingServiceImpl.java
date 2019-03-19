package ru.korchinskiy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.korchinskiy.dto.*;
import ru.korchinskiy.entity.*;
import ru.korchinskiy.service.DTOMappingService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DTOMappingServiceImpl implements DTOMappingService {
    BCryptPasswordEncoder passwordEncoder;

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
    public User convertToUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        user.setBirthday(userDto.getBirthday());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        System.out.println(user.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        return user;
    }

    @Override
    public RoleDto convertToRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setRole(role.getRole());
        return roleDto;
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
    public PaymentTypeDto convertToPaymentTypeDto(PaymentType paymentType) {
        PaymentTypeDto paymentTypeDto = new PaymentTypeDto();
        paymentTypeDto.setId(paymentType.getId());
        paymentTypeDto.setPaymentType(paymentType.getPaymentType());
        return paymentTypeDto;
    }

    @Override
    public DeliveryTypeDto convertToDeliverTypeDto(DeliveryType deliveryType) {
        DeliveryTypeDto deliveryTypeDto = new DeliveryTypeDto();
        deliveryTypeDto.setId(deliveryType.getId());
        deliveryTypeDto.setDeliveryType(deliveryType.getDeliveryType());
        return deliveryTypeDto;
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
    public Set<CategoryDto> convertToCategoryDtoSet(Set<Category> categories) {
        Set<CategoryDto> categoryDtos = new HashSet<>();
        for (Category category : categories) {
            CategoryDto categoryDto = convertToCategoryDto(category);
            categoryDtos.add(categoryDto);
        }
        return categoryDtos;
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
    public Set<CartProductDto> convertToCartProductDtoSet(Set<CartProduct> cartProducts) {
        Set<CartProductDto> cartProductDtos = new HashSet<>();
        for (CartProduct cartProduct : cartProducts) {
            cartProductDtos.add(convertToCartProductDto(cartProduct));
        }
        return cartProductDtos;
    }

    @Override
    public List<PaymentTypeDto> convertToPaymentTypeDtoList(List<PaymentType> paymentTypes) {
        List<PaymentTypeDto> paymentTypeDtos = new ArrayList<>();
        for (PaymentType paymentType : paymentTypes) {
            paymentTypeDtos.add(convertToPaymentTypeDto(paymentType));
        }
        return paymentTypeDtos;
    }

    @Override
    public List<DeliveryTypeDto> convertToDeliveryTypeDtoList(List<DeliveryType> deliveryTypes) {
        List<DeliveryTypeDto> deliveryTypeDtos = new ArrayList<>();
        for (DeliveryType deliveryType : deliveryTypes) {
            deliveryTypeDtos.add(convertToDeliverTypeDto(deliveryType));
        }
        return deliveryTypeDtos;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
