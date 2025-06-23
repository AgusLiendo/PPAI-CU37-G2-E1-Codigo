package Entidad;

import jakarta.persistence.*;

@Entity
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    private String nombre;
    private String apellido;
    private int telefono;
    private String mail;
    
    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;
    
    public Empleado(){}
    
    public Empleado(String nombre, String apellido, int telefono, String mail, Rol rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.mail = mail;
        this.rol = rol;
    }

    public String conocerRol(){
        return this.rol.getNombreRol();
    }

    public boolean sosResponsableDeReparacion(){
        return this.rol.sosResponsableDeReparacion();
    }//Devolveria true si su rol es igual a Reparacion

    //Getters y Setters
    public String obtenerMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getTelefono() {
        return this.telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Rol getRol() {
        return this.rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Long getId() {
        return this.id;
    }
}
