package Interfaces.frontend;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import DAOs.GestorOrdenInspeccion;
import Entidad.InfoOrdenada;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

public class CerrarOrdenABM extends javax.swing.JFrame {
    private java.util.List<JCheckBox> checkBoxes = new ArrayList<>();
    private java.util.List<JTextField> campos = new ArrayList<>();
    private ArrayList <String> motivosTipo;
    private GestorOrdenInspeccion gestor;
    private ArrayList <String> motivosSelec = new ArrayList();
    private ArrayList <String> comentariosSelec = new ArrayList();

    public CerrarOrdenABM(){
        initComponents();
    }
    
    public CerrarOrdenABM(GestorOrdenInspeccion gestor) {
        this.gestor = gestor;
        initComponents();
        this.gestor.buscarMotivosFueraServicio(this);

        
        
        //panelMotivos1.setLayout(new BoxLayout(panelMotivos1, BoxLayout.Y_AXIS));
        //panelMotivos1.setPreferredSize(new Dimension(660, 250)); //tamaño fijo
        //panelMotivos1.setMaximumSize(new Dimension(660, 250));   //evita que se expanda
        panelMotivos1.setLayout(new BoxLayout(panelMotivos1, BoxLayout.Y_AXIS));
        scrollPanelMotivos.setPreferredSize(new java.awt.Dimension(700, 250));
        scrollPanelMotivos.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPanelMotivos.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        //acá se conectaría con el back para que traíga los motivos guardados en la BD
        //o podemos dejarlos a modo de ejemplo para esta entrega
        //esto está en la página 8 de la descripción del dominio
        
     
        
        //ciclo que recorre el array de motivos
        for (String motivo : motivosTipo) {
            //crea el checkbox
            JCheckBox checkBox = new JCheckBox(motivo);
            JLabel lblComentario = new JLabel("Ingrese comentario sobre el motivo seleccionado: ");
            //crea el campo para que escriba un comentario por el motivo seleccionado
            JTextField comentarioPorMotivo = new JTextField(20);
            comentarioPorMotivo.setMaximumSize(new Dimension(600, 25));
            
            //setea como falso al lbl y al field del comentario
            lblComentario.setVisible(false);
            comentarioPorMotivo.setVisible(false);
            
            //si detecta que el checkBox está activo:
            checkBox.addItemListener(e->{
                lblComentario.setVisible(checkBox.isSelected());
                comentarioPorMotivo.setVisible(checkBox.isSelected());
               
                panelMotivos1.revalidate();
                panelMotivos1.repaint();
            });
            checkBoxes.add(checkBox);
            campos.add(comentarioPorMotivo);
            
            panelMotivos1.add(checkBox);
            panelMotivos1.add(lblComentario);
            panelMotivos1.add(comentarioPorMotivo);
            //panelMotivos1.add(Box.createVerticalStrut(10)); //esto es para que haya espacio entre los checkboxes
        }
        panelMotivos1.add(Box.createVerticalGlue());

        this.pack();
      
        this.setSize(800, 600); //tamaño fijo ventana
        this.setLocationRelativeTo(null); //centra la ventana
    }
    
