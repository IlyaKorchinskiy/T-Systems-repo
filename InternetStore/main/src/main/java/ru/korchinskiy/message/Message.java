package ru.korchinskiy.message;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Message {
    public static final String PRODUCT_NOT_ENOUGH = "Not enough product";
    public static final String PRODUCT_ADD_SUCCESS = "Product successfully added";

    public static final String ORDER_SAVE_SUCCESS = "Order successfully saved";
    public static final String CART_IS_EMPTY = "Cart is empty";
    public static final String ORDER_STATUS_UPDATE_SUCCESS = "Order status successfully updated";

    List<String> errors;
    List<String> warnings;
    List<String> confirms;

    public Message() {
        errors = new ArrayList<>();
        warnings = new ArrayList<>();
        confirms = new ArrayList<>();
    }

}
