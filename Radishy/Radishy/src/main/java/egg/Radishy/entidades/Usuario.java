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
    private String email;
    private String apellido;
    private Boolean alta;
    private Boolean enSesion;
    
    @Enumerated(EnumType.STRING)
    private Genero genero;
    
    @Enumerated(EnumType.STRING)
    private Localidad localidad;

    public Usuario() {
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public Usuario(String id, String nombre, String password, String apellido, Boolean alta, Boolean enSesion, Genero genero, Localidad localidad) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.apellido = apellido;
        this.alta = alta;
        this.enSesion = enSesion;
        this.genero = genero;
        this.localidad = localidad;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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
    
    
}
