/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.Radishy.Controladores;

import egg.Radishy.Errores.Errores_servicio;
import egg.Radishy.Servicios.SesionIniciada_servicio;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author usuario
 */
@Controller
@RequestMapping("/sesion")
public class SesionIniciada_controller {
  
   @Autowired
    private SesionIniciada_servicio servicio;
    
   @GetMapping("/sesion")
   public String index(){
       return "index.html";
   }
   
    /*           controladores para regular el inicio de la sesión            */ 
    
    @GetMapping("/sesion/iniciar")
    public String iniciarSesion(){
        return "usuario.html";
    }
    
    @PostMapping("/sesion/iniciada")
    public String iniciar_sesion(ModelMap modelo, @RequestParam String nusuario, @RequestParam String password){
        try {
            servicio.iniciarSesion(nusuario, password);
        } catch (Errores_servicio ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("usuario", nusuario);
            Logger.getLogger(SesionIniciada_controller.class.getName()).log(Level.SEVERE, null, ex);
            return "usuario.hmtl";
        }
        return "redirect:/sesion";
    }
    
    
    /*             controlador para cerrar la sesión del usuario              */
    
    @GetMapping("/sesion/iniciada/cerrar")
    public String cerrarSesion(ModelMap modelo) {
        try {
            servicio.cerrarSesion();
        } catch (Errores_servicio ex) {
            modelo.put("error", ex.getMessage());
            Logger.getLogger(SesionIniciada_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/"; //index.html si no es con redirect
    }
    
    
    /*      controlador que agrega un cultivo a los cultivos del usuario      */
    
    @GetMapping("/sesion/iniciada/agregar-mis-cultivos")
    public String agregarMiCultivo(){
        return "aggCultivo.html";
    }
    
    @PostMapping("/sesion/iniciada/agregado-mis-cultivos")
    public String agregadoMiCultivo(ModelMap modelo, @RequestParam String idCultivo, @RequestParam Date fechaSembrado){
        try {
            servicio.agregarMiCultivo(idCultivo, fechaSembrado);
            modelo.put("confirmacion", "El cultivo seleccionado se ha agregado a sus cultivos con éxito"); //aviso de cultivo agregado
        } catch (Errores_servicio ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("idCultivo", idCultivo);
            modelo.put("fechaSembrado", fechaSembrado);
            Logger.getLogger(SesionIniciada_controller.class.getName()).log(Level.SEVERE, null, ex);
            return "aggCultivo.html";
        }
        return "redirect:/sesion/iniciada/agregar-mis-cultivos"; //aggCultivo.html si no es con redirect
    }
    
    
    /*              controlador para el listado de los cultivos               */
    
    @GetMapping("/sesion/iniciada/mis-cultivos")
    public String verMisCultivos(ModelMap modelo){
        try {
            servicio.misCultivos();
        } catch (Errores_servicio ex) {
            modelo.put("error", ex.getMessage());
            Logger.getLogger(SesionIniciada_controller.class.getName()).log(Level.SEVERE, null, ex);
            return "redirect:/sesion/iniciar"; // usuario.html si no va redirect
        }
        return "misCultivos";
    }
    
    /*    controlador para eliminar un cultivo del listado de los cultivos    */
    
    @GetMapping("/sesion/iniciada/mis-cultivos/eliminar-cultivo")
    public String eliminarCultivo(ModelMap modelo, @RequestParam String idSesIn){
        try {
            servicio.eliminarMiCultivo(idSesIn);
            modelo.put("confirmacion", "El culivo seleccionado ha sido eliminado de sus cultivos con éxito");
        } catch (Errores_servicio ex) {
            modelo.put("error", ex.getMessage());
            Logger.getLogger(SesionIniciada_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "misCultivos";
    }
    
    
    /*   controlador para eliminar todos los cultivos que posee el usuario    */
    
    @GetMapping("/sesion/iniciada/mis-cultivos/eliminar-todos-cultivos")
    public String eliminarTodosCultivos(ModelMap modelo){
        try {
            servicio.vaciarMisCultivos();
        } catch (Errores_servicio ex) {
            modelo.put("error", ex.getMessage());
            Logger.getLogger(SesionIniciada_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "misCultivos.hmtl";
    }
}