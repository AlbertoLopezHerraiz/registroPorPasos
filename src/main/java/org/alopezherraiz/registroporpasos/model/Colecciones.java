package org.alopezherraiz.registroporpasos.model;

import java.util.HashMap;
import java.util.Map;

public class Colecciones {
    private static final Map<String, String> GENEROS= new HashMap<>();
    private static final Map<String, String> EXTRAS= new HashMap<>();
    private static final Map<String, String> NACIONALIDAD= new HashMap<>();
    private static final Map<String, String> DEPARTAMENTOS= new HashMap<>();


    static {
        GENEROS.put("F", "Femenino");
        GENEROS.put("M", "Masculino");
        GENEROS.put("O", "Otro");
        EXTRAS.put("C", "Casado o Pareja de hecho");
        EXTRAS.put("H", "Hijos");
        NACIONALIDAD.put("E", "Española");
        NACIONALIDAD.put("F", "Francesa");
        NACIONALIDAD.put("I", "Italiana");
        NACIONALIDAD.put("P", "Portuguesa");
        DEPARTAMENTOS.put("M", "Marketing");
        DEPARTAMENTOS.put("A", "Administración");
        DEPARTAMENTOS.put("C", "Contabilidad");
        DEPARTAMENTOS.put("RRHH", "Recursos Humanos");
    }

    public static Map<String, String> getGeneros(){
        return GENEROS;
    }
    public static Map<String, String> getExtras(){
        return EXTRAS;
    }
    public static Map<String, String> getNacionalidades(){
        return NACIONALIDAD;
    }
    public static Map<String, String> getDepartamentos(){
        return DEPARTAMENTOS;
    }
}
