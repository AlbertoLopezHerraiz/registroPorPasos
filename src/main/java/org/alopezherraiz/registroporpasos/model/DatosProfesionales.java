package org.alopezherraiz.registroporpasos.model;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class DatosProfesionales {
    private String departamentoSeleccionado;
    @DecimalMin(value= "1080.00")
    @Digits(integer = 10,fraction = 2) @NotNull
    private double salario;
    private String comentarios;
}
