
package egg.Radishy.Repositorios;

import egg.Radishy.Entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author usuario
 */
@Repository
public interface Usuario_repositorio extends JpaRepository<Usuario, String>{
    
    // devuelve la cantidad de usuarios con 'enSesion' true
    @Query("Select count(u) from Usuario u where u.enSesion = true")
    public int cantidadEnSesionTrue();
    
    // busca al usuario con la sesión en true
    @Query("Select u from Usuario u where u.enSesion = true")
    public Usuario findByEnSesion();
    
    // busca al usuario según su nombre de usuario
    @Query("Select u from Usuario u where u.nombre = :nombre")
    public Usuario findByNombreUsuario(@Param("nombre") String nombre);
    
    // devuelve una lista con todas las sesiones iniciadas
    @Query("Select u from Usuario u where u.enSesion = true")
    public List<Usuario> findSesionesIniciadas ();
    
    // busca al usuario según su id
    @Query("SELECT c FROM Usuario c WHERE c.id = :id")
    public Usuario buscarPorId(@Param("id") String id);
    
    // devuelve la cantidad de usuarios que contienen el mismo nombre de usuario
    @Query("Select count(u) from Usuario u where u.nombre = :nombre")
    public int cantidadUsuariosNombre(@Param("nombre") String nombre);
    
    ////////////////////////////////////////////////////////////////////////////
    
    @Query("Select u from Usuario u")
    public List<Usuario> nombresDeUsuarios();
}
