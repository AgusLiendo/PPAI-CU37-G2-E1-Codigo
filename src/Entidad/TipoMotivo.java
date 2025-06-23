package Entidad;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TipoMotivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombreTipoMotivo;
    
    public TipoMotivo(){}
    
    
    public String getNombreMotivo() {
        return nombreTipoMotivo;
    }
    
}

