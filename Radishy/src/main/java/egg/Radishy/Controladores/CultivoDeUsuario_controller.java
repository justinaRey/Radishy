/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.Radishy.Controladores;

import egg.Radishy.Entidades.CultivoDeUsuario;
import egg.Radishy.Errores.Errores_servicio;
import egg.Radishy.Servicios.CultivoDeUsuario_servicio;
import egg.Radishy.Servicios.Cultivo_servicio;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Calendar;
/**
 *
 * @author usuario
 */
@Controller
@RequestMapping("/sesion")
public class CultivoDeUsuario_controller {
  
   @Autowired
    private CultivoDeUsuario_servicio servicio;

    @Autowired
    private Cultivo_servicio cultivoServicio;
    
   @GetMapping("/")
   public String index(){
       return "index";
   }
   
    /*           controladores para regular el inicio de la sesión            */ 
    
    @GetMapping("/iniciar")
    public String iniciarSesion(){
        return "usuario";
    }
    
    @PostMapping("/iniciada")
    public String iniciar_sesion(ModelMap modelo, @RequestParam String nusuario, @RequestParam String password){
        try {
            servicio.iniciarSesion(nusuario, password);
        } catch (Errores_servicio ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("usuario", nusuario);
            Logger.getLogger(CultivoDeUsuario_controller.class.getName()).log(Level.SEVERE, null, ex);
            return "usuario";
        }
        return "redirect:/sesion";
    }
    
    
    /*             controlador para cerrar la sesión del usuario              */
    
    @GetMapping("/iniciada/cerrar")
    public String cerrarSesion(ModelMap modelo) {
        servicio.cerrarSesion();
        return "redirect:/"; //index.html si no es con redirect
    }
    
    
    /*      controlador que agrega un cultivo a los cultivos del usuario      */
    
    @GetMapping("/iniciada/agregar-mis-cultivos")
    public String agregarMiCultivo(ModelMap modelo){
        modelo.put("cultivos", cultivoServicio.listarCultivos());
        return "aggCultivo";
    }
    
    @PostMapping("/iniciada/agregado-mis-cultivos")
    public String agregadoMiCultivo(ModelMap modelo, @RequestParam String idCultivo, @RequestParam String fechaSembrado) throws Exception{
        
        try {
            java.sql.Date fechaSemb = servicio.convertirADate(fechaSembrado);
            servicio.agregarMiCultivo(idCultivo, fechaSemb);
            modelo.put("confirmacion", "El cultivo seleccionado se ha agregado a sus cultivos con éxito"); //aviso de cultivo agregado
        } catch (Errores_servicio ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("cultivo", idCultivo);
            modelo.put("fechaSembrado", fechaSembrado);
            Logger.getLogger(CultivoDeUsuario_controller.class.getName()).log(Level.SEVERE, null, ex);
            return "aggCultivo";
        }
        return "redirect:/sesion/iniciada/agregar-mis-cultivos"; //aggCultivo.html si no es con redirect
    }
    
    
    /*              controlador para el listado de los cultivos               */
    
    @GetMapping("/iniciada/mis-cultivos")
    public String verMisCultivos(ModelMap modelo){
        try {
            List<CultivoDeUsuario> misCultivos = servicio.misCultivos();
            modelo.put("cultivosSesIn", misCultivos);
        } catch (Errores_servicio ex) {
            modelo.put("error", ex.getMessage());
            Logger.getLogger(CultivoDeUsuario_controller.class.getName()).log(Level.SEVERE, null, ex);
            return "redirect:/sesion/iniciar"; // usuario.html si no va redirect
        }
        return "misCultivos";
    }
    
    /*    controlador para eliminar un cultivo del listado de los cultivos    */
    
    @GetMapping("/iniciada/mis-cultivos/eliminar-cultivo")
    public String eliminarCultivo(ModelMap modelo, @RequestParam String idSesIn){
        try {
            servicio.eliminarMiCultivo(idSesIn);
            modelo.put("confirmacion", "El culivo seleccionado ha sido eliminado de sus cultivos con éxito");
        } catch (Errores_servicio ex) {
            modelo.put("error", ex.getMessage());
            Logger.getLogger(CultivoDeUsuario_controller.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CultivoDeUsuario_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "misCultivos";
    }
}
