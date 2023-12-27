package org.alopezherraiz.registroporpasos.configuration;

import lombok.*;

import java.time.LocalDate;
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class DatosPersonales {
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String generoSeleccionado;

}
