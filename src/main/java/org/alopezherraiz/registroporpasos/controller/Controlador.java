package org.alopezherraiz.registroporpasos.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import static org.alopezherraiz.registroporpasos.model.Colecciones.*;

@Controller
public class Controlador {
    private static final int MAX_INTENTOS =3;
    int contador = MAX_INTENTOS;

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
    public String datosUsuarioGet(Model modelo, @ModelAttribute("datosUsuario") DatosUsuario datosUsuario, HttpSession sesion) {
        if(sesion.getAttribute("datosUsuario")!=null){
            modelo.addAttribute("datosUsuario", sesion.getAttribute("datosUsuario"));}
        return "datosusuario";
    }

    @PostMapping("datos1")
    public String datosUsuarioPost(@Valid @ModelAttribute("datosUsuario") DatosUsuario datosUsuario,
                                     BindingResult resultadoVinculacion, HttpSession sesion) {

        if (resultadoVinculacion.hasErrors()) {
            return "datosusuario";
        }
        sesion.setAttribute("datosUsuario", datosUsuario);
        return "redirect:/datos2";
    }
    @GetMapping("datos2")
    public String datosPersonalesGet(Model modelo, @ModelAttribute("datosPersonales") DatosPersonales datosPersonales, HttpSession sesion) {
        if(sesion.getAttribute("datosPersonales")!=null){
            modelo.addAttribute("datosPersonales", sesion.getAttribute("datosPersonales"));}
        return "datospersonales";
    }

    @PostMapping("datos2")
    public String datosPersonalesPost(@Valid @ModelAttribute("datosPersonales") DatosPersonales datosPersonales,
                                      BindingResult resultadoVinculacion, HttpSession sesion) {

        if (resultadoVinculacion.hasErrors()) {
            return "datospersonales";
        }
        sesion.setAttribute("datosPersonales",datosPersonales);
        return "redirect:/datos3";
    }

    @GetMapping("datos3")
    public String datosProfesionales(Model modelo,
                                     @ModelAttribute("datosProfesionales") DatosProfesionales datosProfesionales, HttpSession sesion) {

        if(sesion.getAttribute("datosProfesionales")!=null){
            modelo.addAttribute("datosProfesionales", sesion.getAttribute("datosProfesionales"));}
        return "datosprofesionales";
    }

    @PostMapping("datos3")
    public String datosProfesionalesPost(@Valid @ModelAttribute("datosProfesionales")DatosProfesionales datosProfesionales,
                                         BindingResult resultadoVinculacion, HttpSession sesion) {
        if (resultadoVinculacion.hasErrors()) {
            return "datosprofesionales";
        }
        sesion.setAttribute("datosProfesionales",datosProfesionales);
        return "redirect:/resumen";
    }

    @GetMapping("resumen")
    public String resumen(Model modelo, HttpSession sesion) {
        if(sesion.getAttribute("datosPersonales")!=null){
            modelo.addAttribute("datosPersonales", sesion.getAttribute("datosPersonales"));
        }

        if(sesion.getAttribute("datosProfesionales")!=null){
            modelo.addAttribute("datosProfesionales", sesion.getAttribute("datosProfesionales"));
        }
        if(sesion.getAttribute("datosUsuario")!=null){
            modelo.addAttribute("datosUsuario", sesion.getAttribute("datosUsuario"));
        }else return "resumen";

        return "resumen";
    }
    @PostMapping("resumen")
    public String resumenPost(Model modelo, HttpSession sesion, DatosUsuario usuario){
        try{
        DatosUsuario usuario1= (DatosUsuario) sesion.getAttribute("datosUsuario");
        modelo.addAttribute("datosUsuario", sesion.getAttribute("datosUsuario"));
        modelo.addAttribute("datosProfesionales", sesion.getAttribute("datosProfesionales"));
        modelo.addAttribute("datosPersonales", sesion.getAttribute("datosPersonales"));
        usuario.setUsuario(usuario1.getUsuario());
        usuario.setClave(usuario1.getClave());
        usuario.setDatosPersonales((DatosPersonales) sesion.getAttribute("datosPersonales"));
        usuario.setDatosProfesionales((DatosProfesionales) sesion.getAttribute("datosProfesionales"));

        Colecciones.agregarUsuario(usuario);
        String mensaje= "Usuario introducido satisfactoriamente.";
        modelo.addAttribute("mensaje", mensaje);

        sesion.removeAttribute("datosUsuario");
        sesion.removeAttribute("datosPersonales");
        sesion.removeAttribute("datosProfesionales");
        } catch (Exception e){
            String mensaje= "* El usuario no está completo";
            modelo.addAttribute("mensaje2", mensaje);
        }
        return "resumen";
    }
    @GetMapping("masacre")
    public String masacre(HttpSession session){
        session.invalidate();
        return "redirect:/datos1";
    }
    @GetMapping("paso1")
    public String paso1Get(Model modelo, ArrayList<String> usuariosIniciados,
                           @CookieValue(name = "credenciales", defaultValue = "") String credenciales, HttpSession sesion){
        try{
            String mensaje= (String) sesion.getAttribute("mensaje");
            modelo.addAttribute("mensaje",mensaje);
            sesion.removeAttribute("mensaje");}
        catch (Exception ignored){

        }
        if(!credenciales.isEmpty()) {
            String[] partesCredenciales = credenciales.split("#");

           for(String usuario : partesCredenciales){
               String[] datosUsuario = usuario.split(":");
               usuariosIniciados.add(datosUsuario[0]);
           }
           modelo.addAttribute("usuariosIniciados", usuariosIniciados);
        }
        return "inicio-usuario";
    }
    @PostMapping("paso1")
    public String paso1Post(Model modelo,
                            @RequestParam String usuario,
                            @RequestParam(value = "usuario2", required = false) String usuario2,
                            HttpSession sesion){
        for(int i=1;i<=devuelveUsuarios().size();i++){
            try {
                if (usuario.equals(devuelveUsuarios().get(usuario).getUsuario())) {
                    sesion.setAttribute("usuario", usuario);
                    return "redirect:/paso2";
                }else if(usuario2.equals(devuelveUsuarios().get(usuario2).getUsuario())){
                    sesion.setAttribute("usuario", usuario2);
                    return "redirect:/paso2";
                }
            }   catch (Exception ignored){

            }
        }
        sesion.setAttribute("mensaje", modelo.getAttribute("mensaje"));
        modelo.addAttribute("mensaje", "* ERROR: Usuario incorrecto");
        sesion.setAttribute("mensaje", modelo.getAttribute("mensaje"));
        return "redirect:/paso1";
    }

