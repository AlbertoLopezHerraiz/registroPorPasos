package org.alopezherraiz.registroporpasos.model;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class DatosProfesionales {
    private String departamentoSeleccionado;
    @DecimalMin(value= "0.00")
    @Digits(integer = 10,fraction = 2) @NotNull
    private double salario;
    private String comentarios;
}
