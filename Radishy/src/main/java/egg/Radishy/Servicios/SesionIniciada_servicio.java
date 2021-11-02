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
import egg.Radishy.entidades.Usuario;
import java.util.List;
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
//    public void agregarMiCultivo (String idUsuario, String idCultivo) {
//        
//        Usuario usuario = usuarioRepositorio.findById(idUsuario).get();
//        
//        
//    }
    
    // misCultivos(): devuelve una lista con todos los cultivos que el usuario posee
//    public List<Cultivo> misCultivos () {
//        return null;
//    }
    
    // iniciarSesion(): recibe el nombre de usuario y la contraseña por parte del 'usuario' y controla que se pueda iniciar la sesión y los datos ingresados sean correctos
    public void iniciarSesion (String usuario, String password) throws Errores_servicio{
        // if ('query que cuente la cantidad de usarios con enSesion true' < 1) {
        //  'query que busque al usuario en el repositorio con el nombre de usuario' ---> findByNombreUsuario(nombreUsuario)
        //  Usuario datosUsuario = usuarioRepositorio.findByNombreUsuario(usuario);
        //  if (usuario.getPassword() == password) {
        //      usuario.setEnSesio(true);
        //  } else {
        //      throw new Errores_servicio("La contraseña ingresada no corresponde al nombre de usuario ingresado");
        // } else {
        //  throw new Errores_servicio("No se puede tener dos sesiones ingresadas a la vez, por favor cierre la sesión en curso antes de iniciar una nueva");
    }
    
//    public void editarCuenta (String idUsuario, String usuario, String password, String apodo) {
//        
//        Usuario usuario2 = usuarioRepositorio.findById(idUsuario).get();
//        
//    }
    
//    public void validarDatos (String usuario, String password, String apodo) throws Errores_servicio{
//        if (usuario == null) {
//            throw new Errores_servicio("No ha ingresado un nombre de usuario");
//        }
//        if (password == null) {
//            throw new Errores_servicio("No ha ingresado una contraseña");
//        }
//    }
    
    // incorporarle un boolean al usuario de 'en sesion' para que sea false siempre, salvo cuando ese usuario está en sesión
    
    // cerrarSesion(): recibe el id del Usuario activo y le cierra la sesión
    public void cerrarSesion (String idUsuario) {
        Usuario usuario = usuarioRepositorio.findById(idUsuario).get();
        usuario.setEnSesion(false);
    }
}
