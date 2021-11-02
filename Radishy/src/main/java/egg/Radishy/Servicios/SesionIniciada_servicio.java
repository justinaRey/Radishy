/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.Radishy.Servicios;

import egg.Radishy.Errores.Errores_servicio;
import egg.Radishy.Repositorios.Cultivo_repositorio;
import egg.Radishy.Repositorios.SesionIniciada_repositorio;
import egg.Radishy.Repositorios.Usuario_repositorio;
import egg.Radishy.entidades.Cultivo;
import egg.Radishy.entidades.SesionIniciada;
import egg.Radishy.entidades.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author usuario
 */
@Service
public class SesionIniciada_servicio {
    
    @Autowired
    private SesionIniciada_repositorio repositorioSesIn;
    @Autowired
    private Usuario_repositorio usuarioRepositorio;
    
    @Autowired
    private Cultivo_repositorio cultivoRepositorio;
    
// agregarCultivo(): agrega un cultivo a los que ya posee el usuario
    public void agregarMiCultivo (String idUsuario, String idCultivo) throws Errores_servicio{
        
        Optional<Usuario> rtaus = usuarioRepositorio.findById(idUsuario);
        if (rtaus.isPresent()){
            Usuario usuario = rtaus.get();
            Optional<Cultivo> rtacul = cultivoRepositorio.findById(idCultivo);
            if (rtacul.isPresent()) {
                Cultivo cultivo = rtacul.get();
                //'query de repositorioSesIn que busca la sesionIniciada según el usuario'
                //SesionIniciada sesion = repositorioSesIn.findByUsuario(usuario).get();
                //List<Cultivo> cultivos = sesion.getCultivos();
                //cultivos.add(cultivo);
                //repositorioSesIn.save(sesion);
            } else {
                throw new Errores_servicio("El cultivo que intenta agregar a sus cultivos, no se encuentra guardado en nuestra base de datos.\nSi quiere, puede ir a agregarla a la misma para luego incorporarlo a sus cultivos");
           }
        } else {
            throw new Errores_servicio("No se encontró al usuario.");
        }
        
        
    }
    
// misCultivos(): devuelve una lista con todos los cultivos que el usuario posee (busca con el id de la sesionIniciada)
    public List<Cultivo> misCultivos (String idSesionIniciada) {
        SesionIniciada sesion = repositorioSesIn.findById(idSesionIniciada).get();
        return sesion.getCultivos();
    }

// misCultivosDistintos    
    public List<Cultivo> misCultivosDistinto () throws Errores_servicio{
        //if ('query que cuente la cantidad de usuarios con enSesion = true' == 1) {
        //  'query que busca al usuario segun enSesion = true' ---> findByEnSesion()
        //  Usuario usuario = usuarioRepositorio.findByEnSesion();
        //  'query que busca la sesionIniciada según el usuario (entidad)' ---> findByUsuario(usuario)
        //  SesionIniciada sesion = repositorioSesIn.findByUsuario(usuario);
        //} else {
        //  throw new Errores_servicios("No hay un usuario con la sesión iniciada.\nAntes de poder ver sus cultivos, deberá iniciar sesión.");
        //}
        //return sesion.getCultivos();
        return null;
    }
    

    

    
    // incorporarle un boolean al usuario de 'en sesion' para que sea false siempre, salvo cuando ese usuario está en sesión
    
// iniciarSesion(): recibe el nombre de usuario y la contraseña por parte del 'usuario' y controla que se pueda iniciar la sesión y los datos ingresados sean correctos
    public void iniciarSesion (String usuario, String password) throws Errores_servicio{
        // if ('query que cuente la cantidad de usarios con enSesion true' < 1) {
        //  validarHayaDatosInicioSesio(usuario, password);
        //  'query que busque al usuario en el repositorio con el nombre de usuario' ---> findByNombreUsuario(nombreUsuario)
        //  Usuario datosUsuario = usuarioRepositorio.findByNombreUsuario(usuario);
        //  if (datosUsuario.getPassword() == password) {
        //      datosUsuario.setEnSesio(true);
        //      usuarioRepositorio.save(datosUsuario);
        //  } else {
        //      throw new Errores_servicio("La contraseña ingresada no corresponde al nombre de usuario ingresado");
        // } else {
        //  throw new Errores_servicio("No se puede tener dos sesiones ingresadas a la vez, por favor cierre la sesión en curso antes de iniciar una nueva");
    }
    
    // cerrarSesion(): recibe el id del Usuario activo y le cierra la sesión
    public void cerrarSesion (String idUsuario) {
        Usuario usuario = usuarioRepositorio.findById(idUsuario).get();
        usuario.setEnSesion(false);
    }
    
    // validarHayaDatosInicioSesion(): 
    public void validarHayaDatosInicioSesion(String usuario, String password) throws Errores_servicio {
        if (usuario == null) {
            throw new Errores_servicio("No ha ingresado un nombre de usuario");
        }
        if (password == null) {
            throw new Errores_servicio("No ha ingresado una contraseña");
        }
    }
    
    // editarCuenta(): recibe el id de un usuario (se puede modificar haciendo que se busque al usuario 'enSesion' y sea al que se modifica) y los campos que se le permiten editar al usuario, para modificar los datos de aquellos que ingrese algo
    public void editarCuenta (String idUsuario, String usuario, String passwordActual, String passwordNueva, String apodo) throws Errores_servicio{
        Optional<Usuario> rta = usuarioRepositorio.findById(idUsuario);
        if (rta.isPresent()) {
            Usuario datosUsuario = rta.get();
            if (passwordActual == null) {
                throw new Errores_servicio("No puede guardar ningún cambio si no introduce su contraseña actual");
            }
            if (usuario != null) {
             datosUsuario.setNombre(usuario);
            }
            if (passwordNueva != null) {
                datosUsuario.setPassword(passwordNueva);
            }
            if(apodo != null) {
             datosUsuario.setApodo(apodo);
            }
            usuarioRepositorio.save(datosUsuario);
        } 
    }
    
    // query en repositorio del usuario que cuente la cantidad de usuarios con 'enSesion' = true, y devuelva dicha cantidad 
    // query en repositorio del usuario que busque y devuelva al usuario con 'enSesion' = true  ---> findByEnSesion()
    // query en repositorio del usuario que busca al usuario según el nombre del usuario  ---> findByNombreUsuario()
}
