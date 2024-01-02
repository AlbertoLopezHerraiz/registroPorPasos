package org.alopezherraiz.registroporpasos.model;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class DatosProfesionales {
    private String departamento;
    private float salario;
    private String comentarios;
}
