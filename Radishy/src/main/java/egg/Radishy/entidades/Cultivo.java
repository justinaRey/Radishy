
package egg.Radishy.Entidades;

import egg.Radishy.Enumeraciones.Mes;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Cultivo implements Serializable { // OBS: corregir(o borrar) constructor con params, ver tema de los tiempos y ajustar los sets de estos
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String nombre;
    
    @Enumerated(EnumType.STRING)
    private Mes inicioEpocadeSiembra;
    
    @Enumerated(EnumType.STRING)
    private Mes finEpocaSiembra;
    
    private String metodo;
    
    // comentados porque los utilizo desp con mínimo y máximo + concatenación ---> estos probablemente se borren desp
//    private Integer tiempoGerminar;
//    private Integer tiempoTransplantar;
//    private Integer tiempoCosechar;
    
    private Integer profundidadSiembraCM;
    
    private Integer tiempoGerminarMin; // en reemplazo a tiempoGerminar, se ponen dos con el tiempo min y max que puede tardar
    private Integer tiempoGerminarMax;
    private String tiempoParaGerminar; // concatenación de t.germinar min + max junto con la palabra días
    
    private Integer tiempoTransplantarMin;
    private Integer tiempoTransplantarMax;
    private String tiempoParaTransplantar; // concatenación de ambos tiempos de transplante + la palabra días
    
    private Integer tiempoCosecharMin;
    private Integer tiempoCosecharMax;
    private String tiempoParaCosechar; // concatenación de tiempo de cosecha min y max sumado a la palabra días

    private Boolean modificable;
    
    public Cultivo() {
        modificable = false;
    }

    //REVISAR EL CONSTRUCTOR CON LOS PARÁMETROS RECIBIDOS.
//    public Cultivo(String id, String nombre, Mes inicioEpocadeSiembra, Mes finEpocaSiembra, String metodo, Integer profSiembraCM, Integer tiempoGerminar, Integer tiempoTransplantar, Integer tiempoCosechar) {
//        this.id = id;
//        this.nombre = nombre;
//        this.inicioEpocadeSiembra = inicioEpocadeSiembra;
//        this.finEpocaSiembra = finEpocaSiembra;
//        this.metodo = metodo;
//        this.tiempoGerminar = tiempoGerminar;
//        this.tiempoTransplantar = tiempoTransplantar;
//        this.tiempoCosechar = tiempoCosechar;
//    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Mes getInicioEpocadeSiembra() {
        return inicioEpocadeSiembra;
    }

    public void setInicioEpocadeSiembra(Mes inicioEpocadeSiembra) {
        this.inicioEpocadeSiembra = inicioEpocadeSiembra;
    }

    public Mes getFinEpocaSiembra() {
        return finEpocaSiembra;
    }

    public void setFinEpocaSiembra(Mes finEpocaSiembra) {
        this.finEpocaSiembra = finEpocaSiembra;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }
    
//    public Integer getTiempoGerminar() {
//        return tiempoGerminar;
//    }
//
//    public void setTiempoGerminar(Integer tiempoGerminar) {
//        this.tiempoGerminar = tiempoGerminar;
//    }
//
//    public Integer getTiempoTransplantar() {
//        return tiempoTransplantar;
//    }
//
//    public void setTiempoTransplantar(Integer tiempoTransplantar) {
//        this.tiempoTransplantar = tiempoTransplantar;
//    }
//
//    public Integer getTiempoCosechar() {
//        return tiempoCosechar;
//    }
//
//    public void setTiempoCosechar(Integer tiempoCosechar) {
//        this.tiempoCosechar = tiempoCosechar;
//    }
    
    public Integer getProfundidadSiembraCM() {
        return profundidadSiembraCM;
    }

    public void setProfundidadSiembraCM(Integer profSiembraCM) {
        this.profundidadSiembraCM = profSiembraCM;
    }

    public Integer getTiempoGerminarMin() {
        if (tiempoGerminarMin != null){
            return tiempoGerminarMin;
        } else {
            return 0;
        }
    }

    public void setTiempoGerminarMin(Integer tiempoGerminar) {
        this.tiempoGerminarMin = tiempoGerminar;
    }

    public Integer getTiempoGerminarMax() {
        if (tiempoGerminarMax != null){
            return tiempoGerminarMax;
        } else {
           return 0; 
        }
    }

    public void setTiempoGerminarMax(Integer tiempoGerminar) {
        this.tiempoGerminarMax = tiempoGerminar;
    }
    
