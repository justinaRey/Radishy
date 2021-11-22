package egg.Radishy.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Fabri
 */
@Controller
@RequestMapping("/login")
public class Login_controller { 
    
    @GetMapping("")
    public String login(){
        return "loguearse";
    }
    
//    @GetMapping("/SesionIniciada")
//    public String SesionIniciada(){
//      /*  try{
//         }
//        catch(Exception e){
//        }
//*/
//        return "redirect:/usuario";
//    }
//    @GetMapping("/SesionIniciada/loginsuccessful")
//   public String loginsuccessful(){
//    return "index";
//}
   

}
