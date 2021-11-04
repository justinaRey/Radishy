/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.Radishy.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author usuario
 */
@Controller
@RequestMapping("")
public class SesionIniciada_controller {
  
    @GetMapping("")
    public String login(){
        return "usuario.hmtl";
    }
    
    @GetMapping("/agregar-cultivo-usuario")
    public String agregarCultivo(){
        return "aggCultivo.html";
    }
}
