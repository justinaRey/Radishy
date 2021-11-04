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
@RequestMapping("")
public class SesionIniciada_controller {
  
    @Autowired
    private SesionIniciada_servicio servicio;
    
    // iniciarSesion(): lleva a la pag. html para completar los datos de inicio de sesión
    @GetMapping("/iniciar-sesion(?")
    public String iniciarSesion(){
        return "usuario.hmtl";
    }
    
    // inicioDeSesion(): lo que sucede luego de apretar el botón de "iniciar sesión" (inicio de la sesión y vuelta a pág. pcipal o volver a pedir datos
    @PostMapping("iniciar-sesion")
    public String inicioDeSesion(ModelMap modelo, @RequestParam String usuario, @RequestParam String password){
        try {
            servicio.iniciarSesion(usuario, password);
        } catch (Errores_servicio ex) {
            // envía el mensaje de error a la página (front)
            modelo.put("error", ex.getMessage());
            // recuerda el nombre de usuario ingresado por el usuario en la página(front)
            modelo.put("usuario", usuario);
            Logger.getLogger(SesionIniciada_controller.class.getName()).log(Level.SEVERE, null, ex);
            return "usuario.html";  // vuelve a solicitar los datos al usuario para iniciar sesión
        }
        return "index.html";  // vuelve a la página principal 
    }
    
    // aAgregarCultivo(): lleva a la pág. html para añadir un cultivo al usuario en cuestión.
    @GetMapping("/agregar-cultivo-usuario(?")
    public String aAgregarCultivo(){
        return "aggCultivo.html";
    }
    
    // agregarCultivo(): lo que sucede al apretar 'agregar cultivo' (msj error y permanecer en la misma pág. o ir a los cultivos si se guardó).
    @GetMapping("/agragar-cultivo-usuario")
    public String agregarCultivo(ModelMap modelo, @RequestParam String idCultivo, @RequestParam Date fechaDeSiembra) {
        try {
            servicio.agregarAMisCultivos(idCultivo, fechaDeSiembra);
        } catch (Errores_servicio ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("fechaDeSiembra", fechaDeSiembra);
            Logger.getLogger(SesionIniciada_controller.class.getName()).log(Level.SEVERE, null, ex);
            return "aggCultivo.html";
        }
        return "misCultivos.html";  // posibilidad de llevar a una pág que diga que su cultivo ha sido agregado con éxito
    }
}
