
package egg.Radishy.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import egg.Radishy.Servicios.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.mail.javamail.JavaMailSender;

/**
 *
 * @author ignac
 */
@Controller
@RequestMapping("/")
public class Main_controller {
    
    @Autowired // uS ---> servicio del usuario
    private MailService mS;

    @GetMapping("")
    public String index(){
        return "index";
    }

    @GetMapping("nuestro-contacto")
    public String contacte (){
        return "contacto";
    }
    
    @GetMapping("info/quienes-somos")
    public String infoNuestra(){
        return "info";
    }

    @PostMapping("enviar-mail")
    public String enviarEmail(@RequestParam String destinatario, @RequestParam String asunto, @RequestParam String contenido) {
        mS.enviarMail(destinatario, asunto, contenido);
        return "/send-email";
    }

}
