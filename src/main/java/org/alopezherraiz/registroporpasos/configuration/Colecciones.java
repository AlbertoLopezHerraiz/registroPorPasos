package org.alopezherraiz.registroporpasos.configuration;

import java.util.HashMap;
import java.util.Map;

public class Colecciones {
    private static final Map<String, String> GENEROS= new HashMap<>();

    static {
        GENEROS.put("F", "Femenino");
        GENEROS.put("M", "Masculino");
        GENEROS.put("O", "Otro");}

    public static Map<String, String> getGeneros(){
        return GENEROS;
    }
}
