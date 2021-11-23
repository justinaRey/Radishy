/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.Radishy.Servicios;

import egg.Radishy.Entidades.Cultivo;
import egg.Radishy.Entidades.CultivoDeUsuario;
import egg.Radishy.Entidades.Usuario;
import egg.Radishy.Errores.Errores_servicio;
import egg.Radishy.Repositorios.CultivoDeUsuario_repositorio;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 *
 * @author usuario
 */
@Service
public class CultivoDeUsuario_servicio { // no la revisé xq la hice yo, pero tiene errores
    
   // corregir método de mis cultivos
    
    @Autowired
    CultivoDeUsuario_repositorio repositorioCultUsu;
    
    @Autowired 
    Cultivo_servicio cultivoServicio;
    
    @Autowired 
    Usuario_servicio usuarioServicio;
    
    
    ////////////////////////////////////////////////////////////////////////////
    /////////////////// SECCIÓN RELACIONADA A LOS CULTIVOS /////////////////////
    ////////////////////////////////////////////////////////////////////////////
    
    
    /////////////////////// Eliminación de los cultivos ////////////////////////
    
    /*  Eliminar todos los cultivos del usuario, formas de hacerlo:  */
    
    // vaciarMisCultivos(): elimina todos los cultivos pertenecientes al usuario
    public void vaciarMisCultivos () throws Errores_servicio{
        chequearEsteSesionIniciada();
        Usuario usuario = usuarioServicio.findByEnSesion();
        List<CultivoDeUsuario> sesionUsuario = repositorioCultUsu.findCultivosDelUsuario();
        for (CultivoDeUsuario sesIn : sesionUsuario) {
            eliminarMiCultivo(sesIn.getId());
        }
    }
    
    // MÉTODO QUE NO AGREGÉ XQ EL ANTERIOR QUIZÁ NO SE USE X EL ENsESIÓN
    
    // elimina todos los cultivos del usuario (recibe el id)
    public void eliminarTodosCultivos (String idUsuario) throws Errores_servicio{
        Optional<Usuario> rta = usuarioServicio.findById(idUsuario);
        if (rta.isPresent()){
            Usuario usu = rta.get();
            //List<CultivoDeUsuario> cultivosUsu = repositorioCultUsu.findCultivosDelUsuario();
            List<CultivoDeUsuario> cultivosUsu = repositorioCultUsu.findCultivosDelUsuarioByUsuario(usu);
            if (cultivosUsu.isEmpty()){
                throw new Errores_servicio("No se pueden vaciar sus cultivos ya que usted no poseía cultivos propios");
            }
            repositorioCultUsu.deleteAll(cultivosUsu); // en teoría me borraría todos los que se ingresaron a la lista 'cultivosUsu'
        } else {
            throw new Errores_servicio("No se ha encontrado al usuario del cual se quieren elimianr los cultivos");
        }
    }
    
    /*  Eliminar un cultivo en específico del usuario  */
    
    // eliminarMiCultivo(): elimina al cultivo de la lista de cultivos del usuario seleccionado
    public void eliminarMiCultivo (String idCultUsu) throws Errores_servicio{
        Optional<CultivoDeUsuario> rta = repositorioCultUsu.findById(idCultUsu);
        if (rta.isPresent()){
            repositorioCultUsu.delete(rta.get());
        } else {
            throw new Errores_servicio("No se encontró el cultivo que desea eliminar de sus cultivos");
        }
    }
    
    ////////////////////// Incorporación de los cultivos ///////////////////////
    
    /*  Agrega un cultivo al usuario  */
    
    // agregarMiCultivo(): recibe el id del cultivo
    public void agregarMiCultivo (String idCultivo, java.sql.Date fechaSembrado) throws Errores_servicio{
        chequearEsteSesionIniciada();
        if (idCultivo != null && fechaSembrado != null){
            CultivoDeUsuario sesion = new CultivoDeUsuario();
            Cultivo cultivo = cultivoServicio.findByIdGet(idCultivo);
            Usuario usuario = usuarioServicio.findByEnSesion();
            sesion.setCultivo(cultivo);
            sesion.setUsuario(usuario);
            sesion.setFechaDeSembrado(fechaSembrado);
            repositorioCultUsu.save(sesion);
        } else {
            throw new Errores_servicio("Todos los campos deben estar completos, revise nuevamente el formulario");
        }
    }
    
