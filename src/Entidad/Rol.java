package Entidad;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
            
    private String nombre;
    private String descripcion;
    
    public Rol() {}
    
    public Rol(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public boolean sosResponsableDeReparacion(){
        if (this.nombre.equals("ResponsableDeReparacion")){
            return true;
        }else{
            return false;
        }
    }
    
    public String getNombreRol() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
   
}
