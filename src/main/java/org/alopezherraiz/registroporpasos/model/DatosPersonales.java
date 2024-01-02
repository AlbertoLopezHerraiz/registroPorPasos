package org.alopezherraiz.registroporpasos.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.alopezherraiz.registroporpasos.configuration.validaciones.ValidacionFecha;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class DatosPersonales {
    @NotNull @NotBlank
    private String nombre;
    @NotNull @NotBlank
    private String apellidos;
    @ValidacionFecha @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaNacimiento;
    @NotBlank
    private String generoSeleccionado;
    private ArrayList<String> extrasSeleccionados;
    @NotEmpty
    private ArrayList<String> nacionalidadesSeleccionadas;
}
