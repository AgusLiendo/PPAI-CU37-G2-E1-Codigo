package Entidad;

import java.time.LocalDate;


public class InfoOrdenada {
    public int nroOrden;
    public LocalDate fechaFin;
    public String nombreEstacion;
    public Sismografo sismografo;

    public InfoOrdenada() {
    }

    
    public InfoOrdenada(int nroOrden, LocalDate fechaFin, String nombreEstacion, Sismografo sismografo) {
        this.nroOrden = nroOrden;
        this.fechaFin = fechaFin;
        this.nombreEstacion = nombreEstacion;
        this.sismografo = sismografo;
    }

    public int getNroOrden() {
        return nroOrden;
    }

    public LocalDate getFechaFinalizacion() {
        return fechaFin;
    }

    public String getNombre() {
        return nombreEstacion;
    }

    public Sismografo getSismografo() {
        return sismografo;
    }
    
    
}
