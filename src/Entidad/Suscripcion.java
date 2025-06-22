package Entidad;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Suscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Date fechaHoraInicioSubscripcion;
    private Date fechaHoraFinSubscripcion;
    
     public Suscripcion(){}

    public Suscripcion(Date fechaHoraInicioSubscripcion, Date fechaHoraFinSubscripcion) {
        this.fechaHoraInicioSubscripcion = fechaHoraInicioSubscripcion;
        this.fechaHoraFinSubscripcion = fechaHoraFinSubscripcion;
    }

    public boolean vigente() {
        Date ahora = new Date();
        return ahora.after(fechaHoraInicioSubscripcion) && ahora.before(fechaHoraFinSubscripcion); //verifica que la fecha sea anterior a la fehcaHorafin
    }          //Verifica que ya haya emepezado, para evitar problemas con fechas futuras

    public Date getFechaHoraFinSubscripcion() {
        return fechaHoraFinSubscripcion;
    }

    public void setFechaHoraFinSubscripcion(Date fechaHoraFinSubscripcion) {
        this.fechaHoraFinSubscripcion = fechaHoraFinSubscripcion;
    }

    public Date getFechaHoraInicioSubscripcion() {
        return fechaHoraInicioSubscripcion;
    }

    public void setFechaHoraInicioSubscripcion(Date fechaHoraInicioSubscripcion) {
        this.fechaHoraInicioSubscripcion = fechaHoraInicioSubscripcion;
    }
}
