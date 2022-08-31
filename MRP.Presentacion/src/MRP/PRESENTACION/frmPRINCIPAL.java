/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MRP.PRESENTACION;

import MRP.Entidades.PlanMaestro;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Alex T. Rivera
 */

public class frmPRINCIPAL extends javax.swing.JFrame {
    
    static PlanMaestro oPlanMaestro;
    static boolean estado;
    
    public frmPRINCIPAL() {
        initComponents();
        this.setLocationRelativeTo(null);
        ActivarMenu(false);
    }

    public void ActivarMenu(boolean estado){
        JMenuConsultas.setVisible(estado);
        JLProducto.setVisible(estado);
        JBComponentes.setVisible(estado);
        JBMRP.setVisible(estado);
        JBNB.setVisible(estado);
        JBNN.setVisible(estado);
    }
    
    public void PlanSeleccionado(PlanMaestro oPlanMaestro){
        this.oPlanMaestro = oPlanMaestro;
        JLProducto.setText(oPlanMaestro.toString());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        JLProducto = new javax.swing.JLabel();
        JBMRP = new javax.swing.JButton();
        JBNB = new javax.swing.JButton();
        JBNN = new javax.swing.JButton();
        JBComponentes = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        JMenuConsultas = new javax.swing.JMenu();
        JMINProductos = new javax.swing.JMenuItem();
        JMIRequerimientos = new javax.swing.JMenuItem();
        JMIRP = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JLProducto.setFont(new java.awt.Font("Monotype Corsiva", 1, 48)); // NOI18N
        JLProducto.setForeground(new java.awt.Color(0, 153, 153));
        JLProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLProducto.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PRODUCTO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 153, 153))); // NOI18N

        JBMRP.setText("PLAN MAESTRO");
        JBMRP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBMRPActionPerformed(evt);
            }
        });

        JBNB.setText("NECESIDADES BRUTAS");
        JBNB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBNBActionPerformed(evt);
            }
        });

        JBNN.setText("NECESIDADES NETAS");
        JBNN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBNNActionPerformed(evt);
            }
        });

        JBComponentes.setText("COMPONENTES");
        JBComponentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBComponentesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JLProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JBNB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JBMRP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JBNN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JBComponentes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(433, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(JLProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JBMRP)
                .addGap(18, 18, 18)
                .addComponent(JBComponentes)
                .addGap(18, 18, 18)
                .addComponent(JBNB)
                .addGap(18, 18, 18)
                .addComponent(JBNN)
                .addGap(0, 205, Short.MAX_VALUE))
        );

        jMenu1.setText("MANTENIMIENTO");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("NUEVO PLAN MAESTRO");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("ABRIR PLAN MAESTRO");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        JMenuConsultas.setText("CONSULTAS");

        JMINProductos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        JMINProductos.setText("COMPONENTES PARA N° PRODUCTOS");
        JMINProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMINProductosActionPerformed(evt);
            }
        });
        JMenuConsultas.add(JMINProductos);

        JMIRequerimientos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        JMIRequerimientos.setText("REQUERIMIENTOS DE MATERIALES");
        JMenuConsultas.add(JMIRequerimientos);

        JMIRP.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        JMIRP.setText("RECEPCIONES PROGRAMADAS");
        JMenuConsultas.add(JMIRP);

        jMenuBar1.add(JMenuConsultas);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBMRPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBMRPActionPerformed
        if(!JLProducto.getText().equals("")){
        if(oPlanMaestro!=null){
            if(oPlanMaestro.getId_plan()!=0){
                try {
                    PlanMaestroVerDLG planReport = new PlanMaestroVerDLG(null, true, this.oPlanMaestro,"editar");
                    planReport.setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(frmPRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Error: Seleccione un producto");
        }
    }
    }//GEN-LAST:event_JBMRPActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        SeleccionarPlanMaestroDLG SP = new SeleccionarPlanMaestroDLG(null, true, this);
        SP.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        PlanMaestroDLG frm = new PlanMaestroDLG(this, true);
        frm.modo = "GUARDAR NUEVO";
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void JBComponentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBComponentesActionPerformed
        ItemsDLG DLG = new ItemsDLG(this, true);
        DLG.setVisible(true);
    }//GEN-LAST:event_JBComponentesActionPerformed

    private void JBNNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBNNActionPerformed
        if(JBNN.isEnabled()){
            NecesidadesNetas NN = new NecesidadesNetas();
        }
    }//GEN-LAST:event_JBNNActionPerformed

    private void JBNBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBNBActionPerformed
        if(JBNB.isEnabled()){
            NecesidadesBrutas NB = new NecesidadesBrutas();
        }
    }//GEN-LAST:event_JBNBActionPerformed

    private void JMINProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMINProductosActionPerformed
        NComponentesDLG NC = new NComponentesDLG(this, true);
        NC.setVisible(true);
    }//GEN-LAST:event_JMINProductosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmPRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPRINCIPAL().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBComponentes;
    private javax.swing.JButton JBMRP;
    private javax.swing.JButton JBNB;
    private javax.swing.JButton JBNN;
    private javax.swing.JLabel JLProducto;
    private javax.swing.JMenuItem JMINProductos;
    private javax.swing.JMenuItem JMIRP;
    private javax.swing.JMenuItem JMIRequerimientos;
    private javax.swing.JMenu JMenuConsultas;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
