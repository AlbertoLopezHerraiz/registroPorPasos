package org.alopezherraiz.registroporpasos.configuration.validaciones;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidarFecha.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidacionFecha {
    String message() default "* La edad tiene que ser mayor de 18 años";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
