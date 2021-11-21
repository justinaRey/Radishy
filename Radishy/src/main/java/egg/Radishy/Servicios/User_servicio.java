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
public class User_servicio implements UserDetailsService{ //comentado xq no tengo la clase security todavía, entonces daba errores :(

    @Autowired
    private Usuario_servicio usuarioServicio;

//Este es el metodo que se va a llamar cuando un usuario quiera autentificarse, se quiera loguear
//Recibe el nombre de usuario, busca el usuario en el repositorio(sistema de persistencia)
//y lo transforma en un usuario de SPRING SECURITY
    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        try {
            Usuario usuario = usuarioServicio.findByNombreUsuario(nombre);
            boolean enabled = true;
            boolean accountNonExipired = true;
            boolean credentialNonExpired = true;
            boolean accountNonLocked = true;
            List<GrantedAuthority> authorities = new ArrayList();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()));
            return new User(usuario.getNombre(), usuario.getPassword(), enabled, accountNonExipired, credentialNonExpired, accountNonLocked, authorities);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

    }
    
   
}
