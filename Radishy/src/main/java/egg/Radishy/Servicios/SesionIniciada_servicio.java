/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.Radishy.Servicios;

import egg.Radishy.Entidades.Cultivo;
import egg.Radishy.Entidades.SesionIniciada;
import egg.Radishy.Entidades.Usuario;
import egg.Radishy.Errores.Errores_servicio;
import egg.Radishy.Repositorios.Cultivo_repositorio;
import egg.Radishy.Repositorios.SesionIniciada_repositorio;
import egg.Radishy.Repositorios.Usuario_repositorio;
import java.util.Date;
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
    
   // corregir método de mis cultivos
    @Autowired
    Usuario_repositorio usuarioRepositorio;
    
    @Autowired
    SesionIniciada_repositorio repositorioSesIn;
    
    @Autowired
    Cultivo_repositorio cultivoRepositorio;
    
    // vaciarMisCultivos(): elimina todos los cultivos pertenecientes al usuario
    public void vaciarMisCultivos () throws Errores_servicio{
        chequearEsteSesionIniciada();
        Usuario usuario = usuarioRepositorio.findByEnSesion();
        List<SesionIniciada> sesionUsuario = repositorioSesIn.cultivosUsuario();
        for (SesionIniciada sesIn : sesionUsuario) {
            eliminarMiCultivo(sesIn.getId());
        }
    }
    
    // eliminarMiCultivo(): elimina al cultivo de la lista de cultivos del usuario seleccionado
    public void eliminarMiCultivo (String idSesIn) throws Errores_servicio{
        chequearEsteSesionIniciada();
        Optional<SesionIniciada> rta = repositorioSesIn.findById(idSesIn);
        if (rta.isPresent()){
            repositorioSesIn.delete(rta.get());
        } else {
            throw new Errores_servicio("No se encontró el cultivo que desea eliminar de sus cultivos");
        }
    }
    
    // agregarMiCultivo(): recibe el id de
    public void agregarMiCultivo (String idCultivo, Date fechaSembrado) throws Errores_servicio{
        chequearEsteSesionIniciada();
        if (idCultivo != null && fechaSembrado != null){
            SesionIniciada sesion = new SesionIniciada();
            Cultivo cultivo = cultivoRepositorio.findById(idCultivo).get();
            Usuario usuario = usuarioRepositorio.findByEnSesion();
            sesion.setCultivo(cultivo);
            sesion.setUsuario(usuario);
            sesion.setFechaDeSembrado(fechaSembrado);
            repositorioSesIn.save(sesion);
        } else {
            throw new Errores_servicio("Todos los campos deben estar completos, revise nuevamente el formulario");
        }
    }
    
    // misCultivos(): devuelve una lista con todos los cultivos que posee el usuario en sesión
    public List<SesionIniciada> misCultivos() throws Errores_servicio{
        chequearEsteSesionIniciada();
        List<SesionIniciada> cultivosSesIn = repositorioSesIn.cultivosUsuario();
        if (cultivosSesIn.isEmpty()){
            throw new Errores_servicio("No posee cultivos aún");
        } else {
            return cultivosSesIn;
        }
    }
    
    /*         Todos los campos por separado que hacen ha los datos a mostrar por pantalla de 'mis cultivos'        */
    
    public String nombreCultivo (SesionIniciada sesion){
        return sesion.getCultivo().getNombre();
    }
    
    public String metodoCultivo(SesionIniciada sesion){
        return sesion.getCultivo().getMetodo();
    }
    
    public int profundidadCultivo (SesionIniciada sesion) {
        return sesion.getCultivo().getProfundidadSiembraCM();
    }
    
//    public String riegoCultivo (SesionIniciada sesion) {
//        return sesion.getCultivo().getRiego();
//    }
    
    public Date fechaSembradoCultivo (SesionIniciada sesion){
        return sesion.getFechaDeSembrado();
    }
    
//    public Date fechaGerminadoCultivo (SesionIniciada sesion) {
//        return sesion.getFechaGermiadoProm();
//    }
//    
//    public Date fechaTransplanteCultivo (SesionIniciada sesion) {
//        return sesion.getFechaTransplanteProm();
//    }
//    
//    public Date fechaCosechaCultivo (SesionIniciada sesion) {
//        return sesion.getFechaCosechaAprox();
//    }
    
    /*              métodos para iniciar la sesión y cerrarla                 */
    
    // iniciarSesion(): inicia la sesión del usuario según los datos ingresados
    public void iniciarSesion(String usuario, String password) throws Errores_servicio {
        chequeoEstenSesionesCerradas();
        if (usuario != null) {
            if (usuarioRepositorio.cantidadUsuariosNombre(usuario) != 0) {
                Usuario usu = usuarioRepositorio.findByNombreUsuario(usuario);
                if (password != null){
                    String contrasenia = usu.getPassword();
                    if (contrasenia.equals(password)){
                        usu.setEnSesion(true);
                        usuarioRepositorio.save(usu);
                    } else {
                        throw new Errores_servicio("El usuario y/o contraseña introducidos no son correctos");
                    }
                } else {
                    throw new Errores_servicio("No ha introducido ninguna contraseña");
                }
            } else {
                throw new Errores_servicio("No se ha encontrado ningún usuario con ese 'nombre de usuario'");
            }
        } else {
            throw new Errores_servicio("No ha introducido un nombre de usuario");
        }
    }
    
    // cerrarSesion(): cierra la sesion del usuario que esté abierta
    public void cerrarSesion () throws Errores_servicio{
        chequearEsteSesionIniciada();
    //    if (usuarioRepositorio.cantidadEnSesionTrue() != 0) {
        Usuario usuario = usuarioRepositorio.findByEnSesion();
        usuario.setEnSesion(false);
        usuarioRepositorio.save(usuario);
    //    } else {
    //        throw new Error_servicio_("No se puede cerrar sesión si no hay ninguna sesión iniciada");
    //    }   
    }
    
    
    
    /*               control de que estén todas las sesiones cerradas         */
    
    // chequeoEstenSesionesCerradas(): corrobora que no haya ninguna sesión iniciada
    public void chequeoEstenSesionesCerradas() throws Errores_servicio{
        if (usuarioRepositorio.cantidadEnSesionTrue() != 0){
            throw new Errores_servicio("Ya hay una sesión iniciada.\nCiérrela para poder iniciar sesión con otra cuenta de usuario.");
        }
    }
    
    
    
    /*               control de que haya una sesión iniciada                  */
    
    // chequearEsteSesionIniciada(): corrobora que haya una sesión iniciada
    public void chequearEsteSesionIniciada () throws Errores_servicio{
        int cantSesIn = usuarioRepositorio.cantidadEnSesionTrue();
        if (cantSesIn != 1){
            if (cantSesIn < 1) {
                throw new Errores_servicio("No ha iniciado sesión");
            } else {
                List<Usuario> sesionesIniciadas = usuarioRepositorio.findSesionesIniciadas();
                for (Usuario sesionIniciada : sesionesIniciadas) {
                    cerrarSesiones(sesionIniciada);
                }
                throw new Errores_servicio("No ha iniciado sesión");
            }
        }
    }
    
    // cerrarSesiones(): se utiliza en el chequeo de las sesiones iniciadas, recibe un usuario que debe cerrarle la sesion
    public void cerrarSesiones (Usuario u) {
        u.setEnSesion(false);
        usuarioRepositorio.save(u);
    }
}