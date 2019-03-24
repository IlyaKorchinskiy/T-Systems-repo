package ru.korchinskiy.service.impl;

import org.springframework.stereotype.Service;
import ru.korchinskiy.dto.*;
import ru.korchinskiy.entity.*;
import ru.korchinskiy.service.DTOMappingService;

import java.util.ArrayList;
import java.util.List;

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
        productDto.setDate(product.getDate());
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
        productDto.setDate(product.getDate());
        productDto.setPhotoMd(product.getPhotoMd());
        productDto.setPhotoSm(product.getPhotoSm());
        productDto.setCategories(convertToCategoryDtoList(product.getCategories()));
        return productDto;
    }

    @Override
    public List<ProductDto> convertToProductDtoList(List<Product> products) {
        List<ProductDto> productDtos = new ArrayList<>();
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
        categoryDto.setProducts(convertToProductDtoList(category.getProducts()));
        return categoryDto;
    }

    @Override
    public CategoryWithProductsDto convertToCategoryWithProductsDto(Category category, List<ProductDto> products) {
        CategoryWithProductsDto categoryDto = new CategoryWithProductsDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setParentId(category.getParentId());
        categoryDto.setProducts(products);
        return categoryDto;
    }

    @Override
    public List<CategoryDto> convertToCategoryDtoList(List<Category> categories) {
        List<CategoryDto> categoryDtos = new ArrayList<>();
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
        userDto.setAddresses(convertToAddressDtoList(user.getAddresses()));
        userDto.setRoles(convertToRoleDtoList(user.getRoles()));
        return userDto;
    }

    @Override
    public User convertToUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        user.setBirthday(userDto.getBirthday());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        return user;
    }

    @Override
    public AddressDto convertToAddressDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setAddress(address.getAddress());
        addressDto.setAddressType(address.getAddressType());
        return addressDto;
    }

    @Override
    public List<AddressDto> convertToAddressDtoList(List<Address> addresses) {
        List<AddressDto> addressDtos = new ArrayList<>();
        for (Address address : addresses) {
            addressDtos.add(convertToAddressDto(address));
        }
        return addressDtos;
    }

    @Override
    public RoleDto convertToRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setRole(role.getRole());
        return roleDto;
    }

    @Override
    public List<RoleDto> convertToRoleDtoList(List<Role> roles) {
        List<RoleDto> roleDtos = new ArrayList<>();
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
    public List<CartProductDto> convertToCartProductDtoList(List<CartProduct> cartProducts) {
        List<CartProductDto> cartProductDtos = new ArrayList<>();
        for (CartProduct cartProduct : cartProducts) {
            cartProductDtos.add(convertToCartProductDto(cartProduct));
        }
        return cartProductDtos;
    }

    @Override
    public PaymentTypeDto convertToPaymentTypeDto(PaymentType paymentType) {
        PaymentTypeDto paymentTypeDto = new PaymentTypeDto();
        paymentTypeDto.setId(paymentType.getId());
        paymentTypeDto.setPaymentType(paymentType.getPaymentType());
        return paymentTypeDto;
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
    public DeliveryTypeDto convertToDeliverTypeDto(DeliveryType deliveryType) {
        DeliveryTypeDto deliveryTypeDto = new DeliveryTypeDto();
        deliveryTypeDto.setId(deliveryType.getId());
        deliveryTypeDto.setDeliveryType(deliveryType.getDeliveryType());
        return deliveryTypeDto;
    }

    @Override
    public List<DeliveryTypeDto> convertToDeliveryTypeDtoList(List<DeliveryType> deliveryTypes) {
        List<DeliveryTypeDto> deliveryTypeDtos = new ArrayList<>();
        for (DeliveryType deliveryType : deliveryTypes) {
            deliveryTypeDtos.add(convertToDeliverTypeDto(deliveryType));
        }
        return deliveryTypeDtos;
    }

    @Override
    public OrderDto convertToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUser(order.getUser());
        orderDto.setPaymentType(order.getPaymentType());
        orderDto.setDeliveryType(order.getDeliveryType());
        orderDto.setPaymentStatus(order.getPaymentStatus());
        orderDto.setOrderStatus(order.getOrderStatus());
        orderDto.setAddress(order.getAddress());
        orderDto.setSum(order.getSum());
        orderDto.setDate(order.getDate());
        return orderDto;
    }

    @Override
    public List<OrderDto> convertToOrderDtoList(List<Order> orders) {
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orders) {
            orderDtos.add(convertToOrderDto(order));
        }
        return orderDtos;
    }

}
