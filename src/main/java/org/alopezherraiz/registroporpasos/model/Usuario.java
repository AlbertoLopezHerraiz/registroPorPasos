package org.alopezherraiz.registroporpasos.model;

import lombok.*;
import org.alopezherraiz.registroporpasos.configuration.validaciones.NuevoUsuarioValido;

import java.util.Collection;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@NuevoUsuarioValido
public class Usuario {
    DatosUsuario datosUsuarios;
    DatosPersonales datosPersonales;
    DatosProfesionales datosProfesionales;

}
