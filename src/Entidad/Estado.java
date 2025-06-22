package Entidad;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Estado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombreEstado;
    private String ambito;
    
    public Estado(){}
    
    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
    public String getNombreEstado() {
        return nombreEstado;
    }
    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }
    public String getAmbito() {
        return ambito;
    }

    public boolean esCompletamenteRealizada(){
        return this.nombreEstado.equals("Completamente Realizada");
    }

    public boolean esAmbitoSismografo() {
        return this.ambito.equals("Sismógrafo");
    }
    
    public boolean esFueraDeServicio(){
        return this.nombreEstado.equals("Fuera de Servicio");
    }

    public boolean esAmbitoOrdenInspeccion() {
        return this.ambito.equals("OrdenInspeccion");
    }

    public boolean esCerrada() {
        return this.nombreEstado.equals("Cerrada");
    }
    
}