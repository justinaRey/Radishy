
package egg.Radishy.Controladores;

import egg.Radishy.entidades.Usuario;
import egg.Radishy.enumeraciones.Genero;
import egg.Radishy.enumeraciones.Localidad;
import egg.Radishy.errores.ErrorServicio;
import egg.Radishy.servicios.Usuario_servicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author usuario
 */
@Controller
@RequestMapping("/usuario")
public class Usuario_controller {
    
    @Autowired
    private Usuario_servicio uS;
    
    @GetMapping("")
    String mostrarUsuario(){
        return "usuario";
    }
    
    @GetMapping("/registrar")
    String registrarUsuario(ModelMap modelo){
        modelo.put("genero", Genero.values());
        modelo.put("localidad", Localidad.values());
        return "nuevousuario";
    }
    
    
    @PostMapping("/registro")
    public String guardarRegistro(ModelMap modelo, @RequestParam String nombre, @RequestParam String password, @RequestParam String email, @RequestParam String apellido, @RequestParam Genero genero, @RequestParam Localidad localidad) {
        
        try {
            uS.crearUsuario(nombre, password, email, apellido, genero, localidad);
        } catch (ErrorServicio ex) {
            modelo.put("error", ex.getMessage());
            
            Logger.getLogger(Usuario_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/usuario";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificarUsuario(ModelMap modelo, @PathVariable String id){
        modelo.put("genero", Genero.values());
        modelo.put("localidad", Localidad.values());
        Usuario usuario;
        try {
            usuario = uS.buscarPorId(id);
            modelo.put("usuario",usuario);
        } catch (ErrorServicio ex) {
            Logger.getLogger(Usuario_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/usuario";
    }
     
    @PostMapping("modificacion/{id}")
    public String guardarModificacion(ModelMap modelo, @PathVariable String id, @RequestParam String nombre, @RequestParam String password,@RequestParam String email, @RequestParam String apodo, @RequestParam Genero genero, @RequestParam Localidad localidad){
        try {
            uS.modificarUsuario(id, nombre, password, email, apodo, genero, localidad);
        } catch (ErrorServicio ex) {
            modelo.put("error", ex.getMessage());
            
            Logger.getLogger(Usuario_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/usuario";
    }
    
    
//  Hacer un GetMapping si quieren mostrar los datos del usuario antes de eliminarlo. 
    
    @PostMapping("eliminar/{id}")
    public String eliminarUsuario(@PathVariable String id){
        uS.darDeBajaUsuario(id);
        return "redirect:/usuario";
    }
}
