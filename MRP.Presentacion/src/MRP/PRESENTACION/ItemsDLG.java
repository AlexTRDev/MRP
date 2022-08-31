/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MRP.PRESENTACION;

import MPR.MODELOS.mdlItems;
import MRP.Entidades.Item;
import MRP.LogicaNegocio.ItemLN;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Alex T. Rivera
 */
public class ItemsDLG extends javax.swing.JDialog {

    ItemLN oItemLN = new ItemLN();
    List<Item> listaItems;
    Item oItem;
    String modo;
    DefaultComboBoxModel mdlCBx;
    
    public ItemsDLG(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        mostrarTabla();
        HabilitarComponentes(false);
        JLBLCantidad.setVisible(false);
        JSPCantidadRecep.setVisible(false);
        JLBLSemana.setVisible(false);
        JSPSemana.setVisible(false);
        
    }

    private void mostrarTabla(){
        try {
            //            Item oItem =  new Item();
            listaItems = oItemLN.Consultar(frmPRINCIPAL.oPlanMaestro);
            System.out.println(listaItems);
            mdlItems mdl = new mdlItems(listaItems);
            JTBItems.setModel(mdl);
        } catch (Exception ex) {
            System.out.println("hola");
            Logger.getLogger(ItemsDLG.class.getName()).log(Level.SEVERE, null, ex);
        }
            

    }
    
