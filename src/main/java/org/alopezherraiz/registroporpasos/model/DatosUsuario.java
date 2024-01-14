package org.alopezherraiz.registroporpasos.model;

import jakarta.validation.constraints.*;
import lombok.*;
import org.alopezherraiz.registroporpasos.configuration.validaciones.FieldsValueMatch;
import org.alopezherraiz.registroporpasos.configuration.validaciones.NuevoUsuarioValido;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "clave",
                fieldMatch = "validarClave"
        )
})
@NuevoUsuarioValido
public class DatosUsuario {
    @NotNull @NotBlank
    private String usuario;
    @NotEmpty
    private String clave;
    private String validarClave;

    DatosPersonales datosPersonales;
    DatosProfesionales datosProfesionales;

    public DatosUsuario(String usuario, String clave, String validarClave) {
        this.usuario = usuario;
        this.clave = clave;
        this.validarClave = validarClave;
    }
}
