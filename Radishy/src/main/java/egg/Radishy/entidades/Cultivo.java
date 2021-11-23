
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
    
    private Integer tiempoGerminar;
    private Integer tiempoTransplantar;
    private Integer tiempoCosechar;
    
    private Integer profundidadSiembraCM;

    private Boolean modificable; // en true es xq permite que el usuario haga modificaciones
    
    public Cultivo() {
        modificable = true;
    }
    
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
    
    public Integer getTiempoGerminar() {
        return tiempoGerminar;
    }

    public void setTiempoGerminar(Integer tiempoGerminar) {
        this.tiempoGerminar = tiempoGerminar;
    }

    public Integer getTiempoTransplantar() {
        return tiempoTransplantar;
    }

    public void setTiempoTransplantar(Integer tiempoTransplantar) {
        this.tiempoTransplantar = tiempoTransplantar;
    }

    public Integer getTiempoCosechar() {
        return tiempoCosechar;
    }

    public void setTiempoCosechar(Integer tiempoCosechar) {
        this.tiempoCosechar = tiempoCosechar;
    }
    
    public Integer getProfundidadSiembraCM() {
        return profundidadSiembraCM;
    }

    public void setProfundidadSiembraCM(Integer profSiembraCM) {
        this.profundidadSiembraCM = profSiembraCM;
    }

//    public Boolean getModificable() {
//        return modificable;
//    }
//
//    public void setModificable(Boolean modificable) {
//        this.modificable = modificable;
//    }
//    
    
}
