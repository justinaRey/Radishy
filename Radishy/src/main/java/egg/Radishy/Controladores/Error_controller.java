
package egg.Radishy.controladores;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Error_controller implements ErrorController{ // agregar una opción más que sea un html donde diga: <h1>Lo sentimos</h1><p> No es posible acceder a la ubicación que solicitó o realizar la acción que intentó</p><h5>Nuestras más sinceras disculpas</h5><h7>El equipo de Radishy</h7>
    
    @RequestMapping(value = "/error",method = {RequestMethod.GET, RequestMethod.POST})
    public String mostrarPagError(ModelMap modelo, HttpServletRequest httpServletRequest){
        String mensajeError = "";
        int codigoError = (int) httpServletRequest.getAttribute("javax.servlet.error.status_code");
        switch (codigoError){
            case 400:
                mensajeError = "El mensaje solicitado no existe.";
                //return "400.html";
                break;
            case 401:
                mensajeError = "No se encuentra autorizado."; /*No inició sesión*/
                //return "401.html";
                break;
            case 403:
                mensajeError = "No tiene permisos para acceder al recurso.";/*Relacionado con los roles*/
                //return "403.html";
                break;
            case 404:
                mensajeError = "El servidor no pudo encontrar el contenido solicitado.";
                //return "404.html";
                break;
            case 500:
                mensajeError = "El servidor no pudo realizar la petición con éxito.";
                break;
        }
        
        modelo.addAttribute("codigo", codigoError);
        modelo.addAttribute("mensaje", mensajeError);
        
        return "error";
    }
}
