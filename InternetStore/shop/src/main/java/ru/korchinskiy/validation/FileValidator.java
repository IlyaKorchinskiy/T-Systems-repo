package ru.korchinskiy.validation;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Service
public class FileValidator implements ConstraintValidator<File, MultipartFile> {
    @Override
    public void initialize(File constraintAnnotation) {

    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        return !file.isEmpty();
    }
}
