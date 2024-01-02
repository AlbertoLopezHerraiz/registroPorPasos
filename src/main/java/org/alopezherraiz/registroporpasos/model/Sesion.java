package org.alopezherraiz.registroporpasos.model;

import jakarta.validation.constraints.*;
import lombok.*;
import org.alopezherraiz.registroporpasos.configuration.validaciones.ValidacionFecha;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

 @Getter @Setter @ToString
 @NoArgsConstructor @AllArgsConstructor
public class Sesion {
    DatosPersonales datosPersonales;
    DatosProfesionales datosProfesionales;
    DatosBancarios datosBancarios;
 }
