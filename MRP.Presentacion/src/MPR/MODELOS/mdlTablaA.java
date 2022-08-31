
package MPR.MODELOS;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Alex T. Rivera
 */

public class mdlTablaA extends AbstractTableModel {
    
    private Class tipoColumnas[];
    private String Columnas[] ={
        "<html><br>Lotificación<br><br></html>",
        "<html>Plazo<br/>(semanas)</html>",
        "<html>Disponible</html>",
        "<html>Stock<br/>de<br/>seguridad</html>",
        "<html>Reservado</html>",
        "<html>Codigo<br/>de nivel<br/></html>",
        "<html>ID del<br/>articulo</html>"
        };
    private Boolean editables[];

    public mdlTablaA(int numCol) {
        configColum(numCol);
    }
    private void configColum(int numCol){
        tipoColumnas = new Class[numCol];
        editables = new Boolean[numCol];

        for (int j = 0; j < numCol; j++) {
            tipoColumnas[j] = String.class;
            editables[j] = false;
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
