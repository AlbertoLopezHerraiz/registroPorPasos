package org.alopezherraiz.registroporpasos.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

import static org.alopezherraiz.registroporpasos.configuration.Colecciones.getGeneros;

@Controller
public class Controlador {
    @ModelAttribute("generos")
    private Map<String, String> devuelveListaGeneros(){
        return getGeneros();
    }
    @GetMapping("datos1")
    public String datosPersonales(Model modelo,
                                  @RequestParam String usuario,
                                  HttpSession sesion){
        return "datos-personales";
    }
}
