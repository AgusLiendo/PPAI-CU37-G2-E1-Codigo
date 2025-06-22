package Entidad;

import jakarta.persistence.*;

@Entity
public class EstacionSismologica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long codigoEstacion;
    private String documentoCertificacionADQ;
    private String fechaSolicitudCertificacion;
    private String latitud;
    private String longitud;
    private String nombre;
    private String nroCertificacionAdquisicion;
    
    // Constructor
    
    public EstacionSismologica(){}

    public EstacionSismologica(Long id, Long codigoEstacion, String documentoCertificacionADQ, String fechaSolicitudCertificacion, String latitud, String longitud, String nombre, String nroCertificacionAdquisicion) {
        this.id = id;
        this.codigoEstacion = codigoEstacion;
        this.documentoCertificacionADQ = documentoCertificacionADQ;
        this.fechaSolicitudCertificacion = fechaSolicitudCertificacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.nroCertificacionAdquisicion = nroCertificacionAdquisicion;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodigoEstacion() {
        return codigoEstacion;
    }

    public void setCodigoEstacion(Long codigoEstacion) {
        this.codigoEstacion = codigoEstacion;
    }

    public String getDocumentoCertificacionADQ() {
        return documentoCertificacionADQ;
    }

    public void setDocumentoCertificacionADQ(String documentoCertificacionADQ) {
        this.documentoCertificacionADQ = documentoCertificacionADQ;
    }

    public String getFechaSolicitudCertificacion() {
        return fechaSolicitudCertificacion;
    }

    public void setFechaSolicitudCertificacion(String fechaSolicitudCertificacion) {
        this.fechaSolicitudCertificacion = fechaSolicitudCertificacion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNroCertificacionAdquisicion() {
        return nroCertificacionAdquisicion;
    }

    public void setNroCertificacionAdquisicion(String nroCertificacionAdquisicion) {
        this.nroCertificacionAdquisicion = nroCertificacionAdquisicion;
    }
    
    
}
