/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MPR.MODELOS;

import MRP.Entidades.Item;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Alex T. Rivera
 */
public class mdlItems extends AbstractTableModel{
    
    private String columnas[] ={"ID","NOMBRE","ABREVIATURA","NIVEL","CANTIDAD","PLAZO","DEPENDENCIA"};
    private List<Item> listaItems;
    
    public mdlItems(List<Item> listaItems){
        this.listaItems = listaItems;
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
    
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        switch (columnIndex) {
            case 0: return listaItems.get(rowIndex).getId_item();
            case 1: return listaItems.get(rowIndex).getNombre();
            case 2: return listaItems.get(rowIndex).getIdentificador();
            case 3: return listaItems.get(rowIndex).getCodigo_nivel();
            case 4: return listaItems.get(rowIndex).getCantidad();
            case 5: return listaItems.get(rowIndex).getPlazo();
            case 6: return listaItems.get(rowIndex).getoItem().getId_item();
            default: return null;
        }
        
    }
    @Override
    public String getColumnName(int column){
        return columnas[column];
    }
}
