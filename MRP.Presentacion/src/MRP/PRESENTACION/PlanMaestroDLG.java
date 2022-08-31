/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MRP.PRESENTACION;

import MRP.Entidades.PlanMaestro;
import MRP.Entidades.Semana;
import MRP.LogicaNegocio.PlanMaestroLN;
import MRP.LogicaNegocio.SemanaLN;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Alex T. Rivera
 */
public class PlanMaestroDLG extends javax.swing.JDialog {

    PlanMaestroLN oPlanMaestroLN = new PlanMaestroLN();
    SemanaLN oSemanaLN = new SemanaLN();
    
    PlanMaestro oPlanMaestro;
    String modo="";
    List<Semana> lista = new ArrayList<>();
    
    /**
     * Creates new form PlanMaestro
     * @param parent
     * @param modal
     */
    public PlanMaestroDLG(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setLocationRelativeTo(null);
//    public PlanMaestroDLG() {
        initComponents();
        JBGuardar.setText("GUARDAR NUEVO");
    }

    public void modoEtidar(List<Semana> lista) {
        try {
            modo = "GUARDAR CAMBIOS";
            JBGuardar.setText("GUARDAR CAMBIOS");
            lista = oSemanaLN.ConsultarXPlan(frmPRINCIPAL.oPlanMaestro);
            
            this.lista = lista;
//            this.oPlanMaestro = lista.get(0).getoPlanMaestro();
            
            JTNombre.setText(frmPRINCIPAL.oPlanMaestro.getNombre_plan());
            JTTotal.setText(frmPRINCIPAL.oPlanMaestro.getCantidad());
            JTNumSemanas.setText(frmPRINCIPAL.oPlanMaestro.getTotalSem().toString());
            
        } catch (Exception ex) {
            Logger.getLogger(PlanMaestroDLG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void GuardarCambios(){
        try {
            
            PlanMaestro oPlanMaestro = lista.get(0).getoPlanMaestro();
            
            oPlanMaestro.setNombre_plan(JTNombre.getText());
            oPlanMaestro.setCantidad(JTTotal.getText());
            oPlanMaestro.setTotalSem(Double.parseDouble(JTNumSemanas.getText()));
            
            oPlanMaestroLN.Modificar(oPlanMaestro);

            for (int i = 0; i < (Integer.parseInt(JTNumSemanas.getText())); i++) {
                oSemanaLN.Modificar(lista.get(i));
            }
            
            frmPRINCIPAL.oPlanMaestro = oPlanMaestro;
            
        } catch (Exception ex) {
            Logger.getLogger(PlanMaestroDLG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void GuardarNuevo(){
        try {
            modo = "GUARDAR NUEVO";
            JBGuardar.setText("GUARDAR NUEVO");
            PlanMaestro oPlanMaestro = new PlanMaestro();
//            PlanMaestro oPlanMaestro = lista.get(0).getoPlanMaestro();
            oPlanMaestro.setNombre_plan(JTNombre.getText());
            oPlanMaestro.setCantidad(JTTotal.getText());
            oPlanMaestro.setTotalSem(Double.parseDouble(JTNumSemanas.getText()));
            
//            System.out.println(lista.get(0).getoPlanMaestro());
            System.out.println(oPlanMaestro.getNombre_plan());
            System.out.println(oPlanMaestro.getCantidad());
            System.out.println(oPlanMaestro.getId_plan());
            oPlanMaestroLN.Insertar(oPlanMaestro);
            
            oPlanMaestro.setId_plan(oPlanMaestroLN.Consultar().get(oPlanMaestroLN.Consultar().size()-1).getId_plan());
            
            System.out.println(oPlanMaestro.getId_plan());
            System.out.println(lista.get(0).getoPlanMaestro().getId_plan() + " " + lista.get(0).getCantSemana() + " " + lista.get(0).getNumSemana());
            
            for (int i = 0; i < lista.size(); i++) {
                oSemanaLN.Insertar(lista.get(i));
            }
            
        } catch (Exception ex) {
            Logger.getLogger(PlanMaestroDLG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        JTNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        JTTotal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        JTNumSemanas = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        JBGuardar = new javax.swing.JButton();

        jLabel1.setText("NOMBRE");

        jLabel2.setText("TOTAL");

        jLabel3.setText("N° SEMANAS");

        JTNumSemanas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTNumSemanasActionPerformed(evt);
            }
        });

        jButton1.setText("CANTIDAD POR SEMANA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        JBGuardar.setText("Salir");
        JBGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JBGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(35, 35, 35)
                                        .addComponent(JTNumSemanas, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel1))
                                        .addGap(55, 55, 55)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(JTNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                                            .addComponent(JTTotal))))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(JTNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(JTTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(JTNumSemanas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(JBGuardar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        double filas = Double.parseDouble(JTNumSemanas.getText());
//        filas = filas*4;
        try {
            if (modo.equals("GUARDAR NUEVO")) {
                PlanMaestro oPlanMaestro = new PlanMaestro();
                oPlanMaestro.setTotalSem(Double.parseDouble(JTNumSemanas.getText()));
                int total = Integer.parseInt(JTTotal.getText());
//                lista = new ArrayList<>();
                PlanMaestroOperDLG c  = new PlanMaestroOperDLG(null, true, JTNumSemanas,filas,total,"Semanas",lista);
                c.setVisible(true);
            }else{
                int total = Integer.parseInt(JTTotal.getText());
                PlanMaestroOperDLG c  = new PlanMaestroOperDLG(null, true, JTNumSemanas,filas,total,"Semanas",lista);
                c.setVisible(true);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Los Datos por Semana no coinciden con el Total Asignado");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void JTNumSemanasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTNumSemanasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTNumSemanasActionPerformed

    private void JBGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBGuardarActionPerformed
            if (modo.equals("GUARDAR NUEVO")) {
                GuardarNuevo();
            }else{
                GuardarCambios();
                JOptionPane.showMessageDialog(this, "Los Datos se Modificaron Correctamente");
            }
            this.dispose();

    }//GEN-LAST:event_JBGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBGuardar;
    private javax.swing.JTextField JTNombre;
    private javax.swing.JTextField JTNumSemanas;
    private javax.swing.JTextField JTTotal;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables

 
}
