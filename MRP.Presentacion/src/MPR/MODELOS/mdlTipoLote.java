
package MPR.MODELOS;


import MRP.Entidades.Item;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Alex T. Rivera
 */

public class mdlTipoLote extends AbstractTableModel {
    
    private Class tipoColumnas[];
    private String Columnas[];
    private Boolean editables[];
    private Item oItem;

    public mdlTipoLote(int numCol, Item oItem) {
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
            String lotificacion;
            if(oItem.getTamaño_lote().equals("LAL")){
                lotificacion = "LAL";
            }else{
                lotificacion = "EOQ<br>("+oItem.getTamaño_lote()+")";
            } 
            return "<html>"+lotificacion+"</html>";
        }else if(columnIndex==1){
            return oItem.getPlazo();
        }else if(columnIndex==2){
            return oItem.getDisponible();
        }else if(columnIndex==3){
            return "-";
        }else if(columnIndex==4){
            return "-";
        }else if(columnIndex==5){
            return oItem.getCodigo_nivel();
        }else if(columnIndex==6){
            return oItem.getIdentificador();
        }else{
            return null;
        }
    }
    
}
