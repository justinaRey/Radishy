package egg.Radishy.Servicios;

import egg.Radishy.Repositorios.Usuario_repositorio;
import egg.Radishy.Entidades.Usuario;
import egg.Radishy.Enumeraciones.Genero;
import egg.Radishy.Enumeraciones.Localidad;
import egg.Radishy.Errores.Errores_servicio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Usuario_servicio { //OBS: ver modificarUsuario() para q si o sí deba ingresar password actual p/conf cambios y el tema del encriptado de la contraseña; validar() mal usado en modificarUsuario

    @Autowired  // uR ---> repositorio del usuario
    private Usuario_repositorio uR;

    @Autowired // encoder ---> relacionado a la seguridad
    private BCryptPasswordEncoder encoder;
    
    @Transactional
    public Usuario crearUsuario(String nombre, String password, String apodo, Genero genero, Localidad localidad) throws Errores_servicio {
        validar(nombre, password, apodo, genero, localidad);
        
        Usuario u = new Usuario();
        
        u.setNombre(nombre);
        //Encriptamos el password con el metodo encode
        u.setPassword(encoder.encode(password)); 
        u.setApodo(apodo);
        u.setGenero(genero);
        u.setLocalidad(localidad);
        //comentados xq ya vienen en el constructor marcados de la siguiente manera
//        u.setAlta(Boolean.TRUE);
//        u.setRol(Roles.USER);
        return uR.save(u);
    }

    @Transactional
    public Usuario modificarUsuario(String id, String nombre, String passwordNueva, String passwordActual, String apodo, Genero genero, Localidad localidad) throws Errores_servicio { //comparar contraseña actual ing con contraseña del usuario
        Usuario u;
        try { 
            validar(nombre, passwordActual, apodo, genero, localidad); // este está mal puesto, las validaciones se deben hacer en este método, a excepción del de la contraseña actual
            Optional<Usuario> rta = uR.findById(id);
            if (rta.isPresent()) {
                u = uR.findById(id).get();
                u.setNombre(nombre);
                u.setPassword(passwordNueva);  // no se encripta la contraseña
                u.setApodo(apodo);
                //u.setGenero(genero);
                //u.setLocalidad(localidad);
                u.setAlta(Boolean.TRUE);

            } else {
                throw new Errores_servicio("No se encontró el ID del usuario seleccionado.");
            }
        } catch (Errores_servicio e) {
            System.err.println(e.getMessage());
            return null;
        }
        return uR.save(u);
    }

    @Transactional
    public void darDeBajaUsuario(String id){  // xq try - catch? innecesario, con el if y el throw error en el else está bien nomás
        try {
            Optional<Usuario> rta = uR.findById(id);
            if (rta.isPresent()) {
                Usuario u = rta.get();
                u.setAlta(Boolean.FALSE);
                uR.save(u);
            } else {
                throw new Errores_servicio("No se encontró el ID del usuario seleccionado.");
            }
        } catch (Errores_servicio e) {
            System.err.println(e.getMessage());
        }
    }
    
    private void validar(String nombre, String password, String apodo, Genero genero, Localidad localidad) throws Errores_servicio { // chequear por las dudas
        try {
            if (nombre == null || nombre.isEmpty()) {
                throw new Errores_servicio("El nombre ingresado no es válido.");
            }
            if (password == null || password.isEmpty()) {
                throw new Errores_servicio("La contraseña ingresada no es válida.");
            }
            if (apodo == null || apodo.isEmpty()) {
                throw new Errores_servicio("El apodo ingresado no es válido.");
            }
            if (genero == null) {
                throw new Errores_servicio("El género ingresado no es válido.");
            }
            if (localidad == null) {
                throw new Errores_servicio("La localidad ingresada no es válida.");
            }
        } catch (Errores_servicio e) {
            System.err.println(e.getMessage());
        }
    }
    
    public Usuario buscarPorId(String id) throws Errores_servicio {
        Usuario u = uR.buscarPorId(id);
        return u;
    }
    
        //Método para buscar usuario por nombre
    public Usuario findByNombreUsuario(String nombre){
       Usuario usuario = uR.findByNombreUsuario(nombre);
        return usuario;
    }
}
