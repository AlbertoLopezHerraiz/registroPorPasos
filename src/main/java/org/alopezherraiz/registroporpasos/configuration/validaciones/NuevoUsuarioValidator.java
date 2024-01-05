package org.alopezherraiz.registroporpasos.configuration.validaciones;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.alopezherraiz.registroporpasos.model.Colecciones;
import org.alopezherraiz.registroporpasos.model.DatosUsuario;

public class NuevoUsuarioValidator implements ConstraintValidator<NuevoUsuarioValido, DatosUsuario> {

    @Override
    public void initialize(final NuevoUsuarioValido constraintAnnotation) {
        // No se requiere inicialización específica
    }

    @Override
    public boolean isValid(final DatosUsuario usuario, final ConstraintValidatorContext context) {
        // Verificar que el usuario no esté en la colección de usuarios
        return !Colecciones.usuarioExiste(usuario.getUsuario());
    }
}
