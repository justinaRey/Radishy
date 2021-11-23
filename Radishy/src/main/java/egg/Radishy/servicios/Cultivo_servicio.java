
package egg.Radishy.Servicios;

import egg.Radishy.Errores.Errores_servicio;
import egg.Radishy.Repositorios.Cultivo_repositorio;
import egg.Radishy.Entidades.Cultivo;
import egg.Radishy.Enumeraciones.Mes;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Cultivo_servicio { // OBS: modificar el validar s/los atributos actuales, modificarCultivo() verlo bien ya q le faltan cosas y hay q adaptarlo como el modificarUsuario. Validar() adaptar según mis modificaciones
    
    @Autowired  // cR ---> repositorio del cultivo 
    private Cultivo_repositorio cR;
    
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////  Métodos para crear los cultivos //////////////////
    ////////////////////////////////////////////////////////////////////////////
    
    // MÉTODO YA MODIFICADO POR MÍ, EN EL SENTIDO DE LOS MIN Y MAX EN LOS TIEMPOS
    
    // Crea los cultivos que el usuario quiere agregar
    @Transactional
    public Cultivo guardarCultivo(String nombre, Mes iniSiembra, Mes finSiembra, String metodo, Integer profSiembra, Integer tGerminar, Integer tTransplantar, Integer tCosechar) throws Errores_servicio{
        validarCultivo(nombre, iniSiembra, finSiembra, metodo, profSiembra, tGerminar, tTransplantar, tCosechar);
        //validarDatos(nombre, iniSiembra, finSiembra, metodo, profSiembra, tGerminarMin, tGerminarMax, tTransplantarMin, tTransplantarMax, tCosecharMin, tCosecharMax); // quizá se pueda obviar 
        Cultivo cultivo = new Cultivo();
        
        cultivo.setNombre(nombre);
        cultivo.setInicioEpocadeSiembra(iniSiembra);
        cultivo.setFinEpocaSiembra(finSiembra);
        cultivo.setMetodo(metodo);
        cultivo.setProfundidadSiembraCM(profSiembra);
        cultivo.setTiempoCosechar(tCosechar);
        cultivo.setTiempoTransplantar(tTransplantar);
        cultivo.setTiempoGerminar(tGerminar);
//        cultivo.setTiempoCosecharMax(tCosecharMax);
//        cultivo.setTiempoCosecharMin(tCosecharMin);
//        cultivo.setTiempoGerminarMax(tGerminarMax);
//        cultivo.setTiempoGerminarMin(tGerminarMin);
//        cultivo.setTiempoTransplantarMax(tTransplantarMax);
//        cultivo.setTiempoTransplantarMin(tTransplantarMin);
//        cultivo.setTiempoParaCosechar();
//        cultivo.setTiempoParaTransplantar();
//        cultivo.setTiempoParaGerminar();
        return cR.save(cultivo);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    //////////////////////  Métodos para modificar los cultivos ////////////////
    ////////////////////////////////////////////////////////////////////////////
    
    
    // modificarCultivo(): recibe el id, y los atributos que quiera modificar el usuario del cultivo
//    public Cultivo modificarCultivo(String id, String nombre, Mes inSiemb, Mes finSiemb, String met, Integer tGer, Integer tTrans, Integer tCos, Integer profSiemb) throws Errores_servicio{
//        Cultivo cultivo = cR.findById(id).get();
//        if (cultivo.getModificable()){
//            if (nombre != null) {
//                cultivo.setNombre(nombre);
//            }
//            if (inSiemb != null) {
//                cultivo.setInicioEpocadeSiembra(inSiemb);
//            }
//            if (finSiemb != null) {
//                cultivo.setFinEpocaSiembra(finSiemb);
//            }
//            if (met != null) {
//                cultivo.setMetodo(met);
//            }
//            if (tGer != null) {
//                cultivo.setTiempoGerminar(tGer);
//            }
//            if (tTrans != null) {
//                cultivo.setTiempoTransplantar(tTrans);
//            }
//            if (tCos != null) {
//                cultivo.setTiempoCosechar(tCos);
//            }
//            if (profSiemb != null){
//                cultivo.setProfundidadSiembraCM(profSiemb);
//            }
//            cR.save(cultivo);
//        } else {
//            throw new Errores_servicio("No es posible modificar la información del cultivo seleccionado, ya que este ha sido cargado por defecto");
//        }
//    }
    
    // guarda los datos ingresados para modificar los cultivos ---> ver el tema de que si no ingresó un min o max lo compare con el preexistente
//    public Cultivo modificarCultivo (String id, String nombre, Mes inSiemb, Mes finSiemb, String met, Integer tGerMin, Integer tGerMax, Integer tTransMin, Integer tTransMax, Integer tCosMin, Integer tCosMax, Integer profSiemb) throws Errores_servicio{
//        Optional<Cultivo> rta = cR.findById(id);
//        if (rta.isPresent()){
//            Cultivo cult = rta.get();
//            if (cult.getModificable()){
//                if (nombre != null){
//                    cult.setNombre(nombre);
//                }
//                if (inSiemb != null){
//                    cult.setInicioEpocadeSiembra(inSiemb);
//                }
//                if (finSiemb != null){
//                    cult.setFinEpocaSiembra(finSiemb);
//                }
//                if (met != null){
//                    cult.setMetodo(met);
//                }
//                if (tGerMax != null && tGerMin != null){
//                    if (tGerMax < tGerMin){
////                        cult.setTiempoGerminarMax(tGerMin);
////                        cult.setTiempoGerminarMin(tGerMax);
//                    } else {
////                        cult.setTiempoGerminarMax(tGerMax);
////                        cult.setTiempoGerminarMin(tGerMin);
//                    }
//                }
//                if (tGerMax != null && tGerMin == null){
//                    if (cult.getTiempoGerminarMin() < tGerMax){
//                        cult.setTiempoGerminarMax(tGerMax);
//                    } else {
////                        cult.setTiempoGerminarMax(cult.getTiempoGerminarMin());
////                        cult.setTiempoGerminarMin(tGerMax);
//                    }
//                }
//                if (tGerMin != null && tGerMax == null){
//                     if (cult.getTiempoGerminarMax() > tGerMin){
//                        cult.setTiempoGerminarMin(tGerMin);
//                    } else {
//                        cult.setTiempoGerminarMin(cult.getTiempoGerminarMax());
//                        cult.setTiempoGerminarMax(tGerMin);
//                    }
//                }
//                if (tGerMax != null && tGerMin == null){
//                     if (cult.getTiempoGerminarMin() < tGerMax){
//                        cult.setTiempoGerminarMax(tGerMax);
//                    } else {
//                        cult.setTiempoGerminarMax(cult.getTiempoCosecharMin());
//                        cult.setTiempoGerminarMin(tGerMin);
//                    }
//                }
//                if (tTransMax != null && tTransMin != null){
//                    if (tTransMax < tTransMin){
//                        cult.setTiempoTransplantarMax(tTransMin);
//                        cult.setTiempoTransplantarMin(tTransMax);
//                    } else {
//                        cult.setTiempoTransplantarMax(tTransMax);
//                        cult.setTiempoTransplantarMin(tTransMin);
//                    }
//                }
//                if (tTransMax != null && tTransMin == null){
//                    if (cult.getTiempoTransplantarMin() < tTransMax){
//                        cult.setTiempoTransplantarMax(tTransMax);
//                    } else { 
//                        cult.setTiempoTransplantarMax(cult.getTiempoTransplantarMin());
//                        cult.setTiempoTransplantarMin(tTransMax);
//                    }
//                }
//                if (tTransMin != null && tTransMax == null){
//                    if(cult.getTiempoTransplantarMax() > tTransMin){
//                        cult.setTiempoTransplantarMin(tTransMin);
//                    } else {
//                        cult.setTiempoTransplantarMin(cult.getTiempoTransplantarMax());
//                        cult.setTiempoTransplantarMax(tTransMin);
//                    }
//                }
//                if (tCosMax != null && tCosMin != null){
//                    if (tCosMax < tCosMin){
//                        cult.setTiempoCosecharMax(tCosMin);
//                        cult.setTiempoCosecharMin(tCosMax);
//                    } else {
//                        cult.setTiempoCosecharMax(tCosMax);
//                        cult.setTiempoCosecharMin(tCosMin);
//                    }
//                }
//                if (tCosMax != null && tCosMin == null){
//                    if (cult.getTiempoCosecharMin() < tTransMax){
//                        cult.setTiempoCosecharMax(tCosMax);
//                    } else { 
//                        cult.setTiempoCosecharMax(cult.getTiempoCosecharMin());
//                        cult.setTiempoCosecharMin(tCosMax);
//                    } 
//                }
//                if (tCosMin != null && tCosMax == null){
//                    if(cult.getTiempoCosecharMax() > tCosMin){
//                        cult.setTiempoCosecharMin(tCosMin);
//                    } else {
//                        cult.setTiempoCosecharMin(cult.getTiempoCosecharMax());
//                        cult.setTiempoCosecharMax(tCosMin);
//                    }
//                }
//                if (profSiemb != null) {
//                    cult.setProfundidadSiembraCM(profSiemb);
//                }
//                return cR.save(cult);
//            } else {
//                throw new Errores_servicio("Usted no tiene acceso al cultivo seleccionado para modificarlo");
//            }
//        } else {
//            throw new Errores_servicio("El cultivo ingresado no se encuentra cargado en nuestra base de datos, pruebe con otro");
//        }
//    }
    
    ////////////////////////////////////////////////////////////////////////////
    ///////////////////////  Métodos para eliminar los cultivos ////////////////
    ////////////////////////////////////////////////////////////////////////////
    
    // bajarCultivo(): elimina al cultivo de la base de datos del cual recibe el id
    public void bajarCultivo(String id){
        Cultivo cultivo = cR.findById(id).get();
        cR.delete(cultivo);
    }
    
    // mi método para eliminarlo
//    public void DarBajaCultivo(String id) throws Errores_servicio{
//        Optional<Cultivo> rta = cR.findById(id);
//        if (rta.isPresent()){
//            Cultivo cult = rta.get();
//            if (cult.getModificable()){
//                cR.delete(cult);
//            } else {
//                throw new Errores_servicio("No tiene el acceso requerido para eliminar dicho cultivo");
//            }
//        } else {
//            throw new Errores_servicio("No se ha encontrado al cultivo que quiere eliminar");
//        }
//    }
    ////////////////////////////////////////////////////////////////////////////
    ///////////////////////////  validaciones de los datos /////////////////////
    ////////////////////////////////////////////////////////////////////////////
    
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
    
    // validación de que todos los datos sean completado con el doble comando de los min y max en los tiempos
    public void validarDatos (String nombre, Mes iniSiembra, Mes finSiembra, String metodo, Integer profSiembra, Integer tGerminarMin, Integer tGerminarMax, Integer tTransplantarMin, Integer tTransplantarMax, Integer tCosecharMin, Integer tCosecharMax) throws Errores_servicio{
        if (nombre == null) {
            throw new Errores_servicio("El nombre del cultivo que quiere agregar no puede estar vacío");
        }
        if (iniSiembra == null || finSiembra == null) {
            throw new Errores_servicio("Debe completar todos los campos acerca de la fecha de sembrado");
        }
        if (metodo == null){
            throw new Errores_servicio("No ha completado todos los datos requeridos!\nLe falta completar el método al menos");
        }
        if (profSiembra == null){
            throw new Errores_servicio("Le falta completar la profundidad a la que se debe sembrar el cultivo");
        }
        if (tGerminarMin == null || tGerminarMax == null){
            throw new Errores_servicio("No ha completado todos los campos referidos al tiempo de germinado del cultivo");
        }
        if (tTransplantarMax == null || tTransplantarMin == null) {
            throw new Errores_servicio("No ha completado todos los campos referidos al tiempo de transplante del cultivos");
        }
        if ( tCosecharMax == null || tCosecharMin == null){
            throw new Errores_servicio("No ha completado todos los campos referidos al tiempo de cosecha del cultivo");
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    ///////////////////////////  Búsquedas del repositorio /////////////////////
    ////////////////////////////////////////////////////////////////////////////
    
    public List<Cultivo> listarCultivos(){
        return cR.findAll();
    }
    
    public Cultivo findByIdGet(String id) {
        return cR.findById(id).get();
    }
    
    public Optional<Cultivo> findById(String id){
        return cR.findById(id);
    }
}
