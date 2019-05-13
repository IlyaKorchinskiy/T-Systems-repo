package ru.korchinskiy.validation;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FileValidator implements ConstraintValidator<File, MultipartFile> {
    private static final Long MIN_SIZE = 10 * 1024L;
    private static final Long MAX_SIZE = 100 * 1024L;

    private List<String> formatList;

    @Override
    public void initialize(File constraintAnnotation) {
        formatList = new ArrayList<>(Arrays.asList("jpg", "png"));
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        String[] fileName = file.getOriginalFilename().split("\\.");
        String format = fileName[fileName.length - 1];
        if (!formatList.contains(format)) return false;
        return file.getSize() >= MIN_SIZE && file.getSize() <= MAX_SIZE;
    }
}
