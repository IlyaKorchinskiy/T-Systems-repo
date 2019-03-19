package ru.korchinskiy.message;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Message {
    List<String> errors;
    List<String> warnings;
    List<String> confirms;

    public Message() {
        errors = new ArrayList<>();
        warnings = new ArrayList<>();
        confirms = new ArrayList<>();
    }

}
