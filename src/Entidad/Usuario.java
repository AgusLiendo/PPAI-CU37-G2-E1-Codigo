package Entidad;

import jakarta.persistence.*;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombreUsuario;
    private String contrasenia;
    private Perfil perfil;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Suscripcion_id") 
    private Suscripcion suscripcion;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Empleado_id") 
    private Empleado empleado;
    
    public Usuario(){}

    public Usuario(String nombreUsuario, String contrasenia, Perfil perfil, Suscripcion suscripcion, Empleado empleado) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.perfil = perfil;
        this.suscripcion = suscripcion;
        this.empleado = empleado;
    }

    public boolean login(String nombreUsuario, String contraseña) {
        return this.nombreUsuario.equals(nombreUsuario) && this.contrasenia.equals(contraseña);
    }

    //public void login(String nombreUsuario, String contraseña){
    //
    //}

    //Getters y Setters
    public String getNombreUsuario() {
        return this.nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return this.contrasenia;
    }

    public void setContraseña(String contraseña) {
        this.contrasenia = contraseña;
    }

    public Perfil getPermiso() {
        return this.perfil;
    }

    public void setPermiso(Perfil permiso) {
        this.perfil = permiso;
    }

    public Suscripcion getSuscripcion() {
        return this.suscripcion;
    }

    public void setSuscripcion(Suscripcion suscripcion) {
        this.suscripcion = suscripcion;
    }

    public Empleado getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
