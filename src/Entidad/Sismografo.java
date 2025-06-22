package Entidad;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Sismografo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nroSerie;
    
    private Date fechaAdquisicion;
    
    @ManyToOne
    @JoinColumn(name = "estacionSismologica_id")
    private EstacionSismologica estacion;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "Sismografo_CambioEstado",
        joinColumns = @JoinColumn(name = "Sismografo_id"),
        inverseJoinColumns = @JoinColumn(name = "CambioEstado_id")
    )
    private List<CambioEstado> cambiosEstado = new ArrayList<>();

    public Sismografo(){}
    // Constructor
    public Sismografo(
        Long nroSerie, Date fechaAdquisicion,ArrayList<CambioEstado> cambiosEstado, EstacionSismologica estacion, Estado estado) {
        this.nroSerie = nroSerie;
        this.fechaAdquisicion = fechaAdquisicion;
        this.cambiosEstado = cambiosEstado;
        this.estacion = estacion;
    }

    public Long getNroSerie() {
        return nroSerie;
    }

    public void setNroSerie(Long nroSerie) {
        this.nroSerie = nroSerie;
    }

    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public EstacionSismologica getEstacion() {
        return estacion;
    }

    public void setEstacion(EstacionSismologica estacion) {
        this.estacion = estacion;
    }

    public List<CambioEstado> getCambiosEstado() {
        return cambiosEstado;
    }

    public void setCambiosEstado(List<CambioEstado> cambiosEstado) {
        this.cambiosEstado = cambiosEstado;
    }
    

    public boolean esTuEstacion(String NombreEstacion){
        if (this.estacion.getNombre() == NombreEstacion){
            return true;
        }else{
            return false;
        }
    }
    
    public void enviarAReparar(LocalDate fechaHoraActual, Estado estadoFueraServicio, Empleado empleado, ArrayList<TipoMotivo> tipoMotivos, ArrayList<String> comentarios){
        this.buscarUltimoCambioEstado().setFechaHoraFin(fechaHoraActual);
        ArrayList<MotivoFueraServicio> Motivos = new ArrayList();
        for(int i= 0; i <tipoMotivos.size();i++){
            Motivos.add(new MotivoFueraServicio(tipoMotivos.get(i),comentarios.get(i)));
        }
        this.cambiosEstado.add(new CambioEstado(fechaHoraActual,estadoFueraServicio,empleado,Motivos));
    }
    
    public CambioEstado buscarUltimoCambioEstado(){
        for (CambioEstado cambioEstado : this.cambiosEstado) {
            if(cambioEstado.esUltimoCambioEstado()) {
                return cambioEstado;
            }
        }
        return null;
    }

}