/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.Radishy.Repositorios;

import egg.Radishy.entidades.SesionIniciada;
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

    @Query("Select count(u) from Usuario u where u.enSesion = true")
    public int cantidadEnSesionTrue();
    
    @Query("Select u from Usuario u where u.enSesion = true")
    public Usuario findByEnSesion();
    
    @Query("Select u from Usuario u where u.nombre = :nombre")
    public Usuario findByNombreUsuario(@Param("nombre") String nombre);
}
