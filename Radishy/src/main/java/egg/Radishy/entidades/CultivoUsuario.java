package egg.Radishy.Entidades;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class CultivoUsuario implements Serializable {
    //Atributos
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @ManyToOne
    private Usuario usuario;
    
    @ManyToOne
    private Cultivo cultivo;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private Date fechaDeSembrado;
  
  //Las siguientes fechas son para agregar si se puede, q toma en cuenta los datos de germinación, transplante y cosecha y los implementa a la fecha de sembrado 
    
//    @DateTimeFormat(pattern = "YY-MM-dd")
//    private Date fechaGermiadoProm;
//    
//    @DateTimeFormat(pattern = "YY-MM-dd")
//    private Date fechaTransplanteProm;
//    
//    @DateTimeFormat(pattern = "YY-MM-dd")
//    private Date fechaCosechaAprox;
    
    // constructores:
    public CultivoUsuario(){
        
    }
    
    public CultivoUsuario (Usuario usuario, Cultivo cultivo, Date fechaDeSembrado){
        this.cultivo = cultivo;
        this.fechaDeSembrado = fechaDeSembrado;
        this.usuario = usuario;
        Calendar c = Calendar.getInstance();
        c.setTime(fechaDeSembrado);
//        int cosechaProm = (this.cultivo.getCosecharMin()+this.cultivo.getCosacharMax())/2;
//        int germinadoProm = (this.cultivo.getGerminarMax()+this.cultivo.getGerminarMin())/2;
//        int transplanteProm = (this.cultivo.getTransplantarMax()+this.cultivo.getTransplantarMin())/2;
//        this.fechaCosechaAprox = (c.getTime()); // sumarle cosecahProm
//        this.fechaGermiadoProm = (c.getTime()); // sumarle germinadoProm
//        this.fechaTransplanteProm = (c.getTime()); // sumarle transplanteProm
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cultivo getCultivo() {
        return cultivo;
    }

    public void setCultivo(Cultivo cultivo) {
        this.cultivo = cultivo;
    }

    public Date getFechaDeSembrado() {
        return fechaDeSembrado;
    }

    public void setFechaDeSembrado(Date fechaDeSembrado) {
        this.fechaDeSembrado = fechaDeSembrado;
    }

//    public Date getFechaGermiadoProm() {
//        return fechaGermiadoProm;
//    }
//
//    public void setFechaGermiadoProm(Date fechaGermiadoProm) {
//        this.fechaGermiadoProm = fechaGermiadoProm;
//    }
//
//    public Date getFechaTransplanteProm() {
//        return fechaTransplanteProm;
//    }
//
//    public void setFechaTransplanteProm(Date fechaTransplanteProm) {
//        this.fechaTransplanteProm = fechaTransplanteProm;
//    }
//
//    public Date getFechaCosechaAprox() {
//        return fechaCosechaAprox;
//    }
//
//    public void setFechaCosechaAprox(Date fechaCosechaAprox) {
//        this.fechaCosechaAprox = fechaCosechaAprox;
//    }
//    
    
}
