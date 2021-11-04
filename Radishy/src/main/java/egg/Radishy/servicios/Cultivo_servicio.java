
package egg.Radishy.servicios;

import egg.Radishy.Repositorios.Cultivo_repositorio;
import egg.Radishy.entidades.Cultivo;
import egg.Radishy.enumeraciones.Mes;
import egg.Radishy.errores.ErrorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class Cultivo_servicio {
    
    @Autowired Cultivo_repositorio cR;
    
    public Cultivo guardarCultivo(String nombre, Mes iniSiembra, Mes finSiembra, String metodo, Integer profSiembra, Integer tGerminar, Integer tTransplantar, Integer tCosechar) throws ErrorServicio{
        validarCultivo(nombre, iniSiembra, finSiembra, metodo, profSiembra, tGerminar, tTransplantar, tCosechar);
        Cultivo cultivo = new Cultivo();
        cultivo.setNombre(nombre);
        cultivo.setInicioEpocadeSiembra(iniSiembra);
        cultivo.setFinEpocaSiembra(finSiembra);
        cultivo.setMetodo(metodo);
        cultivo.setProfSiembraCM(profSiembra);
        cultivo.setTiempoGerminar(tGerminar);
        cultivo.setTiempoCosechar(tCosechar);
        cultivo.setTiempoTransplantar(tTransplantar);
        return cR.save(cultivo);
    }
    /*
    public Cultivo modificarCultivo(){
        
    }
    
    public Cultivo bajarCultivo(){
        
    }*/
    
    public void validarCultivo(String nombre, Mes iniSiembra, Mes finSiembra, String metodo, Integer profSiembra, Integer tGerminar, Integer tTransplantar, Integer tCosechar) throws ErrorServicio{
        try {
            List<Cultivo> lista = listarCultivos();
            for (Cultivo cultivo : lista) {
                if(nombre == cultivo.getNombre()){
                    throw new ErrorServicio("El nombre ingresado no es válido.");
                }
            }
            if (nombre == null) {
                throw new ErrorServicio("El nombre ingresado no es válido.");
            }
            if (iniSiembra == null) {
                throw new ErrorServicio("La contraseña ingresada no es válida.");
            }
            if (finSiembra == null) {
                throw new ErrorServicio("El apodo ingresado no es válido.");
            }
            if (metodo == null) {
                throw new ErrorServicio("El género ingresado no es válido.");
            }
            if (profSiembra == null) {
                throw new ErrorServicio("La localidad ingresada no es válida.");
            }
            if (tGerminar == null) {
                throw new ErrorServicio("La localidad ingresada no es válida.");
            }
            if (tTransplantar == null) {
                throw new ErrorServicio("La localidad ingresada no es válida.");
            }
            if (tCosechar == null) {
                throw new ErrorServicio("La localidad ingresada no es válida.");
            }
        } catch (ErrorServicio e) {
            System.err.println(e.getMessage());
        }
    }
    
    public List<Cultivo> listarCultivos(){
        return cR.findAll();
    }
}
