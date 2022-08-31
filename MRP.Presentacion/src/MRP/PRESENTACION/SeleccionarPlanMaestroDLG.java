/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MRP.PRESENTACION;

import MRP.Entidades.Item;
import MRP.Entidades.PlanMaestro;
import MRP.LogicaNegocio.ItemLN;
import MRP.LogicaNegocio.PlanMaestroLN;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


/**
 *
 * @author Alex T. Rivera
 */
public class SeleccionarPlanMaestroDLG extends javax.swing.JDialog {

    PlanMaestroLN oPlanMaestroLN;
    List<PlanMaestro> planes;
    DefaultComboBoxModel modeloCbxPlanes; 
    frmPRINCIPAL menu;
    PlanMaestro planElegido;
    String rutaImagen = "sin_imagen";
    
    public SeleccionarPlanMaestroDLG(java.awt.Frame parent, boolean modal, frmPRINCIPAL menu) {
        super(parent, modal);
        initComponents();
        this.setTitle("MRP - Elija Un plan");
        this.setLocationRelativeTo(null);
        this.menu = menu;
        CargarSelector();
        verImagen(lblImagen);
    }
    
    

    public void CargarSelector(){
        oPlanMaestroLN = new PlanMaestroLN();
        planes = new ArrayList<>();
        PlanMaestro index = new PlanMaestro(0, "<Seleccionar>", null,null);
        
        try {
            planes = oPlanMaestroLN.Consultar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
        }
        planes.add(0,index);
        modeloCbxPlanes = new DefaultComboBoxModel(planes.toArray());
        cbxPlanes.setModel(modeloCbxPlanes);
        
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbxPlanes = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        lblImagen = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cbxPlanes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxPlanes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxPlanesActionPerformed(evt);
            }
        });

        jLabel1.setText("Elija un Plan Maestro De Produccion:");

        jButton1.setText("Abrir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImagen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxPlanes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 206, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbxPlanes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        planElegido = (PlanMaestro)cbxPlanes.getSelectedItem();
        if(planElegido.getId_plan()!=0){
            this.menu.ActivarMenu(true);
            this.menu.PlanSeleccionado(planElegido);
//            this.menu.planElegido;
            frmPRINCIPAL.oPlanMaestro = planElegido;
        }
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    
    public void verImagen(JLabel label) {
//        if(rutaImagen.equals("sin_imagen")){
//            ImageIcon imagen = new ImageIcon(getClass().getResource("imagenes/default.jpg"));        
//            Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), WIDTH));
//            label.setIcon(icono);
//        }else{
//            ImageIcon imagen = new ImageIcon(rutaImagen);        
//            Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), WIDTH));
//            label.setIcon(icono);
//        }
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbxPlanesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxPlanesActionPerformed
        //consulta el componente del nivel 0
        //muestra su imagen
        ItemLN oItemLN = new ItemLN();
        planElegido = (PlanMaestro)cbxPlanes.getSelectedItem();
        Item oItem = new Item();
        try {
            oItem = oItemLN.ConsultarPrincipal(planElegido);
            rutaImagen = oItem.getImagen();
                    verImagen(lblImagen);
        } catch (Exception e) {
            rutaImagen = "sin_imagen";
            verImagen(lblImagen);
        }
    }//GEN-LAST:event_cbxPlanesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbxPlanes;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblImagen;
    // End of variables declaration//GEN-END:variables
}
