/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.Radishy.Servicios;

import egg.Radishy.Repositorios.SesionIniciada_repositorio;
import egg.Radishy.Repositorios.Usuario_repositorio;
import egg.Radishy.entidades.Cultivo;
import egg.Radishy.entidades.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author usuario
 */
@Service
public class SesionIniciada_servicio {
    
    @Autowired
    private SesionIniciada_repositorio repositorioSesIn;
    @Autowired
    private Usuario_repositorio usuarioRepositorio;
    
    // agregarCultivo(): agrega un cultivo a los que ya posee el usuario
    public void agregarMiCultivo (String idUsuario, String idCultivo) {
        
        Usuario usuario = usuarioRepositorio.findById(idUsuario).get();
        
        
    }
    
    public List<Cultivo> misCultivos () {
        return null;
    }
}
