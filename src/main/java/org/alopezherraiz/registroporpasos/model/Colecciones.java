package org.alopezherraiz.registroporpasos.model;

import java.util.HashMap;
import java.util.Map;

public class Colecciones {
    private static final Map<String, String> GENEROS= new HashMap<>();
    private static final Map<String, String> EXTRAS= new HashMap<>();
    private static final Map<String, String> NACIONALIDAD= new HashMap<>();
    private static final Map<String, String> DEPARTAMENTOS= new HashMap<>();
    private static final Map<String, String> TRATAMIENTO= new HashMap<>();
    private static final Map<String, DatosUsuario> USUARIOS= new HashMap<>();


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
        DEPARTAMENTOS.put("R", "Investigación");
        DEPARTAMENTOS.put("S", "Ventas");
        DEPARTAMENTOS.put("C", "Contabilidad");
        DEPARTAMENTOS.put("O", "Operaciones");
        TRATAMIENTO.put("Sra.", "Señora");
        TRATAMIENTO.put("Sr.", "Señor");
        TRATAMIENTO.put("Srta.", "Señorita");
        TRATAMIENTO.put("Srto.", "Señorito");
        TRATAMIENTO.put("Caba.", "Caballera");
        TRATAMIENTO.put("Cabo.", "Caballero");
        TRATAMIENTO.put("Amza.", "Amazona");
        TRATAMIENTO.put("Amzo.", "Amazono");
       //usuario de prueba para testear el login
        USUARIOS.put("root", new DatosUsuario("root", "root", "root",
                new DatosPersonales("root","root", "root"),
                new DatosProfesionales()));
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
    public static Map<String, String> getTratamiento(){
        return TRATAMIENTO;
    }
    public static Map<String, DatosUsuario> getUsuarios(){return USUARIOS;}
    public static boolean usuarioExiste(String usuario){return USUARIOS.containsKey(usuario);}
    public static void agregarUsuario(DatosUsuario usuario){ USUARIOS.put(usuario.getUsuario(), usuario);}
}
