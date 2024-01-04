package org.alopezherraiz.registroporpasos.model;

import jakarta.validation.constraints.*;
import lombok.*;
import org.alopezherraiz.registroporpasos.configuration.validaciones.FieldsValueMatch;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "clave",
                fieldMatch = "validarClave"
        )
})
public class DatosUsuario {
    @NotNull @NotBlank
    private String usuario;
    @NotEmpty
    private String clave;
    @NotEmpty
    private String validarClave;
}
