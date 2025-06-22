package Entidad;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
//definir clase atributo y metodos
//dar cierre orden inspeccion
@Entity
public class OrdenInspeccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "Estacion_id")
    private EstacionSismologica estacionSismologica;
    
    @ManyToOne
    @JoinColumn(name = "Empleado_id")
    private Empleado empleado;
    
    @ManyToOne
    @JoinColumn(name = "Estado_id")
    private Estado estado;
    
    
    private LocalDate fechaHoraCierre;
    private LocalDate fechaFinalizacion;
    private LocalDate fechaHoraInicio;
    private int nroOrden;
    private String observacionCierre;
    
    public OrdenInspeccion(){}

    public OrdenInspeccion(LocalDate fechaHoraFinalizacion, LocalDate fechaHoraInicio, int nroOrden, String observacionCierre){
        this.fechaFinalizacion = null;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraCierre = null;
        this.nroOrden = nroOrden;
        this.observacionCierre = observacionCierre;
    }
    
    public LocalDate getfechaFinalizacion(){
        return this.fechaFinalizacion;
    }

    public int getNumeroOrden(){
        return this.nroOrden;
    }
    
    public void setEstado(Estado Estado){
        this.estado = Estado;
    }
 
   public void setfechaHoraCierre(LocalDate fecha){
        this.fechaFinalizacion = fecha;
   }

   public boolean esDeEmpleado(Long id){
       return this.empleado.getId().equals(id);
   }


   public ArrayList esCompletamenteRealizada(){
       ArrayList<String> respuesta = new ArrayList<>();
        if(this.estado.esCompletamenteRealizada()){
            int NumeroOrden = this.getNumeroOrden();
            LocalDate fechaFin = this.getfechaFinalizacion();
            String nombreEstacion = this.estacionSismologica.getNombre(); 
            respuesta.add(String.valueOf(NumeroOrden));
            respuesta.add(String.valueOf(fechaFin));
            respuesta.add(String.valueOf(nombreEstacion));
 
            return respuesta;
        }else{
            return respuesta;
        }
   }

 

   public void cerrar(Estado estadoCerrado, LocalDate fechaFinalizacion){
    this.setfechaHoraCierre(fechaFinalizacion);
    this.setEstado(estadoCerrado);
  }
 

  
}