    // MÉTODO QUE YO AGREGUÉ XQ MEPA QUE ESTÁ MAL EL QUE HICE ANTES
    
    // crea el 'cultivoDeUsuario' recibiendo los id del cultivo y usuario
    public CultivoDeUsuario incorporarMiCultivo (String idCultivo, String idUsuario, Date fechaSembrado) throws Errores_servicio{
        validarDatos(idUsuario, idCultivo, fechaSembrado);
        Optional<Usuario> rtau = usuarioServicio.findById(idUsuario);
        if (!rtau.isPresent()){
            throw new Errores_servicio("El usuario ingresado no existe/no se ha logueado");
        }
        Optional<Cultivo> rtac = cultivoServicio.findById(idUsuario);
        if (!rtac.isPresent()){
            throw new Errores_servicio("El cultivo ingresado no existe.\nSi desea puede ir a agregarlo o seleccionar otro cultivo de los que le presentamos toda la información");
        }
        Usuario usu = rtau.get();
        Cultivo cult = rtac.get();
        CultivoDeUsuario cdu = new CultivoDeUsuario();
        cdu.setCultivo(cult);
        cdu.setFechaDeSembrado(fechaSembrado);
        cdu.setUsuario(usu);
        return repositorioCultUsu.save(cdu);
    }
    
    ///////////////////////// Muestra de los cultivos //////////////////////////
    
    // misCultivos(): devuelve una lista con todos los cultivos que posee el usuario en sesión
    public List<CultivoDeUsuario> misCultivos() throws Errores_servicio{
        chequearEsteSesionIniciada();
        List<CultivoDeUsuario> cultivosUsu = repositorioCultUsu.findCultivosDelUsuario();
        if (cultivosUsu.isEmpty()){
            throw new Errores_servicio("No posee cultivos aún");
        } else {
            return cultivosUsu;
        }
    }
    
    // MÉTODO QUE YO HAGO XQ EL ANTERIOR PUEDE ESTAR MAL (X LA VALIDACIÓN)
    
    public List<CultivoDeUsuario> verCultivosUsuario (String idUsu)throws Errores_servicio{
        Optional<Usuario> rta = usuarioServicio.findById(idUsu);
        if (rta.isPresent()){
            Usuario usu = rta.get();
            List<CultivoDeUsuario> cdu = repositorioCultUsu.findCultivosDelUsuarioByUsuario(usu);
            if(cdu.isEmpty()){
                throw new Errores_servicio("Aún no posee cultivos vinculados a su cuenta");
            }
            return cdu;
        } else {
            throw new Errores_servicio("No ha iniciado sesión o su usuario no existe.\nPor favor, vaya a iniciar sesión o registrarse para poder proceder");
        }
    }
    
    ///////////////////////// Modificación de cultivo //////////////////////////
    
