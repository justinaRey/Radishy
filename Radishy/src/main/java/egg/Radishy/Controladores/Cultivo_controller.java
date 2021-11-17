package egg.Radishy.Controladores;

import egg.Radishy.Enumeraciones.Mes;
import egg.Radishy.Errores.Errores_servicio;
import egg.Radishy.Servicios.Cultivo_servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cultivos")
public class Cultivo_controller {
    
    @Autowired Cultivo_servicio cS;
    
    @GetMapping("/listarCultivos")
    public String listarCultivos(Model model){
        model.addAttribute("cultivos",cS.listarCultivos());
        return ""; // Nombre del html que muestra la tabla de info de los cultivos
    }
    
    @GetMapping("/agregarCultivo")
    public String agregarCultivo(){
        return "nuevoCultivo";
    }
    
    @PostMapping("/guardarCultivo")
    public String crearCultivo(@RequestParam String nombre, @RequestParam String metodo, @RequestParam Integer profSiembraCM, @RequestParam Integer tiempoGerminar, @RequestParam Integer tiempoTransplantar, @RequestParam Integer tiempoCosechar, @RequestParam Mes iniSiembra, @RequestParam Mes finSiembra) throws Errores_servicio{ // Parámetros, agregar cuando esté el form hecho
        cS.guardarCultivo(nombre, iniSiembra, finSiembra, metodo, profSiembraCM, tiempoGerminar, tiempoTransplantar, tiempoCosechar);
        return "redirect:/cultivos/listarCultivos";
    }
}
