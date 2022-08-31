/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MRP.PRESENTACION;

import MRP.Entidades.PlanMaestro;
import MRP.Entidades.Semana;
import MRP.LogicaNegocio.SemanaLN;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author Alex T. Rivera
 */
public class PlanMaestroVerDLG extends javax.swing.JDialog {

    SemanaLN oSemanaLN = new SemanaLN();
    PlanMaestro oPlanMaestro;
    String modo = "";//nuevo, editar
    List<Semana> listaSemanas;
    
    public PlanMaestroVerDLG(java.awt.Frame parent, boolean modal, PlanMaestro oPlanMaestro, String modo) throws Exception {
        super(parent, modal);
        initComponents();
        this.setTitle("Plan Maestro de Produccion - Tabla");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.modo = modo;
        this.oPlanMaestro = oPlanMaestro;
        pnlTabla.setLayout(new FlowLayout());
        iniciarComponentes();
        generarTabla();
    }
    
    public void iniciarComponentes(){
        if(modo.equals("nuevo")){
            this.btnModificar.setVisible(false);
        }
        if(modo.equals("editar")){
            this.btnModificar.setVisible(true);
        }
    }
    
    
    public void generarTabla() throws Exception{
        String html = "";
        
        listaSemanas = oSemanaLN.ConsultarXPlan(frmPRINCIPAL.oPlanMaestro);
        
        double columnas = oPlanMaestro.getTotalSem();
        
        html += "<html>";
        html += "<table border='1'>";

        //FILA
        html += "<tr>";
        html += "<th scope=\"colgroup\" colspan="+(listaSemanas.size()+1)+">"+oPlanMaestro.getCantidad()+" UNIDADES"+"</th>";

        html += "</tr>";
        
        //FILA
        html += "<tr>";
        html += "<th scope=\"row\">SEMANAS</th>";

        for (int i = 0; i < columnas; i++) {
            html += "<td>"+(i+1)+"</td>";
        }

        html += "</tr>";
        
        //FILA
        html += "<tr>";
        html += "<th scope=\"row\">"+oPlanMaestro.getNombre_plan()+"</th>";

        for (int i = 0; i < columnas; i++) {
            html += "<td>"+listaSemanas.get(i).getCantSemana()+"</td>";
        }
        
        html += "</tr>";

        JLabel tabla = new JLabel(html);
        pnlTabla.add(tabla);
    }
     

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTabla = new javax.swing.JPanel();
        btncancelar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout pnlTablaLayout = new javax.swing.GroupLayout(pnlTabla);
        pnlTabla.setLayout(pnlTablaLayout);
        pnlTablaLayout.setHorizontalGroup(
            pnlTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 853, Short.MAX_VALUE)
        );
        pnlTablaLayout.setVerticalGroup(
            pnlTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 303, Short.MAX_VALUE)
        );

        btncancelar.setText("Salir");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btncancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(pnlTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncancelar)
                    .addComponent(btnModificar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        this.dispose();
        PlanMaestroDLG p = new PlanMaestroDLG(null, true);
        p.modoEtidar(listaSemanas);
        
        p.setVisible(true);
    }//GEN-LAST:event_btnModificarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JPanel pnlTabla;
    // End of variables declaration//GEN-END:variables
}
