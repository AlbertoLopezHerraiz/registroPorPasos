package org.alopezherraiz.registroporpasos.controller;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Map;

import static org.alopezherraiz.registroporpasos.configuration.Colecciones.*;

@Controller
public class Controlador {
    @ModelAttribute("generos")
    private Map<String, String> devuelveListaGeneros(){
        return getGeneros();
    }
    @RequestMapping("datos1")
    public String datosPersonales(Model modelo, @Valid @ModelAttribute("sesion") HttpSession sesion){

        return "datospersonales";
    }
}
