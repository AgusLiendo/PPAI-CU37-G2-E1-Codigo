package DAOs;

import Entidad.*;
import Interfaces.frontend.tablaOrdenesInspeccion;
import Interfaces.frontend.CerrarOrdenABM;
import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class GestorOrdenInspeccion {

    private Sesion sesionActual;
    private Empleado empleadoLogueado;
    private ArrayList<Empleado> empleados;
    private ArrayList<OrdenInspeccion> ordenDeInspeccion;
    private ArrayList<Integer> nroOrden;
    private ArrayList<LocalDate> fechaFin;
    private ArrayList<String> nombreEstacion;
    private ArrayList<Sismografo> sismografos;
    private ArrayList<Estado> estados;
    private ArrayList<Sismografo> sismografoDeLaEstacion;
    private ArrayList<String> motivosFueraServicio;
    private ArrayList<TipoMotivo> seleccionMotivos;
    private ArrayList<String> comentarios;
    
    private ArrayList<InfoOrdenada> infoOrdenada;
    
    private ArrayList<TipoMotivo> tipoMotivo;
    private ArrayList<String> emailEmpleados;
    private OrdenInspeccion ordenSeleccionada;
    private String observacionCierre;
    private Estado estadoFueraServicio;
    private Estado estadoCerrada;
    private boolean confirmacion;
    private LocalDate fechaHoraActual;
    private Sismografo sismografoSeleccionado;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiUnidadPersistencia");
    private EntityManager em = emf.createEntityManager();

    public GestorOrdenInspeccion() {
    }
    
    

    public void nuevoCierreOrden(Sesion sesionActual,tablaOrdenesInspeccion pantalla){
        this.sesionActual = sesionActual;
        
        this.ordenDeInspeccion = new ArrayList<>(em.createQuery("SELECT orden FROM OrdenInspeccion orden", OrdenInspeccion.class).getResultList());
        this.sismografos = new ArrayList<>(em.createQuery("SELECT sismografo FROM Sismografo sismografo", Sismografo.class).getResultList());
        this.tipoMotivo = new ArrayList<>(em.createQuery("SELECT tipo FROM TipoMotivo tipo", TipoMotivo.class).getResultList());
        this.empleados = new ArrayList<>(em.createQuery("SELECT empleado FROM Empleado empleado",Empleado.class).getResultList());
        this.estados = new ArrayList<>(em.createQuery("SELECT estado FROM Estado estado", Estado.class).getResultList());
        
        this.confirmacion = false;
        
        this.sismografoDeLaEstacion = new ArrayList<>();
        this.nroOrden = new ArrayList<>();
        this.nombreEstacion = new ArrayList<>();
        this.fechaFin = new ArrayList<>();
        this.infoOrdenada = new ArrayList<>();
        this.motivosFueraServicio = new ArrayList<>();
        this.seleccionMotivos = new ArrayList<>();
        this.comentarios = new ArrayList<>();
        this.emailEmpleados = new ArrayList<>();


        this.buscarEmpleadoLogueado();
        
        this.buscarOrdenesInspeccionCompletamenteRealizadas();
        
        
        this.ordenarPorFechaFin();
        
        pantalla.mostrarOrdenesParaSeleccion(this.infoOrdenada);
    }

    public ArrayList<InfoOrdenada> getInfoOrdenada() {
        return infoOrdenada;
    }
    
    public void buscarEmpleadoLogueado(){
        this.empleadoLogueado = this.sesionActual.buscarEmpleadoLogueado();
    }

    public void buscarOrdenesInspeccionCompletamenteRealizadas() {
        for (OrdenInspeccion orden : this.ordenDeInspeccion) {
            if (orden.esDeEmpleado(this.empleadoLogueado.getId()) && orden.esCompletamenteRealizada() != null) {
                ArrayList respuesta = orden.esCompletamenteRealizada();

                if (respuesta.size() >= 3) {
                    this.nroOrden.add(Integer.valueOf(respuesta.get(0).toString()));
                    this.fechaFin.add(LocalDate.parse(respuesta.get(1).toString()));
                    this.nombreEstacion.add(respuesta.get(2).toString());
                    
                    if (!nombreEstacion.isEmpty()) {
                        this.buscarSismografo(nombreEstacion.getLast());
                        System.out.println("Traje Sismografo");
                    

                    // Validar que todas las listas tengan al menos un elemento
                    if (!nroOrden.isEmpty() && !fechaFin.isEmpty() && !nombreEstacion.isEmpty() && !sismografoDeLaEstacion.isEmpty()) {
                        InfoOrdenada nuevoElemento = new InfoOrdenada(
                            nroOrden.getLast(),
                            fechaFin.getLast(),
                            nombreEstacion.getLast(),
                            sismografoDeLaEstacion.getLast()
                        );

                        this.infoOrdenada.add(nuevoElemento);
                        System.out.println("nueva informacion agregada: " + nuevoElemento);
                    } else {
                        System.out.println("⚠️ No se pudo crear InfoOrdenada: faltan datos.");
                    }

                } else {
                    System.out.println("⚠️ 'respuesta' no tiene los elementos esperados: " + respuesta);
                }
            }
        }
    }
    }


    public ArrayList<OrdenInspeccion> getOrdenDeInspeccion() {
        return this.ordenDeInspeccion;
    }
    
    

    public void buscarSismografo(String nombreEstacion){
        for (Sismografo sismografo : sismografos){
            if(sismografo.esTuEstacion(nombreEstacion)){
                sismografoDeLaEstacion.add(sismografo);
            }
        }
    }

    public void ordenarPorFechaFin(){
        infoOrdenada.sort(Comparator.comparing(o -> o.getFechaFin()));
    }

    public void tomarSeleccionOrden(int nroOrden){
        for(OrdenInspeccion o : this.ordenDeInspeccion){
            if(o.getNumeroOrden() == nroOrden){
                this.ordenSeleccionada = o;
            }
        }
        
        for(InfoOrdenada info : this.infoOrdenada){
            if (info.getNroOrden() == nroOrden){
                //this.simografoSeleccionado = info.getSismografo();
                for(Sismografo s : this.sismografos){
                    if(s.esTuEstacion(info.getNombreEstacion())){
                        this.sismografoSeleccionado = s;
                    }
                } 
            }
        }
        
    }

    public void tomarObservacionCierre(String observacionCierre){
        this.observacionCierre = observacionCierre;
        this.buscarEstadoFueraServicio();
    }

    public void buscarEstadoFueraServicio(){
        for (Estado estado : estados){
            if (estado.esAmbitoSismografo() && estado.esFueraDeServicio() ){
                this.estadoFueraServicio = estado;
                break;
            }
        }
    }

    public void buscarMotivosFueraServicio(CerrarOrdenABM pantalla){
        for (TipoMotivo motivo : this.tipoMotivo){
            this.motivosFueraServicio.add(motivo.getNombreTipoMotivo());
        }
        pantalla.mostrarMotivosParaSeleccion(this.motivosFueraServicio);
    }

    public void tomarSeleccionMotivo(String nombreTipoMotivo){
        this.seleccionMotivos.add(this.tipoMotivo.get(this.motivosFueraServicio.indexOf(nombreTipoMotivo)));
    }

    public void tomarComentario(String comentario) {
        for (int i = 0; i < this.seleccionMotivos.size(); i++) {
            this.comentarios.add(comentario);
        }
    }

    public void tomarConfirmacionCierre(CerrarOrdenABM pantalla) {
        this.confirmacion = true;
        if(this.validarExistenciaObesrvacion()&& this.validarMotivoSeleccionadoMinimo()){
            this.buscarEstadoCerrada();
            this.tomarFechaHoraActual();
            this.cerrarOrdenInspeccion();
            this.inhabilitarSismografo();
            this.buscarEmpleadoResponsable();
            this.enviarMail();
            this.publicarActualizacionEnMonitores();
            this.finCU();
            em.getTransaction().begin();
            em.getTransaction().commit();
            pantalla.mostrarConfirmacion();
        }else{
            pantalla.mostrarError();
        }
        
    }

    public boolean validarExistenciaObesrvacion(){
        return this.observacionCierre != null;
    }

    public boolean validarMotivoSeleccionadoMinimo(){
        return !this.seleccionMotivos.isEmpty();
    }

    public void buscarEstadoCerrada(){
        for (Estado estado : estados){
            if (estado.esAmbitoOrdenInspeccion() && estado.esCerrada() ){
                estadoCerrada = estado;
                break;
            }
        }
    }

    public void tomarFechaHoraActual(){
        this.fechaHoraActual = LocalDate.now();
    }

    public void cerrarOrdenInspeccion(){
        this.ordenSeleccionada.cerrar(this.estadoCerrada,this.fechaHoraActual);
    }

    public void inhabilitarSismografo(){
       this.sismografoSeleccionado.enviarAReparar(
               this.fechaHoraActual, 
               this.estadoFueraServicio, 
               this.empleadoLogueado, 
               this.seleccionMotivos,
               this.comentarios);
       
    }

    public void buscarEmpleadoResponsable(){
        for (Empleado empleado : empleados){
            if (empleado.sosResponsableDeReparacion()){
                this.emailEmpleados.add(empleado.obtenerMail());
            }
        }
    }
    
    private void enviarMail() {
        System.out.println("MAIL ENVIADO");
    }
    
    private void publicarActualizacionEnMonitores() {
       System.out.println("MONITORES ACTUALIZADOS");
    } 
    
    public void finCU(){
        System.out.println("Fin CU");
    } 

}

