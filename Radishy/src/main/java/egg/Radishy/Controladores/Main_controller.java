
package egg.Radishy.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ignac
 */
@Controller
@RequestMapping("/")
public class Main_controller {
    @GetMapping("")
    public String index(){
        return "index";
    }

    @GetMapping("/nuestro-contacto")
    public String contacte (){
        return "redirect:contacto";
    }
    
    @GetMapping("/info")
    public String info(){
        return "info";
    }
    
    @GetMapping("/receta")
    public String receta(){
        return "receta";
    }
}
