/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.Radishy.Repositorios;

import egg.Radishy.Entidades.CultivoDeUsuario;
import egg.Radishy.Entidades.Usuario;
import java.util.Date;
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
public interface CultivoDeUsuario_repositorio extends JpaRepository<CultivoDeUsuario, String>{ //MODIFIQUÉ AHORA
    
    // según el usuario ingresado busca todos sus 'CultivoDeUsuario'
    @Query("select cu from CultivoDeUsuario cu where cu.usuario = :usuario")
    public List<CultivoDeUsuario> findCultivosDelUsuarioByUsuario(@Param("usuario") Usuario usuario);  
    
    // según 'enSesión' del usuario busca los 'CultivoDeUsuario' que correspondan al usuario en sesión iniciada
    @Query("Select cu from CultivoDeUsuario cu where cu.usuario.enSesion = true")
    public List<CultivoDeUsuario> findCultivosDelUsuario();
    
}
