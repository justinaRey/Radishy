package egg.Radishy.Controladores;

import egg.Radishy.enumeraciones.Mes;
import egg.Radishy.servicios.Cultivo_servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cultivos")
public class Cultivo_controller {
    
    @Autowired Cultivo_servicio cS;
    
    @RequestMapping("/listarCultivos")
    public String listarCultivos(Model model){
        model.addAttribute(cS.listarCultivos());
        return "";
    }
    
    @PostMapping("/guardarCultivo")
    public String crearCultivo(@RequestParam String nombre, @RequestParam String metodo, @RequestParam String profSiembraCM, @RequestParam Integer tiempoGerminar, @RequestParam Integer tiempoTransplantar, @RequestParam Integer tiempoCosechar){ // Parámetros, agregar cuando esté el form hecho
        // Qué hacer con la epoca de siembra

        //cS.guardarCultivo();
        return "nuevoCultivo";
    }
    
}
