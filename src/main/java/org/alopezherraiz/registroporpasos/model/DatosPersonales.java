package org.alopezherraiz.registroporpasos.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class DatosPersonales {
    @NotNull
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String generoSeleccionado;

}
