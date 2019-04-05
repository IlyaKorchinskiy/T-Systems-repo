package ru.korchinskiy.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileValidator.class)
@Documented
public @interface File {
    String message() default "choose file";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
