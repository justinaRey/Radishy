package egg.Radishy.Servicios;

import egg.Radishy.Repositorios.Usuario_repositorio;
import egg.Radishy.Entidades.Usuario;
import egg.Radishy.Enumeraciones.Genero;
import egg.Radishy.Enumeraciones.Localidad;
import egg.Radishy.Errores.Errores_servicio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

public class Usuario_servicio {

    @Autowired
    private Usuario_repositorio uR;

    @Transactional
    public Usuario crearUsuario(String nombre, String password, String apodo, Genero genero, Localidad localidad) throws Errores_servicio {
        validar(nombre, password, apodo, genero, localidad);
        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setPassword(password);
        u.setApodo(apodo);
        u.setGenero(genero);
        u.setLocalidad(localidad);
        u.setAlta(Boolean.TRUE);

        return uR.save(u);
    }

    @Transactional
    public Usuario modificarUsuario(String id, String nombre, String password, String apodo, Genero genero, Localidad localidad) throws Errores_servicio {
        Usuario u;
        try {
            validar(nombre, password, apodo, genero, localidad);
            Optional<Usuario> rta = uR.findById(id);
            if (rta.isPresent()) {
                u = uR.findById(id).get();
                u.setNombre(nombre);
                u.setPassword(password);
                u.setApodo(apodo);
                u.setGenero(genero);
                u.setLocalidad(localidad);
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
    public void darDeBajaUsuario(String id){
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
    
    private void validar(String nombre, String password, String apodo, Genero genero, Localidad localidad) throws Errores_servicio {
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
}
