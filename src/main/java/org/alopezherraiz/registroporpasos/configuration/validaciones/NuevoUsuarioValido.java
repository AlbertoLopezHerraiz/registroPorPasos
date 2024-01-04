package org.alopezherraiz.registroporpasos.configuration.validaciones;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NuevoUsuarioValidator.class)
public @interface NuevoUsuarioValido {
    String message() default "El usuario ya está registrado";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
