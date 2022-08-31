
package MRP.PRESENTACION;


import MPR.MODELOS.mdlTablaA;
import MPR.MODELOS.mdlTablaB;
import MPR.MODELOS.mdlTablaC;
import MPR.MODELOS.mdlTipoLote;
import MPR.MODELOS.mdlIndicadores;
import MRP.AccesoDatos.ItemAD;
import MRP.Entidades.Item;
import MRP.Entidades.TablaComponente;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Alex T. Rivera
 */

public class EstructuraNN {
    public JFrame ventana;
    public JScrollPane scroll;
    public JTable tp;
    public JTable ti;
    public JTable tv;
    private int Dim = 1320;
    
    private List<Item> listaItems;
    private ItemAD oItemAD;
    
    
    List<JPanel> lPaneles;
    JPanel fondo;
    JPanel TituloFondo;
    
//    List<Componente> lArticulos;
    List<TablaComponente> lTablaComponentes;
    int semanas;
    int numSem;
    int numPro;
    //dimenciones para scoll de tablas
    int largo3 = 720;
    
    public EstructuraNN(List<Item> listaItems, List<TablaComponente> lTablaComponentes, int numPro, int numSem) {
        ventana = new JFrame();
        ventana.setLayout(new FlowLayout());
        ventana.setSize(Dim+20,720);
        ventana.setTitle("[MRP] - NECESIDADES DETAS");
        this.listaItems = listaItems;
        this.lTablaComponentes = lTablaComponentes;
        this.semanas = lTablaComponentes.get(0).getTabla()[0].length;
        this.numPro =numPro;
        this.numSem =numSem;
        encabezado();
        paneles();
        
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
    }
    
    public void encabezado(){
        TituloFondo = new JPanel();
        TituloFondo.setLayout(new FlowLayout());
        TituloFondo.setSize(new Dimension(520,100));
//        TituloFondo.setSize(new Dimension(1070,100));
        TituloFondo.setBackground(Color.BLACK);
        
        JPanel panelDoble = new JPanel();
        panelDoble.setLayout(new GridLayout(2,1));
        panelDoble.setBackground(Color.BLUE);

        JTable t1 = new JTable();
        t1.setModel(new mdlTablaA(7));
        JScrollPane jsp1 = new JScrollPane(t1);
        jsp1.setPreferredSize(new Dimension(470,54));
        
        JTable t2 = new JTable();
        t2.setModel(new mdlTablaB(1));
        JScrollPane jsp2 = new JScrollPane(t2);
        jsp2.setPreferredSize(new Dimension(220,54));
        
        JTable t3 = new JTable();
        t3.setModel(new mdlTablaC(this.numPro,this.numSem));//8 = semanas
        JScrollPane jsp3 = new JScrollPane(t3);
        jsp3.setPreferredSize(new Dimension(largo3,22));
        
        JLabel lbl = new JLabel("<html><p ALIGN='center'>SEMANA</p></html>");
        panelDoble.setBackground(Color.lightGray);
        
        panelDoble.add(lbl);
        panelDoble.add(jsp3);
        
        TituloFondo.add(jsp1);
        TituloFondo.add(jsp2);
        TituloFondo.add(panelDoble);
        
        JScrollPane s;
        s = new JScrollPane(TituloFondo);
//        s.setPreferredSize(new Dimension(520,80));
        s.setPreferredSize(new Dimension(Dim,80));
        ventana.add(s);
        
        centrarCeldas(t1);
        centrarCeldas(t3);
    }
    
    public void centrarCeldas(JTable table){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(tcr); 
        }  

    }
    
    public void paneles(){

        lPaneles = new ArrayList<>();
        fondo = new JPanel();
//        fondo.setLayout(new GridLayout(50, 1));
        fondo.setLayout(new GridLayout(listaItems.size(), 1));
        for (int i = 0; i < listaItems.size(); i++) {
            JPanel p = new JPanel();
            p.setLayout(new FlowLayout());
            p.setBackground(Color.BLACK);
            p.add(tablaProducto(listaItems.get(i)));
            p.add(tablaIndicadores(listaItems.get(i)));
            p.add(tablaProgramacion(listaItems.get(i)));
            lPaneles.add(p);
            fondo.add(p);
        }
        scroll = new JScrollPane(fondo);
        scroll.setPreferredSize(new Dimension(Dim,590));
        ventana.add(scroll);
    }
    
    public JScrollPane tablaProducto(Item oItem){
        tv = new JTable();
        tv.setModel(new mdlTipoLote(7, oItem));
        tv.setRowHeight(95);
        JScrollPane jsp = new JScrollPane(tv);
        jsp.setPreferredSize(new Dimension(470,105));
        centrarCeldas(tv);
        return jsp;
    }
    
    public JScrollPane tablaIndicadores(Item oItem){
        List<String> inicadores  = new ArrayList<>();
        inicadores.add("Necesidades brutas");
        inicadores.add("Recepcion programada");
        inicadores.add("Disponible previsto     |     "+oItem.getDisponible());
        inicadores.add("Necesidades netas");
        inicadores.add("Recepcion de órdenes planificadas");
        inicadores.add("Lanzamiento de órdenes planificadas");
        
        tv = new JTable();
        tv.setModel(new mdlIndicadores(2,inicadores));
        JScrollPane jsp = new JScrollPane(tv);
        jsp.setPreferredSize(new Dimension(220,105));
        return jsp;
//                TableColumnModel columnModel = tv.getColumnModel();
//        columnModel.getColumn(0).setPreferredWidth(100);
    }
    
    public JScrollPane tablaProgramacion(Item oItem){
        
        tp = new JTable(getTabla(oItem),cabezera(semanas));
        centrarCeldas(tp);
        JScrollPane jsp = new JScrollPane(tp);
        jsp.setPreferredSize(new Dimension(720,105));
//        jsp.setPreferredSize(new Dimension(largo3,105));
        return jsp;
    }
    
    public String[][] arreglo(int row, int column){
        String[][] malla = new String[row][column];
        for (int i = 0; i < malla.length ; i++) {
            for (int j = 0; j <  malla[0].length; j++) {
                malla[i][j]=Integer.toString(i)+":"+Integer.toString(j);
            }
        }
        return malla;
    }
    
    public String[] cabezera(int semanas){
        String[] titulo = new String[semanas];
        for (int i = 0; i < semanas; i++) {
            titulo[i] = "";
        }
        return titulo;
    }
    
    public String[][] getTabla(Item oItem){//busca su respectiva tabla del componente x
        for (int i = 0; i < lTablaComponentes.size(); i++) {
            if(lTablaComponentes.get(i).getIdItem()==oItem.getId_item()){
                return lTablaComponentes.get(i).getTabla();
            }
        }
        return null;
    }
    
    
    /*-----------------------------Delacaraciones--------------------------------*/

    
}
