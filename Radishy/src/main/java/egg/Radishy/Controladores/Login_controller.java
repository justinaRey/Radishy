package egg.Radishy.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Fabri
 */
@Controller
@RequestMapping("/login")
public class Login_controller { 
    
//    @GetMapping("")
//    public String login(){
//        return "loguearse";
//    }
    
    @GetMapping("")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap modelo) {
        if (error != null) {
            modelo.put("error", "Username o Password INCORRECTOS");
        }
        if (logout != null) {
            modelo.put("logout", "Ha salido CORRECTAMENTE de la plataforma");
        }
        return "loguearse";
    }

}
