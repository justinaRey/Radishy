
package egg.Radishy.Servicios;

import egg.Radishy.Errores.Errores_servicio;
import egg.Radishy.Repositorios.Cultivo_repositorio;
import egg.Radishy.Entidades.Cultivo;
import egg.Radishy.Enumeraciones.Mes;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Cultivo_servicio { // OBS: modificar el validar s/los atributos actuales, modificarCultivo() verlo bien ya q le faltan cosas y hay q adaptarlo como el modificarUsuario. Validar() adaptar según mis modificaciones
    
    @Autowired  // cR ---> repositorio del cultivo 
    Cultivo_repositorio cR;
    
    public Cultivo guardarCultivo(String nombre, Mes iniSiembra, Mes finSiembra, String metodo, Integer profSiembra, Integer tGerminarMin, Integer tGerminarMax, Integer tTransplantarMin, Integer tTransplantarMax, Integer tCosecharMin, Integer tCosecharMax) throws Errores_servicio{
        //validarCultivo(nombre, iniSiembra, finSiembra, metodo, profSiembra, tGerminar, tTransplantar, tCosechar);
        
        Cultivo cultivo = new Cultivo();
        
        cultivo.setNombre(nombre);
        cultivo.setInicioEpocadeSiembra(iniSiembra);
        cultivo.setFinEpocaSiembra(finSiembra);
        cultivo.setMetodo(metodo);
        cultivo.setProfundidadSiembraCM(profSiembra);
        cultivo.setTiempoCosecharMax(tCosecharMax);
        cultivo.setTiempoCosecharMin(tCosecharMin);
        cultivo.setTiempoGerminarMax(tGerminarMax);
        cultivo.setTiempoGerminarMin(tGerminarMin);
        cultivo.setTiempoTransplantarMax(tTransplantarMax);
        cultivo.setTiempoTransplantarMin(tTransplantarMin);
        //cultivo.setTiempoParaCosechar;
        //cultivo.setTiempoParaTransplantar;
        //cultivo.setTiempoParaGerminar;
        return cR.save(cultivo);
    }
    /*
    // modificarCultivo(): recibe el id, y los atributos que quiera modificar el usuario del cultivo
    public Cultivo modificarCultivo(String id, String nombre, Mes inSiemb, Mes finSiemb, String met, Integer tGer, Integer tTrans, Integer tCos, Integer profSiemb) throws Errores_servicio{
        Cultivo cultivo = cR.findById(id).get();
        if (cultivo.getModificable()){
            if (nombre != null) {
                cultivo.setNombre(nombre);
            }
            if (inSiemb != null) {
                cultivo.setInicioEpocadeSiembra(inSiemb);
            }
            if (finSiemb != null) {
                cultivo.setFinEpocaSiembra(finSiemb);
            }
            if (met != null) {
                cultivo.setMetodo(met);
            }
            if (tGer != null) {
                cultivo.setTiempoGerminar(tGer);
            }
            if (tTrans != null) {
                cultivo.setTiempoTransplantar(tTrans);
            }
            if (tCos != null) {
                cultivo.setTiempoCosechar(tCos);
            }
            if (profSiemb != null){
                cultivo.setProfundidadSiembraCM(profSiemb);
            }
            cR.save(cultivo);
        } else {
            throw new Errores_servicio("No es posible modificar la información del cultivo seleccionado, ya que este ha sido cargado por defecto");
        }
    }
    
    // bajarCultivo(): elimina al cultivo de la base de datos del cual recibe el id
    public void bajarCultivo(String id){
        Cultivo cultivo = cR.findById(id).get();
        cR.delete(cultivo);
    }*/
    
    public void validarCultivo(String nombre, Mes iniSiembra, Mes finSiembra, String metodo, Integer profSiembra, Integer tGerminar, Integer tTransplantar, Integer tCosechar) throws Errores_servicio{ //revisar x mis modificaciones
        try {
            List<Cultivo> lista = listarCultivos();
            for (Cultivo cultivo : lista) {
                if(nombre.equals(cultivo.getNombre())){
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
