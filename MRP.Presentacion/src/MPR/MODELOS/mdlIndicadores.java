
package MPR.MODELOS;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Alex T. Rivera
 */

public class mdlIndicadores extends AbstractTableModel {
    
    private final Class tipoColumnas[] = {String.class};
    private final String Columnas[] = {""};
    private final Boolean editables[] = {false};
    private List<String> inicadores;

    public mdlIndicadores(int numCol, List<String> indicadores) {
        this.inicadores = indicadores;
//        Columnas = new String[numCol];
//        generarColumnas(numCol);
    }
    private void generarColumnas(int numCol){
        for (int j = 0; j < numCol; j++) {
            Columnas[j]="";
        }
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    @Override
    public int getRowCount() {
        return inicadores.size();
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
            return inicadores.get(rowIndex);
        }else{
            return null;
        }
    }
    
}
