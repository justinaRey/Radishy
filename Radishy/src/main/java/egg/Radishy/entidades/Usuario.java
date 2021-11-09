package egg.Radishy.entidades;

import egg.Radishy.enumeraciones.Genero;
import egg.Radishy.enumeraciones.Localidad;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String nombre;
    private String password;
    private String apodo;
//    private String email; // se lo puede agregar si se agrega el mail en el front para por ej, recuperar la contraseña
    private Boolean enSesion;
    
    @Enumerated(EnumType.STRING)
    private Genero genero;
    
    @Enumerated(EnumType.STRING)
    private Localidad localidad;

    //@ManyToOne
    //private Cultivo cultivo
    
    public Usuario() {
        enSesion = false;
    }

    public Usuario(String id, String nombre, String password, String apodo, Genero genero, Localidad localidad) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.apodo = apodo;
        this.genero = genero;
        this.localidad = localidad;
        this.enSesion = false;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }
    
    public void setEnSesion (boolean enSesion) {
        this.enSesion = enSesion;
    }
    
    public Boolean getEnSesion(){
        return enSesion;
    }
    
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
}
