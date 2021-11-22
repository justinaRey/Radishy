/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.Radishy.Servicios;

import egg.Radishy.Entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author usuario
 */
@Service
public class User_servicio implements UserDetailsService{

    @Autowired
    private Usuario_servicio usuarioServicio;
    
    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Usuario usu = usuarioServicio.findByNombreUsuario(nombreUsuario);
        List<GrantedAuthority> authorities = new ArrayList();
        authorities.add(new SimpleGrantedAuthority("ROLES" + usu.getRol()));
        return new User(nombreUsuario, usu.getPassword(), authorities);
    }


//Este es el metodo que se va a llamar cuando un usuario quiera autentificarse, se quiera loguear
//Recibe el nombre de usuario, busca el usuario en el repositorio(sistema de persistencia)
//y lo transforma en un usuario de SPRING SECURITY
//    @Override
//    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
//        try {
//            Usuario usuario = usuarioServicio.findByNombreUsuario(nombre);
//            boolean enabled = true;
//            boolean accountNonExipired = true;
//            boolean credentialNonExpired = true;
//            boolean accountNonLocked = true;
//            List<GrantedAuthority> authorities = new ArrayList();
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()));
//            return new User(usuario.getNombre(), usuario.getPassword(), enabled, accountNonExipired, credentialNonExpired, accountNonLocked, authorities); // en video musetra solamente el get nombre y password, y desp los permisos)
//            
////            return new User(nombre, usuario.getPassword(), losPermisosSerian); // lo que para mí debe devolver 
//        } catch (Exception e) {
//            throw new UsernameNotFoundException("El usuario al que intenta acceder no existe");//cambié el mensaje (xq lo voy haciendo mientras lo entiendo)
//        }
//
//    }
//    
//   
}
