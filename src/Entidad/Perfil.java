package Entidad;

import jakarta.persistence.*;
import java.util.ArrayList;

@Entity
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String descripcion;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "perfil_permiso",
        joinColumns = @JoinColumn(name = "perfil_id"),
        inverseJoinColumns = @JoinColumn(name = "permiso_id")
    )
    private ArrayList<Permiso> permisos;
    
    public Perfil(){}
    
    public Perfil(String nombre, String descripcion, ArrayList<Permiso> permiso) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.permisos = permiso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<Permiso> getPermiso() {
        return permisos;
    }

    public void addPermiso(Permiso permiso) {
        this.permisos.add(permiso);
    }
}


