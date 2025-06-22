package Entidad;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Sesion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDate fechaHoraInicio;
    private LocalDate fechaHoraFin;
    
    @ManyToOne
    @JoinColumn(name = "Usuario_id")
    private Usuario usuario;

    public Sesion() {
    }

    public Sesion(LocalDate fechaHoraInicio, Usuario usuario) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.usuario = usuario;
    }

    //implementación metodo empleado logueado
    public Empleado buscarEmpleadoLogueado(){
        return this.usuario.getEmpleado();
    }

    //métodos de seteo
    public LocalDate getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDate fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDate getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDate fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
