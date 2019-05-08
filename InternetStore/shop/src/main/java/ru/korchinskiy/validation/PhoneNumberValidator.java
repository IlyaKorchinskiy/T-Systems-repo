package ru.korchinskiy.validation;

import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Service
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    private static final String REGEX = "\\+\\d{11}";

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {

    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        return phoneNumber.matches(REGEX);
    }
}
