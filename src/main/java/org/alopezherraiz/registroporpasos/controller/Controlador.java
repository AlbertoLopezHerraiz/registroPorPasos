package org.alopezherraiz.registroporpasos.controller;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.alopezherraiz.registroporpasos.configuration.Sesion;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

import static org.alopezherraiz.registroporpasos.configuration.Colecciones.*;

@Controller
public class Controlador {
    @ModelAttribute("generos")
    private Map<String, String> devuelveListaGeneros(){
        return getGeneros();
    }
    @ModelAttribute("extras")
    private Map<String, String> devuelveListaExtras(){
        return getExtras();
    }
    @ModelAttribute("nacionalidades")
    private Map<String, String> devuelveListaNacionalidades(){
        return getNacionalidades();
    }

    @GetMapping("datos1")
    public String datosPersonales(Sesion datos, HttpSession sesion){
        return "datospersonales";
    }
    @PostMapping("datos1")
    public String datosPersonalesPost(Sesion datos, HttpSession sesion){
       // sesion.setAttribute("apellidos", datos.setApellidos(sesion.getAttribute("apellidos")));
        return "redirect:/datos1";
    }
    @GetMapping("datos2")
    public String datosProfesionales(Sesion datos, HttpSession sesion){
        return "datospersonales";
    }
}
