/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MRP.PRESENTACION;

import MRP.Entidades.Datos;
import MRP.Entidades.Item;
import MRP.Entidades.PlanMaestro;
import MRP.Entidades.Semana;
import MRP.Entidades.TablaComponente;
import MRP.LogicaNegocio.ItemLN;
import MRP.LogicaNegocio.SemanaLN;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Alex T. Rivera
 */
public class NecesidadesNetas {
    
    private int totalSemanas;
    private int semanasPedidos;
    private int semanasProceso;
    
    private int[][] datosPadre = new int[87][3];

    SemanaLN oSemanaLN;
    List<Item> listaItems;
    ItemLN oItemLN;
    List<Datos> listaDatos;
    List<TablaComponente> listaTablas = new ArrayList<>();
    PlanMaestro oPlanMaestro = new PlanMaestro(1, null, null, null);
    int niveles = 0 ;
    
    public NecesidadesNetas() {
        oSemanaLN = new SemanaLN();
        oItemLN = new ItemLN();
        listaDatos = new ArrayList<>();
        consultarDatos();
        cargarDatosInciales();
        calcularTiempoProceso();
        master();
    }
    
    

    private void consultarDatos(){
        try {
            listaItems  = oItemLN.Consultar(frmPRINCIPAL.oPlanMaestro);
            niveles = oItemLN.nivelMax(frmPRINCIPAL.oPlanMaestro);
            
            List<Semana>listaSemanas = oSemanaLN.ConsultarXPlan(frmPRINCIPAL.oPlanMaestro);
            semanasPedidos = listaSemanas.size();
            semanasProceso = calcularTiempoProceso();
            totalSemanas = semanasPedidos+semanasProceso;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
        }
    }
    
    public int sumarPlazos(Item oItem){
        if(oItem.getoItem().getId_item()==0){
            return oItem.getPlazo();
        }else{
            try {
                int sum = oItem.getPlazo()+sumarPlazos(getPadre(oItem));
                return sum; 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
            }
        }
        return 0;
    }
     
    private int calcularTiempoProceso(){
        int may = 0;
        int sum = 0;
        for (int i = 0; i < listaItems.size(); i++) {
            sum = sumarPlazos(listaItems.get(i));
            if(sum >= may){
                may = sum;
            }
        }
        return may;
    }
    
