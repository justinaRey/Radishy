/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.Radishy.Servicios;

import egg.Radishy.Repositorios.Cultivo_repositorio;
import egg.Radishy.Repositorios.Usuario_repositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author usuario
 */
@Service
public class Usuario_servicio {
    @Autowired
    private Usuario_repositorio repositorio;
    
    @Autowired
    private Cultivo_repositorio cultivoRepositorio;
    
    public void agregarMiCultivo (String idUsuario, String idCultivo) {
        
    }
            
}
