package egg.Radishy.Controladores;

import egg.Radishy.Entidades.Usuario;
import egg.Radishy.Enumeraciones.Genero;
import egg.Radishy.Enumeraciones.Localidad;
import egg.Radishy.Errores.Errores_servicio;
import egg.Radishy.Servicios.Usuario_servicio;
import egg.Radishy.Servicios.CultivoDeUsuario_servicio;
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
public class Usuario_controller { // falta agregarle lo q pasaría si se quiere acceder a la dirección puesta en la barra de navegación pero aún no se cumplen con los requisitos para hacerlo

    @Autowired // uS ---> servicio del usuario
    private Usuario_servicio uS;

    @Autowired
    private CultivoDeUsuario_servicio cuS;

    @GetMapping("")
    String mostrarUsuario() {
//        **Falta la página que muestra los datos del usuario y te da la opción de modificarlo/eliminarlo
        return "usuario";
    }

    @GetMapping("/registrar")
    String registrarUsuario(ModelMap modelo) {
        modelo.put("genero", Genero.values());
        modelo.put("localidad", Localidad.values());
        return "nuevoUsuario";
    }

    @PostMapping("/registro")
//    **En el HTML, no está la doble contraseña.
    public String guardarRegistro(ModelMap modelo, @RequestParam String nombre, @RequestParam String pass, @RequestParam String pass2, @RequestParam String email, @RequestParam String apodo, @RequestParam Genero genero, @RequestParam Localidad localidad) {

        try {
            uS.nuevoUsuario(nombre, pass, pass2, email, apodo, genero, localidad);
        } catch (Errores_servicio ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);
            modelo.put("apodo", apodo);
            modelo.put("genero", genero);
            modelo.put("localidad", localidad);

            Logger.getLogger(Usuario_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/usuario";
    }

//    @GetMapping("/modificar/{id}")
//    public String modificarUsuario(ModelMap modelo, @PathVariable String id) {
//        modelo.put("genero", Genero.values());
//        modelo.put("localidad", Localidad.values());
//        Usuario usuario;
//        try {
//            usuario = uS.buscarPorId(id);
//            modelo.put("usuario", usuario);
//        } catch (Errores_servicio ex) {
//            Logger.getLogger(Usuario_controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return "";
//    }
    @GetMapping("/modificar")
    public String modificarUsuario(ModelMap modelo, @PathVariable String id) {
        return "modificarusuario";
    }

    @PostMapping("modificacion/{id}")
    public String guardarModificacion(ModelMap modelo, @PathVariable String id, @RequestParam String nombre, @RequestParam String passActual, @RequestParam String passNew, @RequestParam String passNew2, @RequestParam String apodo, @RequestParam String email, @RequestParam Genero genero, @RequestParam Localidad localidad) {
        try {
            uS.cambiarDatosUsuario(id, nombre, passActual, passNew, passNew2, apodo, email, genero, localidad); // hay que agregar la passwordNueva2
        } catch (Errores_servicio ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("apodo", apodo);
            modelo.put("email", email);
            modelo.put("genero", genero);
            modelo.put("localidad", localidad);

            Logger.getLogger(Usuario_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/usuario";
    }

//  Hacer un GetMapping si quieren mostrar los datos del usuario antes de eliminarlo. 
//    @PostMapping("eliminar/{id}")
//    public String eliminarUsuario(@PathVariable String id) {
//        uS.darDeBajaUsuario(id);
////        Si no se agrega un GetMapping, se puede enviar un mensaje de que el usuario se ha eliminado correctamente.
////        modelo.put(mensajeExito, "El usuario se ha eliminado con éxito.")
//        return "redirect:/usuario";
//    }

    @PostMapping("cerrarsesion")
    public void cerrarSesion(ModelMap modelo){
        try {
            cuS.cerrarSesion();
        } catch (Errores_servicio ex) {
            modelo.put("error", ex.getMessage());
            Logger.getLogger(Usuario_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}