package ru.korchinskiy.message;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Message {
    public static final String PRODUCT_NOT_ENOUGH = "Not enough product";
    public static final String PRODUCT_ADD_SUCCESS = "Product successfully added";
    public static final String PRODUCT_ADD_TO_CART_SUCCESS = "Product successfully added to cart";
    public static final String PRODUCT_ADD_TO_DB_CART_SUCCESS = "Product successfully added to database cart";
    public static final String PRODUCT_ALREADY_EXISTS = "Product with this title already exists";
    public static final String ORDER_SAVE_SUCCESS = "Order successfully saved";
    public static final String ORDER_STATUS_UPDATE_SUCCESS = "Order status successfully updated";
    public static final String CART_IS_EMPTY = "Cart is empty";
    public static final String FILE_SAVE_FAIL = "Couldn't save file";
    public static final String FILE_SAVE_SUCCESS = "File successfully saved";
    public static final String USER_ALREADY_EXISTS = "User with this e-mail already exists";
    public static final String USER_ADD_SUCCESS = "User successfully registered";
    public static final String USER_UPDATE_SUCCESS = "Info successfully updated";
    public static final String USER_ADDRESS_DELETE_SUCCESS = "Address deleted successfully";
    public static final String USER_ADDRESS_UPDATE_SUCCESS = "Address updated successfully";
    public static final String USER_ADDRESS_ADD_SUCCESS = "Address added successfully";
    public static final String CATEGORY_UPDATE_SUCCESS = "Category successfully updated";
    public static final String CATEGORY_ALREADY_EXISTS = "Category with the name already exists";
    public static final String CATEGORY_ADD_SUCCESS = "Category successfully added";
    public static final String CATEGORY_DELETE_SUCCESS = "Category successfully removed";
    public static final String VALIDATION_REGISTER_FAIL = "Registration validation failed";
    public static final String VALIDATION_EDIT_USER_FAIL = "Edit user validation failed";
    public static final String VALIDATION_ADD_PRODUCT_FAIL = "Add product validation failed";
    public static final String UPDATE = "Update";


    List<String> errors;
    List<String> warnings;
    List<String> confirms;

    public Message() {
        errors = new ArrayList<>();
        warnings = new ArrayList<>();
        confirms = new ArrayList<>();
    }

}
