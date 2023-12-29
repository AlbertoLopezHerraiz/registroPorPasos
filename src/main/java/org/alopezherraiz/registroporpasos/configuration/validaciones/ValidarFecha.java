package org.alopezherraiz.registroporpasos.configuration.validaciones;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;
import java.time.LocalDate;

public class ValidarFecha implements ConstraintValidator<ValidacionFecha, LocalDate> {
    @Override
    public void initialize(ValidacionFecha constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate fechaNacimiento, ConstraintValidatorContext context) {
        if (fechaNacimiento == null) {
            return false;  // La validación no pasará si la fecha de nacimiento es nula
        }

        // Verifica si la fecha de nacimiento es posterior al año 2000
        return fechaNacimiento.isAfter(LocalDate.of(1999, 12, 31));
    }
}
