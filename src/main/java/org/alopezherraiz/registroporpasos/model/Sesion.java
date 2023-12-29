package org.alopezherraiz.registroporpasos.model;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

 @Getter @Setter @ToString
 @NoArgsConstructor @AllArgsConstructor
public class Sesion {
    @NotNull @NotBlank
    private String nombre;
    @NotNull @NotBlank
    private String apellidos;
    @NotNull @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaNacimiento;
    private String generoSeleccionado;

 }
