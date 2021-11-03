
package egg.Radishy.Repositorios;

import egg.Radishy.entidades.Usuario;
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
    
    @Query("SELECT c FROM Usuario c WHERE c.id = :id")
    public Usuario buscarPorId(@Param("id") String id);
    
    @Query("Select count(u) from Usuario u where u.enSesion = true")
    public int cantidadEnSesionTrue();
    
    @Query("Select u from Usuario u where u.enSesion = true")
    public Usuario findByEnSesion();
    
    @Query("Select u from Usuario u where u.nombre = :nombre")
    public Usuario findByNombreUsuario(@Param("nombre") String nombre);
    
}
