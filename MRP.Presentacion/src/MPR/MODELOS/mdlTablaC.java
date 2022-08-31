
package MPR.MODELOS;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Alex T. Rivera
 */

public class mdlTablaC extends AbstractTableModel {
    
    private Class tipoColumnas[];
    private String Columnas[];
    private Boolean editables[];
    int numSem;
    int numPro;

    public mdlTablaC(int numPro, int numSem) {
        this.numSem=numSem;
        this.numPro=numPro;
        configColum(numPro+numSem);
        calcularColumnas();
        
    }
    
    private void configColum(int numCol){
        tipoColumnas = new Class[numCol];
        editables = new Boolean[numCol];
        
        for (int j = 0; j < numCol; j++) {
            tipoColumnas[j] = String.class;
            editables[j] = false;
        }
    }
    
    public void calcularColumnas(){
        int total = numSem+numPro;
        Columnas = new String[total];
        
        int a = numPro;
        for (int i = 0; i < numPro; i++) {
            if (a == 1) {
                Columnas[i]=Integer.toString(a-1);                
            }else{
                Columnas[i]="-"+Integer.toString(a-1);
            }
            a--;
        }
        int b = 1;
        for (int i = numPro; i < total; i++) {
            Columnas[i]=Integer.toString(b);
            b++;
        }
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    @Override
    public int getRowCount() {
        return 0;
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
        return null;
    }
}
