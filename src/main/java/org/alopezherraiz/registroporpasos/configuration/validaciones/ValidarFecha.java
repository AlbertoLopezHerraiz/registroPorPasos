package org.alopezherraiz.registroporpasos.configuration.validaciones;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.time.Period;

public class ValidarFecha implements ConstraintValidator<ValidacionFecha, LocalDate> {

    @Override
    public boolean isValid(LocalDate fechaNacimiento, ConstraintValidatorContext context) {
        try {
        int years= Period.between(fechaNacimiento, LocalDate.now()).getYears();
            return years >= 18;
        } catch (Exception e) {
            return false;
        }
    }
}
