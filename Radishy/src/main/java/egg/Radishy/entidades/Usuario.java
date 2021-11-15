package egg.Radishy.Entidades;

import egg.Radishy.Enumeraciones.Genero;
import egg.Radishy.Enumeraciones.Localidad;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.Inheritance;
//import javax.persistence.InheritanceType;
import org.hibernate.annotations.GenericGenerator;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED) // algo de la security
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String nombre;
    private String password;
    private String apodo;
    private Boolean alta;
    private String email;
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

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public Usuario(String id, String nombre, String password, String apodo, Boolean alta, Boolean enSesion, Genero genero, Localidad localidad) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.apodo = apodo;
        this.alta = alta;
        this.enSesion = enSesion;
        this.genero = genero;
        this.localidad = localidad;
        this.enSesion = false;
    }

    public Boolean getEnSesion() {
        return enSesion;
    }

    public void setEnSesion(Boolean enSesion) {
        this.enSesion = enSesion;
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
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