//    public void setTiempoGerminar(){
//        this.tiempoGerminar = "";
//        if (getTiempoGerminarMin != 0){
//           this.tiempoGerminar = String.ValueOff(getTiempoGerminarMin);
//        }
//        if (getTiempoGerminarMax != 0) {
//            if(getTiempoGerminarMin == 0){    
//               this.tiempoGerminar = String.ValueOff(getTiempoGerminarMax);
//            } else {
//               this.tiempoGerminar = this.tiempoGerminar.concat(" - ");
//               this.tiempoGerminar = this.tiempoGerminar.concat(getTiempoGerminarMax);
//            }
//        }
//        if (getTiempoGerminarMin == 0 && getTiempoGerminarMax == 0){
//            this.tiempoGerminar.concat(" - ");
//        } else {
//            this.tiempoGerminar.concat(" días");
//    }
    
//    public String getTiempoGerminar () {
//          return this.tiempoGerminar;
    
    public Integer getTiempoTransplantarMin() {
        if (this.tiempoTransplantarMin != null){
            return tiempoTransplantarMin;
        } else {
            return 0;
        }
    }

    public void setTiempoTransplantarMin(Integer tiempoTransplantar) {
        this.tiempoTransplantarMin = tiempoTransplantar;
    }

    public Integer getTiempoTransplantarMax() {
        if (this.tiempoTransplantarMax != null){
            return tiempoTransplantarMax;
        } else {
            return 0;
        }
    }

    public void setTiempoTransplantarMax(Integer tiempoTransplantar) {
        this.tiempoTransplantarMax = tiempoTransplantar;
    }
    
//    public void setTiempoParaTransplantar(){
//        this.tiempoParaTransplantar = "";
//        if (getTransplantarMin != 0){
//           this.tiempoParaTransplantar = String.ValueOff(getTiempoTransplantarMin);
//        }
//        if (getTiempoTransplantarMax != 0) {
//            if(getTiempoTransplantarMin == 0){    
//               this.tiempoParaTransplantar = String.ValueOff(getTiempoTransplantarMax);
//            } else {
//               this.tiempoParaTransplantar = this.tiempoParaTransplantar.concat(" - ");
//               this.tiempoParaTransplantar = this.tiempoParaTransplantar.concat(getTiempoGerminarMax);
//            }
//        }
//        if (getTiempoTransplantarMin == 0 && getTiempoTransplantarMax == 0){
//            this.tiempoParaTransplantar.concat(" - ");
//        } else {
//            this.tiempoParaTransplantar.concat(" días");
//    }
    
//    public String getTiempoParaTransplantar () {
//          return this.tiempoParaTransplantar;
    
    public Integer getTiempoCosecharMin() {
        if (this.tiempoCosecharMin != null){
            return tiempoCosecharMin;
        } else {
            return 0;
        }
    }

    public void setTiempoCosecharMin(Integer tiempoCosechar) {
        this.tiempoCosecharMin = tiempoCosechar;
    }

    public Integer getTiempoCosecharMax() {
        if (this.tiempoCosecharMax != null){
            return tiempoCosecharMax;
        } else {
            return 0;
        }        
    }

    public void setTiempoCosecharMax(Integer tiempoCosechar) {
        this.tiempoCosecharMax = tiempoCosechar;
    }
    
//    public void setTiempoCosechar(){
//        this.tiempoCosechar = "";
//        if (getTiempoCosecharMin != 0){
//           this.tiempoCosechar = String.ValueOff(getTiempoCosecharMin);
//        }
//        if (getTiempoCosecharMax != 0) {
//            if(getTiempoCosecharMin == 0){    
//               this.tiempoCosechar = String.ValueOff(getTiempoCosecharMax);
//            } else {
//               this.tiempoCosechar = this.tiempoCosechar.concat(" - ");
//               this.tiempoCosechar = this.tiempoCosechar.concat(getTiempoCosecharMax);
//            }
//        }
//        if (getTiempoCosecharMin == 0 && getTiempoCosecharMax == 0){
//            this.tiempoCosechar.concat(" - ");
//        } else {
//            this.tiempoCosechar.concat(" días");
//        }
//    }
    
//    public String getTiempoCosechar () {
//          return this.tiempoCosechar;
    //}

    public Boolean getModificable() {
        return modificable;
    }

    public void setModificable(Boolean modificable) {
        this.modificable = modificable;
    }
    
    
}
