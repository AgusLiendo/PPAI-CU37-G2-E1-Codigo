package Entidad;

import jakarta.persistence.*;

@Entity
public class MotivoFueraServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String comentario;
    
    @ManyToOne
    @JoinColumn(name = "TipoMotivo_id")
    private TipoMotivo tipoMotivo;

    
    public MotivoFueraServicio(){}
    
    public MotivoFueraServicio( TipoMotivo tipoMotivo, String comentario) {
        this.tipoMotivo = tipoMotivo;
        this.comentario = comentario;
    }

    public TipoMotivo getTipoMotivo() {
        return tipoMotivo;
    }

    public void setTipoMotivo(TipoMotivo tipoMotivo) {
        this.tipoMotivo = tipoMotivo;
    }

    public static String[] getNombresMotivos(TipoMotivo[] tiposMotivo) {
        String[] nombresMotivos = new String[tiposMotivo.length];
        for (int i = 0; i < tiposMotivo.length; i++) {
            nombresMotivos[i] = tiposMotivo[i].getNombreMotivo();
        }
        return nombresMotivos;
    }

}
