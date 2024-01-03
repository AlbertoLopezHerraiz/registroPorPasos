package org.alopezherraiz.registroporpasos.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.alopezherraiz.registroporpasos.model.DatosBancarios;
import org.alopezherraiz.registroporpasos.model.DatosPersonales;
import org.alopezherraiz.registroporpasos.model.DatosProfesionales;
import org.alopezherraiz.registroporpasos.model.Sesion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.alopezherraiz.registroporpasos.model.Colecciones.*;

@Controller
public class Controlador {
    @ModelAttribute("generos")
    private Map<String, String> devuelveListaGeneros() {
        return getGeneros();
    }

    @ModelAttribute("extras")
    private Map<String, String> devuelveListaExtras() {
        return getExtras();
    }

    @ModelAttribute("nacionalidades")
    private Map<String, String> devuelveListaNacionalidades() {
        return getNacionalidades();
    }

    @ModelAttribute("departamentos")
    private Map<String, String> devuelveDepartamentoSeleccionado() {
        return getDepartamentos();
    }
    @GetMapping("datos1")
    public String datosPersonales(Model modelo, @ModelAttribute("datosPersonales") DatosPersonales datosPersonales) {
        return "datospersonales";
    }

    @PostMapping("datos1")
    public String datosPersonalesPost(Model modelo,
                                      @Valid @ModelAttribute("datosPersonales") DatosPersonales datosPersonales,
                                      BindingResult resultadoVinculacion, HttpSession sesion) {

        if (resultadoVinculacion.hasErrors()) {
            return "datospersonales";
        }
        sesion.setAttribute("datosPersonales",datosPersonales);
        return "redirect:/datos2";
    }

    @GetMapping("datos2")
    public String datosProfesionales(@ModelAttribute("datosProfesionales") DatosProfesionales datosProfesionales) {
       datosProfesionales.setDepartamentoSeleccionado("M");
       datosProfesionales.setSalario(2750.00);
        return "datosprofesionales";
    }

    @PostMapping("datos2")
    public String datosProfesionalesPost(Model modelo,
                                         @Valid @ModelAttribute("datosProfesionales")DatosProfesionales datosProfesionales,
                                         BindingResult resultadoVinculacion, HttpSession sesion) {
        String mensaje = "";
        if (resultadoVinculacion.hasErrors()) {
            modelo.addAttribute(mensaje);
            return "datosprofesionales";
        }
        sesion.setAttribute("datosProfesionales",datosProfesionales);
        return "redirect:/datos3";
    }
    @GetMapping("datos3")
    public String datosBancarios(@ModelAttribute("datosBancarios") DatosBancarios datosBancarios) {

        return "datosbancarios";
    }

    @PostMapping("datos3")
    public String datosBancariosPost(Model modelo,
                                     @Valid @ModelAttribute("datosBancarios")DatosBancarios datosBancarios,
                                     BindingResult resultadoVinculacion, HttpSession sesion) {
        System.out.println(sesion.getAttribute("datosProfesionales")+"\n -------------------- \n");

        String mensaje = "";
        if (resultadoVinculacion.hasErrors()) {
            modelo.addAttribute(mensaje);
            return "datosbancarios";
        }
        sesion.setAttribute("datosBancarios", datosBancarios);
        return "redirect:/resumen";
    }
    @GetMapping("resumen")
    public String resumen(Model modelo, HttpSession sesion) {
      //  DatosPersonales datosPersonales = (DatosPersonales) sesion.getAttribute("datosPersonales");
        modelo.addAttribute("datosPersonales", sesion.getAttribute("datosPersonales"));
        DatosProfesionales datosProfesionales = (DatosProfesionales) sesion.getAttribute("datosProfesionales");
        DatosBancarios datosBancarios = (DatosBancarios) sesion.getAttribute("datosBancarios");
        modelo.addAttribute("datosProfesionales", datosProfesionales);
        return "resumen";
    }
}