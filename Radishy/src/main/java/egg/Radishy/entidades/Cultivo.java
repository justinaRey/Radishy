
package egg.Radishy.entidades;

import egg.Radishy.enumeraciones.Mes;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Cultivo implements Serializable {
    
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
    private Integer profSiembraCM;
    private Integer tiempoGerminar;
    private Integer tiempoTransplantar;
    private Integer tiempoCosechar;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeSiembra;

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

    public Integer getProfSiembraCM() {
        return profSiembraCM;
    }

    public void setProfSiembraCM(Integer profSiembraCM) {
        this.profSiembraCM = profSiembraCM;
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

    public Date getFechaDeSiembra() {
        return fechaDeSiembra;
    }

    public void setFechaDeSiembra(Date fechaDeSiembra) {
        this.fechaDeSiembra = fechaDeSiembra;
    }
    
    
}
