/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.Radishy.Controladores;

import egg.Radishy.enumeraciones.Mes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author usuario
 */
@Controller
@RequestMapping("/cultivos")
public class Cultivo_controller {
    
    @RequestMapping("/list")
    public String listarCultivos(Model model){
        //model.addAttribute();
        return "cultivos-list";
    }
    
    @RequestMapping("/form")
    public String crearCultivos(){
        return "cultivos-form";
    }
    
    /*
    @PostMapping("/save")
    public String guardarCultivo(@RequestParam String nombre, @RequestParam Mes iniEpocaSiembra, @RequestParam Mes finEpocaSiembra, @RequestParam String metodo, @RequestParam Integer profSiembraCM, @RequestParam Integer tiempoGerminar){
        
    }*/
    
}
