package ru.korchinskiy.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korchinskiy.dto.*;
import ru.korchinskiy.entity.*;
import ru.korchinskiy.service.DTOMappingService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DTOMappingServiceImpl implements DTOMappingService {

    @Override
    public ProductDto convertToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setCost(product.getCost());
        productDto.setTitle(product.getTitle());
        productDto.setAuthor(product.getAuthor());
        productDto.setAmount(product.getAmount());
        productDto.setDescription(product.getDescription());
        productDto.setDate(product.getDate());
        productDto.setPhotoMd(product.getPhotoMd());
        productDto.setPhotoSm(product.getPhotoSm());
        return productDto;
    }

    @Override
    public Product convertToProduct(NewProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setAuthor(productDto.getAuthor());
        product.setDescription(productDto.getDescription());
        product.setCost(productDto.getCost());
        product.setAmount(productDto.getAmount());
        product.setDate(new Date());
        return product;
    }

    @Override
    public ProductWithCategoriesDto convertToProductWithCategoriesDto(Product product) {
        ProductWithCategoriesDto productDto = new ProductWithCategoriesDto();
        productDto.setId(product.getId());
        productDto.setCost(product.getCost());
        productDto.setTitle(product.getTitle());
        productDto.setAuthor(product.getAuthor());
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
    public Category convertToCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setTitle(categoryDto.getTitle());
        category.setParentId(categoryDto.getParentId());
        return category;
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
    public CategoryTreeDto convertToCategoryTreeDto(Category category) {
        CategoryTreeDto categoryDto = new CategoryTreeDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setParentId(category.getParentId());
        categoryDto.setSubcategories(new ArrayList<>());
        return categoryDto;
    }

    @Override
    public List<CategoryTreeDto> convertToCategoryTreeDtoList(List<Category> categories) {
        List<CategoryTreeDto> categoryDtos = new ArrayList<>();
        for (Category category : categories) {
            CategoryTreeDto categoryDto = convertToCategoryTreeDto(category);
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
        cartDto.setCartProducts(convertToCartProductDtoList(cart.getCartProducts()));
        return cartDto;
    }

    @Override
    public CartProductDto convertToCartProductDto(CartProduct cartProduct) {
        CartProductDto cartProductDto = new CartProductDto();
        cartProductDto.setId(cartProduct.getId());
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
    public OrderDto convertToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUser(convertToUserDto(order.getUser()));
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

    @Override
    public OrderProductDto convertToOrderProductDto(OrderProduct orderProduct) {
        OrderProductDto orderProductDto = new OrderProductDto();
        orderProductDto.setId(orderProduct.getId());
        orderProductDto.setProduct(convertToProductDto(orderProduct.getProduct()));
        orderProductDto.setAmount(orderProduct.getAmount());
        orderProductDto.setCost(orderProduct.getCost());
        return orderProductDto;
    }

    @Override
    public List<OrderProductDto> convertToOrderProductDtoList(List<OrderProduct> orderProducts) {
        List<OrderProductDto> orderProductDtos = new ArrayList<>();
        for (OrderProduct orderProduct : orderProducts) {
            orderProductDtos.add(convertToOrderProductDto(orderProduct));
        }
        return orderProductDtos;
    }

    @Override
    public OrderHistoryDto convertToOrderHistoryDto(OrderHistory orderHistory) {
        OrderHistoryDto orderHistoryDto = new OrderHistoryDto();
        orderHistoryDto.setId(orderHistory.getId());
        orderHistoryDto.setPaymentType(orderHistory.getPaymentType());
        orderHistoryDto.setDeliveryType(orderHistory.getDeliveryType());
        orderHistoryDto.setPaymentStatus(orderHistory.getPaymentStatus());
        orderHistoryDto.setOrderStatus(orderHistory.getOrderStatus());
        orderHistoryDto.setAddress(orderHistory.getAddress());
        orderHistoryDto.setSum(orderHistory.getSum());
        orderHistoryDto.setDate(orderHistory.getDate());
        return orderHistoryDto;
    }

    @Override
    public List<OrderHistoryDto> convertToOrderHistoryDtoList(List<OrderHistory> orderHistories) {
        List<OrderHistoryDto> orderHistoryDtos = new ArrayList<>();
        for (OrderHistory orderHistory : orderHistories) {
            orderHistoryDtos.add(convertToOrderHistoryDto(orderHistory));
        }
        return orderHistoryDtos;
    }

    @Override
    public ProductStatsDto convertToProductStatsDto(ProductStats productStats) {
        ProductStatsDto productStatsDto = new ProductStatsDto();
        productStatsDto.setId(productStats.getId());
        productStatsDto.setProduct(convertToProductDto(productStats.getProduct()));
        productStatsDto.setAmount(productStats.getAmount());
        productStatsDto.setMonth(productStats.getMonth());
        productStatsDto.setYear(productStats.getYear());
        return productStatsDto;
    }

    @Override
    public List<ProductStatsDto> convertToProductStatsDtoList(List<ProductStats> productStatsList) {
        List<ProductStatsDto> productStatsDtoList = new ArrayList<>();
        for (ProductStats productStats : productStatsList) {
            productStatsDtoList.add(convertToProductStatsDto(productStats));
        }
        return productStatsDtoList;
    }

    @Override
    public UserStatsDto convertToUserStatsDto(UserStats userStats) {
        UserStatsDto userStatsDto = new UserStatsDto();
        userStatsDto.setId(userStats.getId());
        userStatsDto.setUser(convertToUserDto(userStats.getUser()));
        userStatsDto.setSum(userStats.getSum());
        userStatsDto.setMonth(userStats.getMonth());
        userStatsDto.setYear(userStats.getYear());
        return userStatsDto;
    }

    @Override
    public List<UserStatsDto> convertToUserStatsDtoList(List<UserStats> userStatsList) {
        List<UserStatsDto> userStatsDtoList = new ArrayList<>();
        for (UserStats userStats : userStatsList) {
            userStatsDtoList.add(convertToUserStatsDto(userStats));
        }
        return userStatsDtoList;
    }

}
