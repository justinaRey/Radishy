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
import egg.Radishy.Servicios.MailService;

/**
 *
 * @author usuario
 */
@Controller
@RequestMapping("/usuario")
public class Usuario_controller { // falta agregarle lo q pasaría si se quiere acceder a la dirección puesta en la barra de navegación pero aún no se cumplen con los requisitos para hacerlo

    @Autowired // uS ---> servicio del usuario
    private Usuario_servicio uS;

    @Autowired // uS ---> servicio del usuario
    private MailService mS;

    @Autowired
    private CultivoDeUsuario_servicio cuS;

    @GetMapping("")
    String mostrarUsuario() {
//        **Falta la página que muestra los datos del usuario y te da la opción de modificarlo/eliminarlo
        return "usuario";
    }

    @GetMapping("/registrar")
    String registrarUsuario(ModelMap modelo) {
        modelo.put("generos", Genero.values());
        modelo.put("localidades", Localidad.values());
        return "nuevoUsuario";
    }

    @PostMapping("/registro")
//    **En el HTML, no está la doble contraseña.
    public String guardarRegistro(ModelMap modelo, @RequestParam String nombre,@RequestParam String apellido, @RequestParam String password, @RequestParam String password2, @RequestParam String email, @RequestParam String apodo, @RequestParam Genero genero, @RequestParam Localidad localidad) {

        try {
            uS.nuevoUsuario(nombre, apellido , password, password2,apodo, email, genero, localidad);
            mS.enviarMail(email, "Registro", "Confimarcion de usuario");
        } catch (Errores_servicio ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("apellido", apellido);
            modelo.put("email", email);
            modelo.put("apodo", apodo);
            modelo.put("localidades", Localidad.values());
            modelo.put("generos", Genero.values());
            Logger.getLogger(Usuario_controller.class.getName()).log(Level.SEVERE, null, ex);
            return "nuevousuario";
        }
        return "redirect:/login";
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
    public String modificarUsuario(ModelMap modelo) {
        return "modificarusuario";
    }

    @PostMapping("modificacion")
    public String guardarModificacion(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String password2, @RequestParam String password3, @RequestParam String apodo, @RequestParam String email) {
        try {
            uS.cambiarDatosUsuarioSinId(nombre,apellido, password2, password3, apodo, email); // hay que agregar la passwordNueva2
        } catch (Errores_servicio ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("apodo", apodo);
            modelo.put("email", email);
            modelo.put("apellido", apellido);

            Logger.getLogger(Usuario_controller.class.getName()).log(Level.SEVERE, null, ex);
            return "modificarusuario";
        }
        return "index";
    }

//  Hacer un GetMapping si quieren mostrar los datos del usuario antes de eliminarlo. 
//    @PostMapping("eliminar/{id}")
//    public String eliminarUsuario(@PathVariable String id) {
//        uS.darDeBajaUsuario(id);
////        Si no se agrega un GetMapping, se puede enviar un mensaje de que el usuario se ha eliminado correctamente.
////        modelo.put(mensajeExito, "El usuario se ha eliminado con éxito.")
//        return "redirect:/usuario";
//    }

    @GetMapping("cerrarsesion")
    public String cerrarSesion(ModelMap modelo){
//        try {
            cuS.cerrarSesion();
//        } catch (Errores_servicio ex) {
//            modelo.put("error", ex.getMessage());
//            Logger.getLogger(Usuario_controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return "redirect:/?logout";
    }

    @PostMapping("recuperarpassword")
    public String recuperarPass(ModelMap modelo, @RequestParam String apodo){
        try{
            uS.generarContrasenia(apodo);
        }catch (Errores_servicio ex){
            modelo.put("error", ex.getMessage());

            Logger.getLogger(Usuario_controller.class.getName()).log(Level.SEVERE, null, ex);
            return "probando";
        }
        return "redirect:/";
    }

    @GetMapping("probando")
    public String probando(){
        return "olvidastetucontrasenia";
    }
}