    @GetMapping("paso2")
    public String paso2Get(){

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
                sesion.setAttribute("datosUsuario", devuelveUsuarios().get(usuario));
                return "redirect:/devolver";
            }
            contador--;
            modelo.addAttribute("mensaje",  "* ERROR: Usuario desconocido");

            if(contador ==0){
                return "inicio-usuario";
            }
            modelo.addAttribute("mensaje",  "* ERROR: Contaseña incorrecta, te quedan "
                    + contador + " intentos");
        }

        return "inicio-clave";
    }
    @GetMapping("devolver")
    public String devolver(Model modelo , HttpSession sesion, HttpServletResponse respuestaHttp,
                           @CookieValue(name = "credenciales", defaultValue = "") String credenciales){
        DatosUsuario usuario = getUsuarios().get((String) sesion.getAttribute("usuario"));
        modelo.addAttribute("usuario", usuario);

        if(credenciales.isEmpty()){
            credenciales = sesion.getAttribute("usuario")+":0#";
            sesion.setAttribute((String)sesion.getAttribute("usuario"), 0);
        }
        credenciales = contenidoCookie(credenciales, sesion);
        respuestaHttp.addCookie(new Cookie("credenciales", credenciales));
        DatosUsuario datosUsuario = (DatosUsuario) sesion.getAttribute("datosUsuario");
        DatosPersonales datosPersonales = datosUsuario.getDatosPersonales();
        modelo.addAttribute("tratamientoSeleccionado", datosPersonales.getTratamientoSeleccionado());
        modelo.addAttribute("apellidos", datosPersonales.getApellidos());
        modelo.addAttribute("usuario", sesion.getAttribute("usuario"));
        modelo.addAttribute("clave", sesion.getAttribute("clave"));
        modelo.addAttribute("contador", sesion.getAttribute((String) sesion.getAttribute("usuario")));

        return "inicio-completado";
    }
    @PostMapping("devolver")
    public String devolverPost(){
        return"redirect:/paso1";
    }
    public static String contenidoCookie(String credenciales, HttpSession sesion){
        StringBuilder texto= new StringBuilder();
        HashMap<String, Integer> usuarios= new HashMap<>();
        String[] partesCredenciales = credenciales.split("#");

        for(String usuario1 : partesCredenciales){
            String[] datosUsuario = usuario1.split(":");
            usuarios.put(datosUsuario[0], Integer.parseInt(datosUsuario[1]));
        }
        if(!usuarios.containsKey((String) sesion.getAttribute("usuario"))){
            usuarios.put((String)sesion.getAttribute("usuario"), 1);
        }else{
            usuarios.put((String)sesion.getAttribute("usuario"), usuarios.get((String) sesion.getAttribute("usuario"))+1);
        }
        for(Map.Entry<String, Integer>usuario1 : usuarios.entrySet()){
            texto.append(usuario1.getKey()).append(":").append(usuario1.getValue()).append("#");
            sesion.setAttribute(usuario1.getKey(), usuario1.getValue());
        }
        sesion.setAttribute("usuariosIniciados",usuarios);
        return texto.toString();
    }
}