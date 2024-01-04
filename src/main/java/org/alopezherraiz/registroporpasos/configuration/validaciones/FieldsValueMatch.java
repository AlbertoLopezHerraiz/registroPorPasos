package org.alopezherraiz.registroporpasos.configuration.validaciones;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = FieldsValueMatchValidator.class)
@Target({ TYPE })
@Retention(RUNTIME)
@Documented

public @interface FieldsValueMatch {
    String message() default "* Las claves no son coincidentes";
    Class<?>[] groups() default {};  // Specify the validation groups here
    Class<? extends Payload>[] payload() default {};
    String field();

    String fieldMatch();

    @Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        FieldsValueMatch[] value();
    }
}
