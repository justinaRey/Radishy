
package egg.Radishy.entidades;

import egg.Radishy.enumeraciones.Mes;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
}