    private void LllenarComboBox(){
        try {
            listaItems = new ArrayList<>();
            
            oItem = new Item(0, "<Seleccione>");
            
            listaItems = oItemLN.Consultar(frmPRINCIPAL.oPlanMaestro);
            
            listaItems.add(0, oItem);
            
            mdlCBx = new DefaultComboBoxModel(listaItems.toArray());
            
            JCBDependencia.setModel(mdlCBx);
            
        } catch (Exception ex) {
            Logger.getLogger(ItemsDLG.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void HabilitarComponentes(boolean estado){
        JPNLDatos.setEnabled(estado);
        JTXTIdentificador.setEnabled(estado);
        JTXTNombre.setEnabled(estado);
        JSPCantidadDep.setEnabled(estado);
        JSPDisponible.setEnabled(estado);
        JSPNivel.setEnabled(estado);
        JSPPlazo.setEnabled(estado);
        JSPStock.setEnabled(estado);
        JBTAceptar.setEnabled(estado);
        JBTNCancelar.setEnabled(estado);
        JRBEOQ.setEnabled(estado);
        JRBLAL.setEnabled(estado);
        JChBRecepProgramadas.setEnabled(estado);
        
        JBTNEliminar.setEnabled(!estado);
        JBTNInsertar.setEnabled(!estado);
        JBTNSalir.setEnabled(!estado);
        JBTNModificar.setEnabled(!estado);
        JTBItems.setEnabled(!estado);
    }
    
    private void Limpiar(){
        JTXTIdentificador.setText("");
        JTXTNombre.setText("");
        JSPCantidadDep.setValue(0);
        JSPCantidadRecep.setValue(0);
        JSPDisponible.setValue(0);
        JSPNivel.setValue(0);
        JSPPlazo.setValue(0);
        JSPSemana.setValue(0);
        JSPStock.setValue(0);
        JRBLAL.setSelected(true);
        JChBRecepProgramadas.setSelected(false);
        JCBDependencia.setSelectedIndex(0);
    }
       
    private void Guardar() throws Exception{
        
        if (DatosOK() && modo.equals(JBTNInsertar.getText())) {
            System.out.println("entro insertar");
            oItem = new Item();

            oItem.setNombre(JTXTNombre.getText());
            oItem.setIdentificador(JTXTIdentificador.getText());
            oItem.setCantidad((int) JSPCantidadDep.getValue());
            oItem.setCodigo_nivel((int) JSPNivel.getValue());
            oItem.setDisponible((int) JSPDisponible.getValue());
            oItem.setId_plan(frmPRINCIPAL.oPlanMaestro.getId_plan());
            oItem.setImagen("NO DISPONIBLE");
            oItem.setPlazo((int) JSPPlazo.getValue());
            oItem.setPrecio_almacen(0.0);
            oItem.setPrecio_pedido(0.0);
            oItem.setId_plan(frmPRINCIPAL.oPlanMaestro.getId_plan());
            
            if (JChBRecepProgramadas.isSelected()) {
                oItem.setRecepProgramadas(JSPCantidadRecep.getValue().toString());
                oItem.setSemana_RPP(JSPSemana.getValue().toString());
            }else{
                oItem.setRecepProgramadas("0");
                oItem.setSemana_RPP("0");
            }
            
            oItem.setReservado(0);
            oItem.setStock((int) JSPStock.getValue());
            
            if (JRBEOQ.isSelected()) {
                oItem.setTamaño_lote(JRBEOQ.getText());
            }else{
                oItem.setTamaño_lote(JRBLAL.getText());
            }
            
            oItem.setoItem((Item) JCBDependencia.getSelectedItem());
            System.out.println(oItem.getCantidad() +"  " + oItem.getIdentificador());
            oItemLN.Insertar(oItem);
            
            JOptionPane.showMessageDialog(this, "Se Guardo Correctamente","Mensaje del Sistema",JOptionPane.INFORMATION_MESSAGE);
            try {
            } catch (Exception ex) {
                Logger.getLogger(ItemsDLG.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        }else if (DatosOK() && modo.equals(JBTNModificar.getText())) {
            System.out.println("entro modificar");
            oItem = new Item();
            
            oItem.setId_item(listaItems.get(filaModificar).getId_item());
            oItem.setNombre(JTXTNombre.getText());
            oItem.setIdentificador(JTXTIdentificador.getText());
            oItem.setCantidad((int) JSPCantidadDep.getValue());
            oItem.setCodigo_nivel((int) JSPNivel.getValue());
            oItem.setDisponible((int) JSPDisponible.getValue());
            oItem.setId_plan(frmPRINCIPAL.oPlanMaestro.getId_plan());
            oItem.setImagen("NO DISPONIBLE");
            oItem.setPlazo((int) JSPPlazo.getValue());
            oItem.setPrecio_almacen(0.0);
            oItem.setPrecio_pedido(0.0);
            oItem.setId_plan(frmPRINCIPAL.oPlanMaestro.getId_plan());
            
            if (JChBRecepProgramadas.isSelected()) {
                oItem.setRecepProgramadas((String) JSPCantidadRecep.getValue());
                oItem.setSemana_RPP((String) JSPSemana.getValue());
            }else{
                oItem.setRecepProgramadas("0");
                oItem.setSemana_RPP("0");
            }
            
            oItem.setReservado(0);
            oItem.setStock((int) JSPStock.getValue());
            
            if (JRBEOQ.isSelected()) {
                oItem.setTamaño_lote(JRBEOQ.getText());
            }else{
                oItem.setTamaño_lote(JRBLAL.getText());
            }
            
            oItem.setoItem((Item) JCBDependencia.getSelectedItem());
            
              System.out.println(oItem.getCantidad() +"  " + oItem.getIdentificador());
            oItemLN.Modificar(oItem);
            filaModificar = 0;
        }
        
    }
    
    private boolean DatosOK(){
        
        if (JTXTNombre.getText().equals("") || JTXTIdentificador.getText().equals("") || JCBDependencia.getSelectedIndex()==-1 ) {
            String Mensaje = "Estimado Usuario Asegurese de Llenar los datos Correctamente!! GRACIAS !!";
            JOptionPane.showMessageDialog(this, Mensaje,"Mensaje del Sistema",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
        
    }
    
    int filaModificar;
    private void Modificar(){

        filaModificar = JTBItems.getSelectedRow();
        
        if (filaModificar > 0) {
            modo = JBTNModificar.getText();
            if (JOptionPane.showConfirmDialog(this, "¿Esta Seguro Desea Modificar el Registro?","Mensaje del Sistema",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                JTXTNombre.setText(listaItems.get(filaModificar).getNombre());
                JTXTIdentificador.setText(listaItems.get(filaModificar).getIdentificador());
                JSPCantidadDep.setValue(listaItems.get(filaModificar).getCantidad());
                JSPDisponible.setValue(listaItems.get(filaModificar).getDisponible());
                JSPNivel.setValue(listaItems.get(filaModificar).getCodigo_nivel());
                JSPPlazo.setValue(listaItems.get(filaModificar).getPlazo());
                JSPStock.setValue(listaItems.get(filaModificar).getStock());

                if (listaItems.get(filaModificar).getTamaño_lote().equals(JRBEOQ.getText())) {
                    JRBEOQ.setSelected(true);
                }else{
                    JRBLAL.setSelected(true);
                }

                if (!listaItems.get(filaModificar).getSemana_RPP().equals("") || !listaItems.get(filaModificar).getRecepProgramadas().equals("")) {
                    JChBRecepProgramadas.setSelected(true);
                    JSPSemana.setValue(listaItems.get(filaModificar).getSemana_RPP());
                    JSPCantidadRecep.setValue(listaItems.get(filaModificar).getRecepProgramadas());
                }else{
                    JChBRecepProgramadas.setSelected(false);
                }
            }
        }else{
            JOptionPane.showMessageDialog(this, "Seleccione un Registro");
        }
    }
    
    private void Eliminar(){
        int fila = 0;
        
        fila = JTBItems.getSelectedRow();
        
        if (fila != 0) {
            
            if (JOptionPane.showConfirmDialog(this, "¿Esta Seguro de Eliminar el Registro?","Mensaje del Sistema",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    oItemLN.Eliminar(listaItems.get(fila));
                } catch (Exception ex) {
                    Logger.getLogger(ItemsDLG.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }else{
            JOptionPane.showMessageDialog(this, "Seleccione un Registro");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        JPNLComponentes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTBItems = new javax.swing.JTable();
        JBTNSalir = new javax.swing.JButton();
        JBTNEliminar = new javax.swing.JButton();
        JBTNModificar = new javax.swing.JButton();
        JBTNInsertar = new javax.swing.JButton();
        JPNLDatos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        JTXTNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        JTXTIdentificador = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        JSPNivel = new javax.swing.JSpinner();
        JCBDependencia = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        JSPCantidadDep = new javax.swing.JSpinner();
        JSPPlazo = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        JSPDisponible = new javax.swing.JSpinner();
        JSPStock = new javax.swing.JSpinner();
        JRBLAL = new javax.swing.JRadioButton();
        JRBEOQ = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        JChBRecepProgramadas = new javax.swing.JCheckBox();
        JLBLCantidad = new javax.swing.JLabel();
        JSPCantidadRecep = new javax.swing.JSpinner();
        JLBLSemana = new javax.swing.JLabel();
        JSPSemana = new javax.swing.JSpinner();
        JBTAceptar = new javax.swing.JButton();
        JBTNCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        JPNLComponentes.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 102)), "TABLA DE COMPONENTES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 153, 102))); // NOI18N

        jScrollPane1.setViewportView(JTBItems);

        JBTNSalir.setText("SALIR");
        JBTNSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTNSalirActionPerformed(evt);
            }
        });

        JBTNEliminar.setText("ELIMINAR");
        JBTNEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTNEliminarActionPerformed(evt);
            }
        });

        JBTNModificar.setText("MODIFICAR");
        JBTNModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTNModificarActionPerformed(evt);
            }
        });

        JBTNInsertar.setText("INSERTAR");
        JBTNInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTNInsertarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPNLComponentesLayout = new javax.swing.GroupLayout(JPNLComponentes);
        JPNLComponentes.setLayout(JPNLComponentesLayout);
        JPNLComponentesLayout.setHorizontalGroup(
            JPNLComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNLComponentesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPNLComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNLComponentesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(JBTNInsertar)
                        .addGap(18, 18, 18)
                        .addComponent(JBTNModificar)
                        .addGap(18, 18, 18)
                        .addComponent(JBTNEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(JBTNSalir)))
                .addContainerGap())
        );
        JPNLComponentesLayout.setVerticalGroup(
            JPNLComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNLComponentesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(JPNLComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBTNSalir)
                    .addComponent(JBTNEliminar)
                    .addComponent(JBTNModificar)
                    .addComponent(JBTNInsertar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JPNLDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 102)), "DATOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 153, 102))); // NOI18N

