
package MPR.MODELOS;


import MRP.Entidades.Item;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Alex T. Rivera
 */

public class mdlTablaCuerpoPlazo extends AbstractTableModel {
    
    private Class tipoColumnas[];
    private String Columnas[];
    private Boolean editables[];
    private Item oItem;

    public mdlTablaCuerpoPlazo(int numCol, Item oItem) {
        this.oItem = oItem;
        configColum(numCol);
    }
    private void configColum(int numCol){
        tipoColumnas = new Class[numCol];
        Columnas = new String[numCol];
        editables = new Boolean[numCol];
        for (int j = 0; j < numCol; j++) {
            tipoColumnas[j] = String.class;
            Columnas[j]="";
            editables[j] = false;
        }
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount() {
        return Columnas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return Columnas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int column) {
        return tipoColumnas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex==0){
            String Plazo;
            Plazo = "<br><h3>"+oItem.getPlazo()+"</h3>";
            
            return "<html>"+Plazo+"</html>";
        }else{
            return null;
        }
    }
    
}
