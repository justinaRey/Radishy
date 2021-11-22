package egg.Radishy.Servicios;

import egg.Radishy.Repositorios.Usuario_repositorio;
import egg.Radishy.Entidades.Usuario;
import egg.Radishy.Enumeraciones.Genero;
import egg.Radishy.Enumeraciones.Localidad;
import egg.Radishy.Errores.Errores_servicio;
import java.util.List;
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
    
    ////////////////////////////////////////////////////////////////////////////
    /////////////////////////  Métodos para crear los usuario //////////////////
    ////////////////////////////////////////////////////////////////////////////
    
    //MÉTODO QUE YA ESTABA + MIS COMENTARIOS X MI MODIFICACIÓN EN EL SERVICIO
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
    
    // MÉTODO QUE YO CREE POR MODIFICACIONES Q EN MI OPINIÓN IBAN (todo tiene un porque)
    
    //nuevoUsuario(): registra al usuario nuevo que se registra y valida que cumpla con las condiciones
    @Transactional
    public Usuario nuevoUsuario(String nombre, String password, String password2, String apodo, String email, Genero genero, Localidad localidad) throws Errores_servicio{
        validarDatosCompletos(nombre, password, password2, email, apodo, genero, localidad);
        Usuario usuario = new Usuario();
        usuario.setApodo(apodo);
        usuario.setEmail(email);
        usuario.setGenero(genero);
        usuario.setLocalidad(localidad);
        usuario.setNombre(nombre);
        usuario.setPassword(encoder.encode(password)); //encripta la contraseña ingresaga.
        return uR.save(usuario);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    /////////////////// Métodos para modificar los usuarios ////////////////////
    ////////////////////////////////////////////////////////////////////////////
    
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

    // MÉTODO QUE YO CREE POR MODIFICACIONES Q EN MI OPINIÓN IBAN (todo tiene un porque)
    
    @Transactional // ver tema del encriptado de la contra, en qué me afecta
    public Usuario cambiarDatosUsuario (String id, String nombre, String passAct, String passNew, String passNew2, String apodo, String email, Genero genero, Localidad localidad) throws Errores_servicio{
        Usuario usuario = uR.buscarPorId(id);
        if (passAct == null){
            throw new Errores_servicio("Para guardar los cambios, debe ingresar su contraseña actual");
        }
        if (!passAct.equals(usuario.getPassword())){ // tema de spring security
            throw new Errores_servicio("No ha ingresado correctamente su contraseña, por ende los cambios no pueden guardarse");
        }
        if (nombre != null){
            usuario.setNombre(nombre);
        }
        if (passNew != null || passNew2 != null){
            if (!passNew.equals(passNew2)){
                throw new Errores_servicio("La contraseña nueva ingresada no coincide en ambos campos donde debe ingresarla");
            }
            if(passNew.length() < 6){
                throw new Errores_servicio("Su contraseña no puede tener menos de 6 caracteres");
            }
            usuario.setPassword(passNew);
        }
        if (apodo != null) {
            usuario.setApodo(apodo);
        }
        if (email != null) {
            usuario.setEmail(email);
        }
        if (genero != null){
            usuario.setGenero(genero);
        }
        if (localidad != null){
            usuario.setLocalidad(localidad);
        }
        return uR.save(usuario);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    //////////////////// Métodos para 'eliminar' un usuario/////////////////////
    ////////////////////////////////////////////////////////////////////////////
    
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
    
    // MÉTODO QUE YO CREE POR MODIFICACIONES Q EN MI OPINIÓN IBAN (todo tiene un porque)
    
    // da de baja al usuario
    @Transactional
    public Usuario bajaAusuario(String id) throws Errores_servicio{
        Optional<Usuario> rta = uR.findById(id);
        if (rta.isPresent()){
            Usuario us = rta.get();
            us.setAlta(Boolean.FALSE);
            us.setEnSesion(false);
            return uR.save(us);
        } else {
            throw new Errores_servicio("No se encontró al usuario en cuestión");
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////// Validaciones de datos ///////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    
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
    
    // verifica que todos los campos contengan la información correspondiente
    public void validarDatosCompletos(String nombre, String password, String password2, String email, String apodo, Genero genero, Localidad localidad) throws Errores_servicio{
        if (nombre == null) {
            throw new Errores_servicio("El nombre de usuario no puede estar vacío");
        }
        validarNombreUsuario(nombre);
        if (password == null || password2 == null) {
            throw new Errores_servicio("La contraseña no puede estar vacía");
        }
        if (password.length() < 6){
            throw new Errores_servicio("La contraseña debe tener al menos 6 caracteres");
        }
        if (!password.equals(password2)){
            throw new Errores_servicio("Las contraseñas no coinciden :(");
        }
        if (apodo == null){
            throw new Errores_servicio("El apodo no puede estar vacío");
        }
        if (email == null) {
            throw new Errores_servicio("El email no puede estar vacío");
        }
        if (genero == null){
            throw new Errores_servicio("No ha completado su género");
        }
        if (localidad == null) {
            throw new Errores_servicio("No ha completado su localidad");
        }
    }
    
    // verifica que el nombre de usuario no se encuentre con otro usuario ya
    private void validarNombreUsuario (String nombre) throws Errores_servicio{
        List<Usuario> usuarios = uR.findAll();
        for (Usuario usuario : usuarios) {
            if(usuario.getNombre().equals(nombre)){
                throw new Errores_servicio("Ya se encuentra registrado un usuario con ese nombre de usuario\tPor favor, ingrese otro nombre de usuario.\nEn caso de que sea suyo dicho usuario y no recuerde la contraseña del mismo, porceda a intentar recuperarla");
            }
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    //////////////////////// Búsquedas del repositorio /////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    
    public Usuario buscarPorId(String id) throws Errores_servicio {
        Usuario u = uR.buscarPorId(id);
        return u;
    }
    
    //Método para buscar usuario por nombre
    public Usuario findByNombreUsuario(String nombre){
       Usuario usuario = uR.findByNombreUsuario(nombre);
        return usuario;
    }
    
    //Busca al usuario con el enSesion en true
    public Usuario findByEnSesion() {
        return uR.findByEnSesion();
    }
    
    public int cantidadUsuariosNombre (String nombre){
        return uR.cantidadUsuariosNombre(nombre);
    }
    
    public Usuario save (Usuario usuario) {
        return uR.save(usuario);
    }
    
    public int cantidadEnSesionTrue(){
        return uR.cantidadEnSesionTrue();
    }
    
    public List<Usuario> findSesionesIniciadas(){
        return uR.findSesionesIniciadas();
    }
}