        jLabel1.setText("NOMBRE");

        jLabel2.setText("ABREVIATURA");

        jLabel3.setText("NIVEL");

        jLabel4.setText("DEPENDENCIA");

        JSPNivel.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

        jLabel5.setText("CANTIDAD DEP.");

        JSPCantidadDep.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

        JSPPlazo.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("PLAZO");

        jLabel7.setText("DIPONIBLE");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("STOCK SEGURIDAD");

        JSPDisponible.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

        JSPStock.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

        buttonGroup1.add(JRBLAL);
        JRBLAL.setSelected(true);
        JRBLAL.setText("LAL");

        buttonGroup1.add(JRBEOQ);
        JRBEOQ.setText("EOQ");

        jLabel9.setText("TAMAÑO DE LOTE");

        JChBRecepProgramadas.setText("RECEPCIONES PROGRAMADAS");
        JChBRecepProgramadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JChBRecepProgramadasActionPerformed(evt);
            }
        });

        JLBLCantidad.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        JLBLCantidad.setText("CANTIDAD");

        JSPCantidadRecep.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

        JLBLSemana.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        JLBLSemana.setText("SEMANA");

        JSPSemana.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

        JBTAceptar.setText("ACEPTAR");
        JBTAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTAceptarActionPerformed(evt);
            }
        });

        JBTNCancelar.setText("CANCELAR");
        JBTNCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTNCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPNLDatosLayout = new javax.swing.GroupLayout(JPNLDatos);
        JPNLDatos.setLayout(JPNLDatosLayout);
        JPNLDatosLayout.setHorizontalGroup(
            JPNLDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNLDatosLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(JPNLDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JChBRecepProgramadas)
                    .addGroup(JPNLDatosLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(JRBLAL)
                        .addGap(110, 110, 110)
                        .addComponent(JRBEOQ))
                    .addGroup(JPNLDatosLayout.createSequentialGroup()
                        .addGroup(JPNLDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPNLDatosLayout.createSequentialGroup()
                                .addGroup(JPNLDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(51, 51, 51))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNLDatosLayout.createSequentialGroup()
                                .addComponent(JLBLCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(JPNLDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JSPNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JCBDependencia, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(JPNLDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JPNLDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JPNLDatosLayout.createSequentialGroup()
                                        .addGroup(JPNLDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(JSPCantidadRecep, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(JSPDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(JLBLSemana, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(JSPSemana, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JPNLDatosLayout.createSequentialGroup()
                                        .addComponent(JSPCantidadDep, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(81, 81, 81)
                                        .addGroup(JPNLDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(JPNLDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(JSPPlazo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(JSPStock, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(JPNLDatosLayout.createSequentialGroup()
                                    .addComponent(JBTAceptar)
                                    .addGap(18, 18, 18)
                                    .addComponent(JBTNCancelar)))))
                    .addGroup(JPNLDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(JPNLDatosLayout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(51, 51, 51)
                            .addComponent(JTXTIdentificador))
                        .addGroup(JPNLDatosLayout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(51, 51, 51)
                            .addComponent(JTXTNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        JPNLDatosLayout.setVerticalGroup(
            JPNLDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNLDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPNLDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JTXTNombre)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(JPNLDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JTXTIdentificador)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(JPNLDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(JRBLAL)
                    .addComponent(JRBEOQ))
                .addGap(18, 18, 18)
                .addGroup(JPNLDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JSPNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(JPNLDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JCBDependencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(JPNLDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JSPCantidadDep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JSPPlazo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(JPNLDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JSPDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JSPStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(JChBRecepProgramadas)
                .addGap(18, 18, 18)
                .addGroup(JPNLDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLBLCantidad)
                    .addComponent(JSPCantidadRecep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLBLSemana)
                    .addComponent(JSPSemana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JPNLDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBTNCancelar)
                    .addComponent(JBTAceptar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPNLDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(JPNLComponentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JPNLComponentes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JPNLDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBTNSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTNSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_JBTNSalirActionPerformed

    private void JBTNInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTNInsertarActionPerformed
        modo = JBTNInsertar.getText();
        HabilitarComponentes(true);
        LllenarComboBox();
        
    }//GEN-LAST:event_JBTNInsertarActionPerformed

    private void JBTAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTAceptarActionPerformed
        try {
            Guardar();
            mostrarTabla();
        } catch (Exception ex) {
            Logger.getLogger(ItemsDLG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JBTAceptarActionPerformed

    private void JBTNModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTNModificarActionPerformed
        Modificar();             

    }//GEN-LAST:event_JBTNModificarActionPerformed

    private void JBTNCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTNCancelarActionPerformed
        HabilitarComponentes(false);
        Limpiar();
    }//GEN-LAST:event_JBTNCancelarActionPerformed

    private void JChBRecepProgramadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JChBRecepProgramadasActionPerformed
        if (JChBRecepProgramadas.isSelected()) {
            JLBLCantidad.setVisible(true);
            JSPCantidadRecep.setVisible(true);
            JLBLSemana.setVisible(true);
            JSPSemana.setVisible(true);
        }else{
            JLBLCantidad.setVisible(false);
            JSPCantidadRecep.setVisible(false);
            JLBLSemana.setVisible(false);
            JSPSemana.setVisible(false);
        }
    }//GEN-LAST:event_JChBRecepProgramadasActionPerformed

    private void JBTNEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTNEliminarActionPerformed
        Eliminar();
    }//GEN-LAST:event_JBTNEliminarActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBTAceptar;
    private javax.swing.JButton JBTNCancelar;
    private javax.swing.JButton JBTNEliminar;
    private javax.swing.JButton JBTNInsertar;
    private javax.swing.JButton JBTNModificar;
    private javax.swing.JButton JBTNSalir;
    private javax.swing.JComboBox JCBDependencia;
    private javax.swing.JCheckBox JChBRecepProgramadas;
    private javax.swing.JLabel JLBLCantidad;
    private javax.swing.JLabel JLBLSemana;
    private javax.swing.JPanel JPNLComponentes;
    private javax.swing.JPanel JPNLDatos;
    private javax.swing.JRadioButton JRBEOQ;
    private javax.swing.JRadioButton JRBLAL;
    private javax.swing.JSpinner JSPCantidadDep;
    private javax.swing.JSpinner JSPCantidadRecep;
    private javax.swing.JSpinner JSPDisponible;
    private javax.swing.JSpinner JSPNivel;
    private javax.swing.JSpinner JSPPlazo;
    private javax.swing.JSpinner JSPSemana;
    private javax.swing.JSpinner JSPStock;
    private javax.swing.JTable JTBItems;
    private javax.swing.JTextField JTXTIdentificador;
    private javax.swing.JTextField JTXTNombre;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
