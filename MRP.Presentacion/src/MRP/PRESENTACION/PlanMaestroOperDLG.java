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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Alex T. Rivera
 */
public class PlanMaestroOperDLG extends javax.swing.JDialog {
    
    int filas;
    int cantidadTotal;
    JTextField cantSem;
    String periodo = "";
    List<JLabel> lEtiquetas;
    List<JTextField> ltxts;
    JButton btnGuardar;
    JButton btnCancelar;
    PlanMaestroLN oPlanMaestroLN = new PlanMaestroLN();
    List<PlanMaestro> listaPM = new ArrayList<>();
    SemanaLN oSemanaLN;
    List<Semana> lista;
    
    public PlanMaestroOperDLG(java.awt.Frame parent, boolean modal, JTextField cantSem, double filas, int total, String periodo,List<Semana> lista) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Configuracion");
        this.setSize(210,380);
        this.setLayout(new FlowLayout());
        this.filas =(int)filas;
        this.cantidadTotal = total;
        this.cantSem =cantSem;
        this.periodo = periodo;
        this.lista = lista;
        generarComponentes();
        crearPanel();
        crearBotones();
        cargarDatos(lista);
    }
    
    public void crearPanel(){
        panel.setLayout(new GridLayout(filas, 2));
        JScrollPane scp = new JScrollPane(panel);
        scp.setPreferredSize(new Dimension(180, 300));
        this.add(scp);
    }
    
    public void crearBotones(){
        Dimension dim = new Dimension(86, 23);
        btnGuardar = new JButton("Guardar");
        btnGuardar.setPreferredSize(dim);
        btnCancelar = new JButton("Salir");
        btnCancelar.setPreferredSize(dim);
        btnGuardar.addMouseListener(botonEvento);
        btnCancelar.addMouseListener(botonEvento);
        this.add(btnCancelar);
        this.add(btnGuardar);
    }
    
    public void generarComponentes(){
        lEtiquetas = new ArrayList<>();
        ltxts = new ArrayList<>();
        JLabel lbl;
        JTextField txt;
        String num ="";
        for (int i = 0; i < filas; i++) {
            if(i < 9){
                num = "0"+Integer.toString(i+1);
            }else{
                num = Integer.toString(i+1);
            }
                
            lbl = new JLabel(periodo+" "+num+" :");
            txt = new JTextField();
            lEtiquetas.add(lbl);
            ltxts.add(txt);
            panel.add(lbl);
            panel.add(txt);
        }
    }
    
    public void cargarDatos(List<Semana> lista){
        try {
            if (lista.isEmpty()) {
                for (int i = 0; i < ltxts.size(); i++) {
                    ltxts.get(i).setText("");
                }
            }else{
                for (int i = 0; i < lista.size(); i++) {
                    ltxts.get(i).setText(lista.get(i).getCantSemana());
                }
            }
            
        } catch (Exception ex) {
            Logger.getLogger(PlanMaestroOperDLG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void guardarCambios(){
        if(DatosOK()){
            if (lista.isEmpty()) {
                try {
//                    lista = new ArrayList<>();
                    
                    listaPM = oPlanMaestroLN.Consultar();
                    PlanMaestro oPlanMaestro= new PlanMaestro();
                    oPlanMaestro.setId_plan(listaPM.get(listaPM.size()-1).getId_plan()+1);
                    
                    for (int i = 0; i < ltxts.size(); i++) {
                        Semana sem = new Semana();
                        sem.setoPlanMaestro(oPlanMaestro);
                        sem.setNumSemana(Integer.toString(i+1));
                        sem.setCantSemana(ltxts.get(i).getText());
                        lista.add(i, sem);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(PlanMaestroOperDLG.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                while(!lista.isEmpty()){
                    lista.remove(0);
                }
                
                for (int i = 0; i < ltxts.size(); i++) {
                    Semana sem = new Semana();
                        sem.setoPlanMaestro(frmPRINCIPAL.oPlanMaestro);
                        sem.setNumSemana(Integer.toString(i+1));
                        sem.setCantSemana(ltxts.get(i).getText());
                        lista.add(i, sem);
                }
            }
        }
    }
    
    
    public boolean DatosOK(){
        int sum = 0;
        for (int i = 0; i < ltxts.size(); i++) {
            try {
                sum += Integer.parseInt(ltxts.get(i).getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: tipo de datos no valido");
                return false;
            }
        }
        if(sum != this.cantidadTotal){
            JOptionPane.showMessageDialog(null, "Las cantidades no coinciden\nIngresado: "+sum+"\nTotal: "+cantidadTotal);
            return false;
        }
        return true;
    }
    
    public void salir(){
        this.dispose();
    }
    
    MouseListener botonEvento =new MouseListener(){   

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource() == btnGuardar){
                guardarCambios();
                salir();
            }else{
                salir();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    };
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.FlowLayout());

        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Cantidad por semana"));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 368, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 255, Short.MAX_VALUE)
        );

        getContentPane().add(panel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
