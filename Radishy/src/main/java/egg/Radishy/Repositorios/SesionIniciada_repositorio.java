/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.Radishy.Repositorios;

import egg.Radishy.Entidades.SesionIniciada;
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
public interface SesionIniciada_repositorio extends JpaRepository<SesionIniciada, String>{
        
    @Query("select s from SesionIniciada s where s.usuario = :usuario")
    public SesionIniciada findByUsuario(@Param("usuario") Usuario usuario);  // este ya estaba de antes 
    
    @Query("select s from SesionIniciada s where s.usuario.enSesion = true")
    public List<SesionIniciada> cultivosUsuario();
    
    // no se si se hace así    
//    @Query("Select c.nombre, c.metodo, c.profuncidadSiembraCm, c.riego, s.fechaDeSembrado, s.fechaGerminadoProm, s.fechaTransplanteProm, s.fechaCosechaProm from Cultivo c, SesionIniciada_ s where s.usuario.enSesion = true")
//    public List<SesionIniciada> cultivosUsuarioVista();
    
    // como creo que se hace
    @Query("select c.nombre from Cultivo c, SesionIniciada s where s.id = :id")
    public String nombreCultivo(@Param("id") String id); 
    
    @Query("select c.metodo from Cultivo c, SesionIniciada s where s.id = :id")
    public String metodoCultivo (@Param("id") String id);
    
    @Query("select c.profundidadSiembraCM from Cultivo c, SesionIniciada s where s.id = :id")
    public String profundidadSiembra (@Param("id") String id);
    
//    @Query("select c.riego from Cultivo c, SesionIniciada s where s.id = :id")
//    public String riegoCultivo (@Param("id") String id);
    
    @Query("select s.fechaDeSembrado from SesionIniciada s where s.id = :id")
    public Date fechaSembradoCultivo (@Param("id") String id);
    
//    @Query("Select s.fechaGerminadoProm from SesionIniciada_ s where s.id = :id")
//    public Date fechaGerminadoCultivo (@Param("id") String id);
//    
//    @Query("Select s.fechaTransplanteProm from SesionIniciada_ s where s.id = :id")
//    public Date fechaTransplanteCultivo (@Param("id") String id);
//    
//    @Query("Select s.fechaCosechaProm from SesionIniciada_ s where s.id = :id")
//    public Date fechaCosechaCultivo (@Param("id") String id);
}
