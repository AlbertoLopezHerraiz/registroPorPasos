package org.alopezherraiz.registroporpasos.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.alopezherraiz.registroporpasos.model.Sesion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.alopezherraiz.registroporpasos.model.Colecciones.*;

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
    public String datosPersonales(Model modelo, @ModelAttribute("datos") Sesion datos, HttpSession sesion){
        return "datospersonales";
    }
    @PostMapping("datos1")
    public String datosPersonalesPost(Model modelo, @Valid @ModelAttribute("datos") Sesion datos, HttpSession sesion){
        System.out.println(datos.getApellidos() + ", "+datos.getNombre());
        return "redirect:/datos1";
    }
    @GetMapping("datos2")
    public String datosProfesionales(Sesion datos, HttpSession sesion){
        return "datospersonales";
    }
}
