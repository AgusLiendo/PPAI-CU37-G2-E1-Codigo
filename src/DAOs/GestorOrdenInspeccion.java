package DAOs;

import Entidad.*;
import Interfaces.frontend.tablaOrdenesInspeccion;
import Interfaces.frontend.CerrarOrdenABM;
import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class GestorOrdenInspeccion {
    // Definicion de los atributos del gestor --> Es la informacion que almacena durante la secuencia del Caso de Uso
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
        // sesion actual del Usuario Logueado --> es el puntero a la actual sesion
        this.sesionActual = sesionActual;
        
        // Por medio de una consulta nos conectamos a la base de datos y traemos todos los repositorios de las entidades que necesitemos implementar
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

        //El gestor invoca los metodos de la entidad sesion
        this.buscarEmpleadoLogueado();
        
        /*
        El gestor invoca el metodo que trae todas las ordenes de inspecciones 
        que son completamente realizadas y que pertenecen al responsable de inspeccion
        */
        this.buscarOrdenesInspeccionCompletamenteRealizadas();
        
        //El gestor invoca el metodo que le permite ordenar las ordenes de Inspeccion Segun su fecha de Finalizacion
        this.ordenarPorFechaFinalizacion();
        
        /*
        Invoca el metodo de la pantalla Mostrar Ordenes para la seleccion 
        y le pasa en el parametro las ordenes ordenadas para que las muestre
        */
        pantalla.mostrarOrdenesParaSeleccion(this.infoOrdenada);
    }
    
    public ArrayList<InfoOrdenada> getInfoOrdenada() {
        return this.infoOrdenada;
    }
    
    public void buscarEmpleadoLogueado(){
        this.empleadoLogueado = this.sesionActual.buscarEmpleadoLogueado();
    }
    
    /*
        Se encarga de buscar las ordenes completamente realizadas:
            Para eso se aplica el patron experto para buscar la informacion segun quien la contenga
            Para el caso de buscar el sismografo se aprovecha la visibilidad sobre el objeto,
            ya que el sismografo posee el puntero de su estacion
        
    */
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
                    }

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

    /*
        Para buscar el sismografo Asociado a una orden, se recorre el array de sismografos
        verificando si coincide con la estacion de la orden
    */
    public void buscarSismografo(String nombreEstacion){
        for (Sismografo sismografo : sismografos){
            if(sismografo.esTuEstacion(nombreEstacion)){
                this.sismografoDeLaEstacion.add(sismografo);
            }
        }
    }

    /*
        Implementa el metodo sort para ordenar el array de ordenes de Inspeccion
        que son de un determinado responsable de Inspeccion
    */
    public void ordenarPorFechaFinalizacion(){
        this.infoOrdenada.sort(Comparator.comparing(o -> o.getFechaFinalizacion()));
    }

    /*
        Este metodo se encarga de guardar la orden seleccionada desde la pantalla,
        por el responsable de Inspeccion
    */
    public void tomarSeleccionOrden(int nroOrden){
        for(OrdenInspeccion o : this.ordenDeInspeccion){
            if(o.getNumeroOrden() == nroOrden){
                this.ordenSeleccionada = o;
            }
        }
        // Se utliza una clase auxiliar para guardar los datos y poder manipularlos con mayor facilidad
        for(InfoOrdenada info : this.infoOrdenada){
            if (info.getNroOrden() == nroOrden){
                //this.simografoSeleccionado = info.getSismografo();
                for(Sismografo s : this.sismografos){
                    if(s.esTuEstacion(info.getNombre())){
                        this.sismografoSeleccionado = s;
                    }
                } 
            }
        }
        
    }
    
    /*
        Este metodo se encarga de guardar la observacion ingresada desde la pantalla,
        por el responsable de Inspeccion
    */
    public void tomarObservacionCierre(String observacionCierre){
        this.observacionCierre = observacionCierre;
        //Genera una invocacion al metodo que debe buscar el puntero del Estado fuera de servicio del sismografo
        this.buscarEstadoFueraServicio();
    }
    
    
    /*
        Este metodo se encarga de buscar entre todos los estados existentes, 
        el puntero del Estado fuera de servicio del sismografo
    */
    public void buscarEstadoFueraServicio(){
        for (Estado estado : estados){
            if (estado.esAmbitoSismografo() && estado.esFueraDeServicio() ){
                this.estadoFueraServicio = estado;
                break;
            }
        }
    }
    
    /*
        Este metodo se encarga de traer todos los tipos de motivos por los que un sismografo
        puede quedar en estado fuera de servicio, almacenando el nombre por medio del metodo getNombre() 
        que esta definido en la entidad TipoMotivo
    */
    public void buscarMotivosEstadoFueraServicio(CerrarOrdenABM pantalla){
        for (TipoMotivo motivo : this.tipoMotivo){
            this.motivosFueraServicio.add(motivo.getNombreMotivo());
        }
        //despues de traerlos se los pasa a la pantalla para que el responsable pueda seleccionarlos
        pantalla.mostrarMotivosParaSeleccion(this.motivosFueraServicio);
    }
    
    /*
        El gestor recibe de la pantalla por medio de este metodo 
        los motivos que han sido seleccionados por el responsable
    */
    public void tomarSeleccionMotivo(String nombreTipoMotivo){
        //utilizamos el indexOf para traer el puntero del motivo por medio del atributo nombre
        this.seleccionMotivos.add(this.tipoMotivo.get(this.motivosFueraServicio.indexOf(nombreTipoMotivo)));
    }
    
    /*
        
    */
    public void tomarComentario(String comentario) {
        for (int i = 0; i < this.seleccionMotivos.size(); i++) {
            this.comentarios.add(comentario);
        }
    }

    public void tomarConfirmacionCierre(CerrarOrdenABM pantalla) {
        this.confirmacion = true;
        if(this.validarExistenciaObservacion()&& this.validarMotivoSeleccionadoMinimo()){
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

    public boolean validarExistenciaObservacion(){
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

