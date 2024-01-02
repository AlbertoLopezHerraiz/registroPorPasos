package org.alopezherraiz.registroporpasos.configuration.validaciones;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;
import java.time.LocalDate;

public class ValidarFecha implements ConstraintValidator<ValidacionFecha, LocalDate> {

    @Override
    public boolean isValid(LocalDate fechaNacimiento, ConstraintValidatorContext context) {
        try {

        // Verifica si la fecha de nacimiento es posterior al año 2000
        return fechaNacimiento.isAfter(LocalDate.of(1999, 12, 31));
        } catch (Exception e) {
            return false;
        }
    }
}
