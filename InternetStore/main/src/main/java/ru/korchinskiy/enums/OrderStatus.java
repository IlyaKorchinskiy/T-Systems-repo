package ru.korchinskiy.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {
    NEW,
    CONFIRMED,
    SHIPPED,
    DELIVERED
}
