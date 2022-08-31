/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MPR.MODELOS;

import MRP.Entidades.Datos;
import MRP.Entidades.Item;
import MRP.Entidades.Semana;
import MRP.PRESENTACION.frmPRINCIPAL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Alex T. Rivera
 */
public class mdlNComponentes extends AbstractTableModel{
    
    private String columnas[] ={"NOMBRE","ABREVIATURA","CANTIDAD","CALCULO","TOTAL"};
    private List<Item> listaItems;
    private int Calculo;
    
    public mdlNComponentes(List<Item> listaItems, int Calculo){
        this.listaItems = listaItems;
        this.Calculo = Calculo;
        cargarDatosInciales();
    }
    
    @Override
    public int getRowCount(){
        return this.listaItems.size();
    }
    
    @Override
    public int getColumnCount(){
        return this.columnas.length;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    List<Datos> listaDatos = new ArrayList<>();
    
    private void cargarDatosInciales(){
        listaDatos.add(new Datos(0, 0, listaItems.get(0).getCantidad()));
        System.out.println(listaDatos);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        switch (columnIndex) {
            case 0: return listaItems.get(rowIndex).getNombre();
            case 1: return listaItems.get(rowIndex).getIdentificador();
            case 2: return listaItems.get(rowIndex).getCantidad();
            case 3: return listaItems.get(rowIndex).getIdentificador() +"*"+Calculo;
            case 4: return listaItems.get(rowIndex).getCantidad()*Calculo;
            default: return null;
        }
        
    }
    @Override
    public String getColumnName(int column){
        return columnas[column];
    }
}
