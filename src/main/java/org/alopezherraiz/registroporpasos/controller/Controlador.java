package org.alopezherraiz.registroporpasos.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.alopezherraiz.registroporpasos.model.Colecciones;
import org.alopezherraiz.registroporpasos.model.DatosUsuario;
import org.alopezherraiz.registroporpasos.model.DatosPersonales;
import org.alopezherraiz.registroporpasos.model.DatosProfesionales;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.alopezherraiz.registroporpasos.model.Colecciones.*;

@Controller
public class Controlador {
    private static final int MAX_INTENTOS =3;
    private int contador = MAX_INTENTOS;

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
    @ModelAttribute("tratamientos")
private Map<String, String>devuelveTratamiento(){
        return getTratamiento();
    }
private Map<String, DatosUsuario>devuelveUsuarios(){
        return getUsuarios();
    }

    @GetMapping("datos1")
    public String datosBancarios(Model modelo, @ModelAttribute("datosUsuario") DatosUsuario datosUsuario, HttpSession sesion) {
        if(sesion.getAttribute("datosUsuario")!=null){
            modelo.addAttribute("datosUsuario", sesion.getAttribute("datosUsuario"));}
        return "datosusuario";
    }

    @PostMapping("datos1")
    public String datosBancariosPost(Model modelo,
                                     @Valid @ModelAttribute("datosUsuario") DatosUsuario datosUsuario,
                                     BindingResult resultadoVinculacion, HttpSession sesion) {

        if (resultadoVinculacion.hasErrors()) {
            return "datosusuario";
        }
        sesion.setAttribute("datosUsuario", datosUsuario);
        return "redirect:/datos2";
    }
    @GetMapping("datos2")
    public String datosPersonales(Model modelo, @ModelAttribute("datosPersonales") DatosPersonales datosPersonales, HttpSession sesion) {
        if(sesion.getAttribute("datosPersonales")!=null){
            modelo.addAttribute("datosPersonales", sesion.getAttribute("datosPersonales"));}
        return "datospersonales";
    }

    @PostMapping("datos2")
    public String datosPersonalesPost(Model modelo,
                                      @Valid @ModelAttribute("datosPersonales") DatosPersonales datosPersonales,
                                      BindingResult resultadoVinculacion, HttpSession sesion) {

        if (resultadoVinculacion.hasErrors()) {
            return "datospersonales";
        }
        sesion.setAttribute("datosPersonales",datosPersonales);
        return "redirect:/datos3";
    }

    @GetMapping("datos3")
    public String datosProfesionales(Model modelo, @ModelAttribute("datosProfesionales") DatosProfesionales datosProfesionales, HttpSession sesion) {
       datosProfesionales.setDepartamentoSeleccionado("M");
       datosProfesionales.setSalario(2750.00);
        if(sesion.getAttribute("datosProfesionales")!=null){
            modelo.addAttribute("datosProfesionales", sesion.getAttribute("datosProfesionales"));}
        return "datosprofesionales";
    }

    @PostMapping("datos3")
    public String datosProfesionalesPost(Model modelo,
                                         @Valid @ModelAttribute("datosProfesionales")DatosProfesionales datosProfesionales,
                                         BindingResult resultadoVinculacion, HttpSession sesion) {
        if (resultadoVinculacion.hasErrors()) {
            return "datosprofesionales";
        }
        sesion.setAttribute("datosProfesionales",datosProfesionales);
        return "redirect:/resumen";
    }

    @GetMapping("resumen")
    public String resumen(Model modelo, HttpSession sesion, DatosUsuario usuario) {
        if(sesion.getAttribute("datosPersonales")!=null){
            modelo.addAttribute("datosPersonales", sesion.getAttribute("datosPersonales"));}
        if(sesion.getAttribute("datosProfesionales")!=null){
            modelo.addAttribute("datosProfesionales", sesion.getAttribute("datosProfesionales"));}
        if(sesion.getAttribute("datosUsuario")!=null){
            modelo.addAttribute("datosUsuario", sesion.getAttribute("datosUsuario"));}
        if(sesion.getAttribute("datosUsuario")!=null && sesion.getAttribute("datosProfesionales")!=null && sesion.getAttribute("datosPersonales")!=null){
           DatosUsuario usuario1= (DatosUsuario) sesion.getAttribute("datosUsuario");
            usuario.setUsuario(usuario1.getUsuario());
            usuario.setClave(usuario1.getClave());
            usuario.setDatosPersonales((DatosPersonales) sesion.getAttribute("datosPersonales"));
            usuario.setDatosProfesionales((DatosProfesionales) sesion.getAttribute("datosProfesionales"));
            Colecciones.agregarUsuario(usuario);
            String mensaje= "Usuario introducido satisfactoriamente.";
            sesion.removeAttribute("datosUsuario");
            sesion.removeAttribute("datosPersonales");
            sesion.removeAttribute("datosProfesionales");
            modelo.addAttribute("mensaje", mensaje);
        }
        return "resumen";
    }
    @GetMapping("paso1")
    public String paso1Get(HttpSession sesion){
        sesion.setAttribute("paso", 1);
        return "inicio-usuario";
    }
    @PostMapping("paso1")
    public String paso1Post(Model modelo,
                            @RequestParam String usuario,
                            HttpSession sesion){

        for(int i=1;i<=devuelveUsuarios().size();i++){

            if(usuario.equals(devuelveUsuarios().get(usuario).getUsuario() )){
                sesion.setAttribute("usuario", usuario);
                contador=MAX_INTENTOS;
                return "redirect:/paso2";
            }
        }
        modelo.addAttribute("mensaje", "ERROR: Usuario incorrecto");
        return "inicio-usuario";
    }
    @GetMapping("paso2")
    public String paso2Get(HttpSession sesion){
        int paso= (int) sesion.getAttribute("paso")+1;
        sesion.setAttribute("paso", paso);
        return "inicio-clave";
    }
    @PostMapping("paso2")
    public String paso2Post(Model modelo,
                            @RequestParam String clave,
                            HttpSession sesion) {

        for (int i = 1; i <= devuelveUsuarios().size(); i++) {
            String usuario = (String) sesion.getAttribute("usuario");

            if (clave.equals(devuelveUsuarios().get(usuario).getClave())) {
                sesion.setAttribute("clave", clave);

                return "redirect:/formulario/paso3";
            }
            contador--;
            modelo.addAttribute("mensaje",  "ERROR: Usuario desconocido");

            if(contador==0){
                return "cuenta";
            }
            modelo.addAttribute("mensaje",  "ERROR: Contaseña incorrecta, te quedan "
                    + contador + " intentos");
        }
        return "inicio-clave";
    }
}