    // modifica al cultivo seleccionado (fecha de sembrado, ya que es el único atributo que puede modificarse)
    public CultivoDeUsuario modificarCDUfechaSembrado (String idCDU, Date fechaSem)throws Errores_servicio{
        if (idCDU == null) {
            throw new Errores_servicio("No ha ingresado cuál de sus cultivos es el que pretende modificar");
        }
        Optional<CultivoDeUsuario> rta = repositorioCultUsu.findById(idCDU);
        if (rta.isPresent()){
            CultivoDeUsuario cdu = rta.get();
            if (fechaSem != null) {
                cdu.setFechaDeSembrado(fechaSem);
            }
        return repositorioCultUsu.save(cdu);
        } else {
            throw new Errores_servicio("No se ha logrado encontrar al cultivo seleccionado dentro de los cultivos que posee en su usuario");
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    ///////////////// SECCIÓN DEDICADA A LOS DATOS DEL USUARIO /////////////////
    ////////////////////////////////////////////////////////////////////////////
    
    //////////////////////// Inicio de la sesión (mal) /////////////////////////
    
    // iniciarSesion(): inicia la sesión del usuario según los datos ingresados
    public void iniciarSesion(String usuario, String password) throws Errores_servicio {
        chequeoEstenSesionesCerradas();
        if (usuario != null) {
            if (usuarioServicio.cantidadUsuariosNombre(usuario) != 0) {
                Usuario usu = usuarioServicio.findByNombreUsuario(usuario);
                if (password != null){
                    String contrasenia = usu.getPassword();
                    if (contrasenia.equals(password)){
                        usu.setEnSesion(true);
                        usuarioServicio.save(usu);
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
    
    //////////////////////// Cierre de la sesión (mal) /////////////////////////
    
    // cerrarSesion(): cierra la sesion del usuario que esté abierta
    public void cerrarSesion (){
        //chequearEsteSesionIniciada();
        if (usuarioServicio.cantidadEnSesionTrue() != 0) {
        Usuario usuario = usuarioServicio.findByEnSesion();
        usuario.setEnSesion(false);
        usuarioServicio.save(usuario);
        }
//        } else {
//            throw new Error_servicio_("No se puede cerrar sesión si no hay ninguna sesión iniciada");
//        }   
    }
    
    
    
    /////////////////// Validaciones sobre estado del usuario //////////////////
    
    /*            control de que no haya ninguna sesión iniciada              */
    
    // chequeoEstenSesionesCerradas(): corrobora que no haya ninguna sesión iniciada
    public void chequeoEstenSesionesCerradas() throws Errores_servicio{
        if (usuarioServicio.cantidadEnSesionTrue() != 0){
            throw new Errores_servicio("Ya hay una sesión iniciada.\nCiérrela para poder iniciar sesión con otra cuenta de usuario.");
        }
    }
    
    /*               control de que haya una sesión iniciada                  */
    
    // chequearEsteSesionIniciada(): corrobora que haya una sesión iniciada
    public void chequearEsteSesionIniciada () throws Errores_servicio{
        int cantSesIn = usuarioServicio.cantidadEnSesionTrue();
        if (cantSesIn != 1){
            if (cantSesIn < 1) {
                throw new Errores_servicio("No ha iniciado sesión");
            } else {
                List<Usuario> sesionesIniciadas = usuarioServicio.findSesionesIniciadas();
                for (Usuario sesionIniciada : sesionesIniciadas) {
                    cerrarSesiones(sesionIniciada);
                }
                throw new Errores_servicio("No ha iniciado sesión");
            }
        }
    }
    
    /*                cierre de todas las sesiones iniciadas                  */
    
    // cerrarSesiones(): se utiliza en el chequeo de las sesiones iniciadas, recibe un usuario que debe cerrarle la sesion
    public void cerrarSesiones (Usuario u) {
        u.setEnSesion(false);
        usuarioServicio.save(u);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    ///////////////////  SECCIÓN DE VALIDACIONES DE DATOS  /////////////////////
    ////////////////////////////////////////////////////////////////////////////
    
    // corrobora que todos los parámetros sean no nulos
    public void validarDatos(String idUsu, String idCult, Date fechaSem) throws Errores_servicio{
        if (idUsu == null) {
            throw new Errores_servicio("No ha iniciado sesión!\nDebe ir a iniciarla, o registrase en caso de no poseer una, para poder agregar un cultivo a su cuenta");
        }
        if (idCult == null){
            throw new Errores_servicio("No ha ingresado el cultivo que quiere agregar a sus cultivos");
        }
        if (fechaSem == null){
            throw new Errores_servicio("No ha ingresado la fecha de sembrado de su cultivo");
        }
    }
    /////////////////////// Eliminación de los cultivos ////////////////////////

    public java.sql.Date convertirADate(String fecha) throws Exception{
//        Calendar now = Calendar.getInstance();
//        int anio = Integer.parseInt(fecha.substring(0, 3));
//        int mes = Integer.parseInt(fecha.substring(5, 6));
//        int dia = Integer.parseInt(fecha.substring(8, 9));
//        now.set(anio, mes, dia);
//        
//        System.out.println(now);
        //Date date2 = new SimpleDateFormat("YYYY-MM-dd").parse("2021-11-10");
        java.sql.Date date = java.sql.Date.valueOf( fecha );
//        System.out.println(anio);
//        System.out.println(mes);
//        System.out.println(dia);
        return date;
    }
}