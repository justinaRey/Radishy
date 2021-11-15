
package egg.Radishy.Servicios;

import egg.Radishy.Errores.Errores_servicio;
import egg.Radishy.Repositorios.Cultivo_repositorio;
import egg.Radishy.Entidades.Cultivo;
import egg.Radishy.Enumeraciones.Mes;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class Cultivo_servicio {
    
    @Autowired Cultivo_repositorio cR;
    
    public Cultivo guardarCultivo(String nombre, Mes iniSiembra, Mes finSiembra, String metodo, Integer profSiembra, Integer tGerminar, Integer tTransplantar, Integer tCosechar) throws Errores_servicio{
        validarCultivo(nombre, iniSiembra, finSiembra, metodo, profSiembra, tGerminar, tTransplantar, tCosechar);
        Cultivo cultivo = new Cultivo();
        cultivo.setNombre(nombre);
        cultivo.setInicioEpocadeSiembra(iniSiembra);
        cultivo.setFinEpocaSiembra(finSiembra);
        cultivo.setMetodo(metodo);
        cultivo.setProfundidadSiembraCM(profSiembra);
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
    
    public void validarCultivo(String nombre, Mes iniSiembra, Mes finSiembra, String metodo, Integer profSiembra, Integer tGerminar, Integer tTransplantar, Integer tCosechar) throws Errores_servicio{
        try {
            List<Cultivo> lista = listarCultivos();
            for (Cultivo cultivo : lista) {
                if(nombre == cultivo.getNombre()){
                    throw new Errores_servicio("El nombre ingresado ya está en la lista de cultivos.");
                }
            }
            if (nombre == null) {
                throw new Errores_servicio("El nombre no fue ingresado.");
            }
            if (iniSiembra == null) {
                throw new Errores_servicio("El inicio de la siembra no fue ingresado.");
            }
            if (finSiembra == null) {
                throw new Errores_servicio("El final de la siembra no fue ingresado.");
            }
            for (Mes mes : Mes.values()) {
                if(iniSiembra != mes || finSiembra != mes){
                    throw new Errores_servicio("El mes ingresado no es correcto.");
                }
            }
            if (metodo == null) {
                throw new Errores_servicio("El método no fue ingresado.");
            }
            if (profSiembra == null) {
                throw new Errores_servicio("La profundidad de siembra no fue ingresada");
            }
            if (tGerminar == null) {
                throw new Errores_servicio("El tiempo de germinar no fue ingresado.");
            }
            if (tTransplantar == null) {
                throw new Errores_servicio("El tiempo de transplantar no fue ingresado.");
            }
            if (tCosechar == null) {
                throw new Errores_servicio("El tiempo de cosechar no fue ingresado.");
            }
        } catch (Errores_servicio e) {
            System.err.println(e.getMessage());
        }
    }
    
    public List<Cultivo> listarCultivos(){
        return cR.findAll();
    }
}
