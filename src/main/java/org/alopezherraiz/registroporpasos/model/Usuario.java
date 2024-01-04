package org.alopezherraiz.registroporpasos.model;

import lombok.*;

@Getter @Setter @ToString
 @NoArgsConstructor @AllArgsConstructor
public class Usuario {
    DatosPersonales datosPersonales;
    DatosProfesionales datosProfesionales;
    DatosBancarios datosBancarios;
 }