    private void cargarDatosInciales(){
        try {
            int t = this.semanasProceso;
            List<Semana>listaSemanas = oSemanaLN.ConsultarXPlan(frmPRINCIPAL.oPlanMaestro);
            
            System.out.println(listaSemanas);
            
            for (int i = 0; i < listaSemanas.size(); i++) {
                if (listaSemanas.get(i).getCantSemana().equals("x") || listaSemanas.get(i).getCantSemana().equals("X")) {
                    listaDatos.add(new Datos(0, (t+i), 0));
                }else{
                    listaDatos.add(new Datos(0, (t+i), Integer.parseInt(listaSemanas.get(i).getCantSemana())));
                }
            }
            System.out.println(listaDatos);
        } catch (Exception ex) {
            Logger.getLogger(NecesidadesNetas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int[][] ubicarNecesidadesBrutas(int[][] t , Item oItem){
        int id_padre;
        int multiplo = 1;
        List<Datos> listaOrdenes = new ArrayList<>();
        Item padre = getPadre(oItem);
        
        if(padre == null){
            id_padre = 0;
        }else{
            id_padre = padre.getId_item();
            multiplo = oItem.getCantidad();
        }
        
        for (int i = 0; i < listaDatos.size(); i++) {
            if(listaDatos.get(i).getId_componente() == id_padre){
                listaOrdenes.add(listaDatos.get(i));
            }
        }
        
        for (int i = 0; i < listaOrdenes.size(); i++) {
            t[0][listaOrdenes.get(i).getSemana()] = listaOrdenes.get(i).getCantidad()*multiplo;
        }
        return t;
    }
    
    private void master(){
        //resolveremos las necesidades brutas por niveles 0,1,2,3,4,5,....
        //creamos una lisata para almacenar los componentes por nivel.
        List<Item> lNivel; 
        for (int i = 0; i < niveles+1; i++) {
            lNivel = getComponentesNivel(i);
            for (int j = 0; j < lNivel.size(); j++) {
                resolverMatriz(lNivel.get(j));
            }
        }
        EstructuraNN e = new EstructuraNN(listaItems, listaTablas, this.semanasProceso, this.semanasPedidos);
    }   
    
    public void resolverMatriz(Item oItem){
        //lotificacion LAL/EOQ
        
        int necesidadesBrutas = 0;//i=0
        int recepcionesProgramadas = 0;//i=1
        int disponiblePrevisto = oItem.getDisponible();//i=2
        int necesidadesNetas = 0;//i=3
        int recepcionOrdenesPlanificadas = 0;//i=4
        int lanzamientoOrdenesPlanificadas = 0;//i=5
        int lettime = oItem.getPlazo();
        
        int[][] tabla = new int[6][totalSemanas];
        
        //ubicamos las necesidades Netas de cada producto: semana y cantidad
        ubicarNecesidadesBrutas(tabla,oItem);
        //ubicamos la recpcion programada: semana y cantidad
        tabla[1][1]=0;
        //calculamos disponible previsto
        boolean operacion = true;
        for (int i = 0; i < tabla[0].length; i++) {
            if(operacion){
                tabla[2][i]=disponiblePrevisto+tabla[1][i];
                operacion = false;
            }else{
                tabla[2][i]=tabla[2][i-1]+tabla[1][i];
            }

            if(tabla[0][i]!=0){
                operacion = true;
                int netas =tabla[0][i]-tabla[2][i];
                if(netas>0){
                    tabla[3][i]=netas;
                    tabla[4][i] = tabla[3][i];
                    disponiblePrevisto=0;
                }else{
                    tabla[3][i]=-1;
                    tabla[4][i] = 0;
                    disponiblePrevisto=Math.abs(netas);
                }
                
                try {
                    if(!oItem.getTamaño_lote().equals("LAL")){
                        
                        int mul = 1;
                        int lote = 3;
                        try {
                            lote = Integer.parseInt(oItem.getTamaño_lote());
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
                        }
                        if(tabla[4][i]<lote){
                            if(tabla[4][i]==0){
                                tabla[5][i-lettime] = 0;
                            }else{
                                tabla[5][i-lettime] = lote;
                            }
                            
                        }else{
                            int incremento = lote;
                            while(lote<tabla[4][i]){
                                lote = lote+incremento;
                            }
                            tabla[5][i-lettime] = lote;
                        }
                        
                    }else{
                        tabla[5][i-lettime] = tabla[4][i];
                    }
                    //tabla[5][i-lettime] = tabla[4][i];
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Alerte: "+e.getMessage()+" Error: tabla[5]["+(i-lettime)+"]");
                }
            }
        }
        guardarOrdenes(tabla, oItem.getId_item(), oItem.getTamaño_lote(), oItem.getPlazo());
        GuardarTabla(tabla, oItem.getId_item());
    }
    
    public Item getPadre(Item oItem){
        for (int i = 0; i < listaItems.size(); i++) {
            if(oItem.getoItem().getId_item() == listaItems.get(i).getId_item()){
                return listaItems.get(i);
            }
        }
        return null;
    }
    
    public List<Item> getComponentesNivel(int nivel){
        List<Item> l = new ArrayList<>();
        for (int i = 0; i < listaItems.size(); i++) {
            if(listaItems.get(i).getCodigo_nivel() == nivel){
                l.add(listaItems.get(i));
            }
        }
        return l;
    }
    
    public List<Datos> getDatosPadre(Item oItem){
        List<Datos> l = new ArrayList<>();
        for (int i = 0; i < listaDatos.size(); i++) {
            if(oItem.getoItem().getId_item() == listaDatos.get(i).getId_componente()){
                l.add(listaDatos.get(i));
            }
        }
        return l;
    }
    
    public void guardarOrdenes(int[][] t,int id_componente, String lot, int plazo){
        for (int i = 0; i < t[0].length; i++) {
            if(t[5][i]!=0){
                if(lot.equals("LAL")){
                    listaDatos.add(new Datos(id_componente, i, t[5][i]));
                }else{
                    listaDatos.add(new Datos(id_componente, (i-plazo), t[4][i]));
                }
            }
        }
    }
    
    public  void GuardarTabla(int tabla[][], int id_componente){
        String[][] t = new String[tabla.length][tabla[0].length];
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[0].length; j++) {
                if((i!=2) && tabla[i][j]==0){
                    t[i][j] = "";
                }else{
                    if(tabla[i][j]==-1){
                        t[i][j] = "0";
                    }else{
                        t[i][j] = Integer.toString(tabla[i][j]);
                    }
                }
            }
        }
//        System.out.println(listaTablas);
        listaTablas.add(new TablaComponente(id_componente, t));
    }
    
}
