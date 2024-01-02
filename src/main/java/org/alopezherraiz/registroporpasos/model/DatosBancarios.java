package org.alopezherraiz.registroporpasos.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class DatosBancarios {
    @NotNull @NotBlank
    private String cuentaCorriente;
}
