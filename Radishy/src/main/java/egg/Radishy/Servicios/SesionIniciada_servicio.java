/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.Radishy.Servicios;

import egg.Radishy.Repositorios.SesionIniciada_repositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author usuario
 */
@Service
public class SesionIniciada_servicio {
    
    @Autowired
    private SesionIniciada_repositorio repositorio;
    
    
}