    public void mostrarMotivosParaSeleccion(ArrayList<String> motivosTipo){
        this.motivosTipo = motivosTipo;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelButtons = new javax.swing.JPanel();
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        panelTitle = new javax.swing.JPanel();
        panelInfoOrden = new javax.swing.JPanel();
        fieldObsCierre = new javax.swing.JTextField();
        lblObsCierre = new javax.swing.JLabel();
        scrollPanelMotivos = new javax.swing.JScrollPane();
        panelMotivos1 = new javax.swing.JPanel();
        lblInicioSesion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnConfirmar.setBackground(new java.awt.Color(0, 102, 153));
        btnConfirmar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnConfirmar.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmar.setText("Confirmar cierre");
        btnConfirmar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(0, 0, 0)));
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(102, 102, 102));
        btnCancelar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(0, 0, 0)));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelButtonsLayout = new javax.swing.GroupLayout(panelButtons);
        panelButtons.setLayout(panelButtonsLayout);
        panelButtonsLayout.setHorizontalGroup(
            panelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelButtonsLayout.setVerticalGroup(
            panelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelTitleLayout = new javax.swing.GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        panelTitleLayout.setVerticalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );

        fieldObsCierre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldObsCierreActionPerformed(evt);
            }
        });

        lblObsCierre.setText("Observación de cierre:");

        javax.swing.GroupLayout panelInfoOrdenLayout = new javax.swing.GroupLayout(panelInfoOrden);
        panelInfoOrden.setLayout(panelInfoOrdenLayout);
        panelInfoOrdenLayout.setHorizontalGroup(
            panelInfoOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoOrdenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblObsCierre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fieldObsCierre, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelInfoOrdenLayout.setVerticalGroup(
            panelInfoOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoOrdenLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelInfoOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldObsCierre, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObsCierre))
                .addContainerGap())
        );

        panelMotivos1.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccione motivos fuera de servicio"));

        javax.swing.GroupLayout panelMotivos1Layout = new javax.swing.GroupLayout(panelMotivos1);
        panelMotivos1.setLayout(panelMotivos1Layout);
        panelMotivos1Layout.setHorizontalGroup(
            panelMotivos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 664, Short.MAX_VALUE)
        );
        panelMotivos1Layout.setVerticalGroup(
            panelMotivos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
        );

        scrollPanelMotivos.setViewportView(panelMotivos1);

        lblInicioSesion.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblInicioSesion.setForeground(new java.awt.Color(0, 51, 102));
        lblInicioSesion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInicioSesion.setText("CERRAR ORDEN DE INSPECCIÓN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lblInicioSesion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelInfoOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scrollPanelMotivos, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInicioSesion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelInfoOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scrollPanelMotivos, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(panelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void fieldObsCierreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldObsCierreActionPerformed
        this.gestor.cerrarOrdenInspeccion();
    }//GEN-LAST:event_fieldObsCierreActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        //valida si un checkbox esta seleccionado y el campo de observacion se ingreso,
        //igual esto no se si lo tengo que hacer yo
        //o ya se verificaría con el back, lo dejo por las dudas!!
        
        String observacion = fieldObsCierre.getText().trim();
        if (observacion.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Debe ingresar una observación de cierre.",
                "Campo obligatorio",
                javax.swing.JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
         boolean algunSeleccionado = checkBoxes.stream().anyMatch(JCheckBox::isSelected);
         if (!algunSeleccionado) {
           javax.swing.JOptionPane.showMessageDialog(
               this,
               "Debe seleccionar al menos un motivo antes de cerrar la orden.",
               "Advertencia",
               javax.swing.JOptionPane.WARNING_MESSAGE
           );
           return;
       }
         
         //validar que cada checkbox seleccionado tenga comentario no vacío
        
        for (int i = 0; i < checkBoxes.size(); i++) {
            JCheckBox cb = checkBoxes.get(i);
            JTextField txt = campos.get(i);
            if (cb.isSelected()) {
                String texto = txt.getText().trim();
                if (texto.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "Debe ingresar un comentario para el motivo: \"" + cb.getText() + "\".",
                        "Comentario obligatorio",
                        javax.swing.JOptionPane.WARNING_MESSAGE
                    );
                    txt.requestFocus();
                    return; // corta la ejecución, obliga a corregir
                }
            }
        }

       //solicitar confirmación
       //esto contempla 8. Sistema: solicita confirmación para cerrar la Orden de Inspección. 
       //9. RI: confirma el cierre de la Orden de Inspección. 
       int opcion = javax.swing.JOptionPane.showOptionDialog(
            this,
            "¿Está seguro que desea cerrar la Orden de Inspección?",
            "Confirmar cierre",
            javax.swing.JOptionPane.YES_NO_OPTION,
            javax.swing.JOptionPane.QUESTION_MESSAGE,
            null,
            new Object[]{"Sí", "No"},
            "No"
        );

        if (opcion == 0) {  //0 es "Sí", 1 es "No"
            //hizo clic en "Sí"
            this.gestor.tomarObservacionCierre(observacion);
            
            for (int i = 0; i < checkBoxes.size(); i++) {
                JCheckBox cb = checkBoxes.get(i);
                JTextField txt = campos.get(i);
                if (cb.isSelected()){
                    this.gestor.tomarSeleccionMotivo(cb.getText());
                    this.gestor.tomarComentario(txt.getText());  
                }
            }
            
           
            this.gestor.tomarConfirmacionCierre(this);   
        }    

    }//GEN-LAST:event_btnConfirmarActionPerformed

    public static void main(String args[]) {
   
        java.awt.EventQueue.invokeLater(() -> {
            new CerrarOrdenABM().setVisible(true);         
        });    
    }
    
    public void mostrarError(){
        javax.swing.JOptionPane.showMessageDialog(this, "ERROR EN AUTENTICACION MINIMA");
    }
    
    public void mostrarConfirmacion(){
        javax.swing.JOptionPane.showMessageDialog(this, "Orden cerrada exitosamente.");
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JTextField fieldObsCierre;
    private javax.swing.JLabel lblInicioSesion;
    private javax.swing.JLabel lblObsCierre;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JPanel panelInfoOrden;
    private javax.swing.JPanel panelMotivos1;
    private javax.swing.JPanel panelTitle;
    private javax.swing.JScrollPane scrollPanelMotivos;
    // End of variables declaration//GEN-END:variables
}
