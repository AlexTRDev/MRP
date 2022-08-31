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
public class NecesidadesBrutas {
    
    private int totalSemanas;
    private int semanasPedidos;
    private int semanasProceso;
    
    SemanaLN oSemanaLN;
    List<Item> listaItems;
    ItemLN oItemLN;
    List<Datos> listaDatos;
    List<TablaComponente> listaTablas = new ArrayList<>();
    int niveles = 0 ;
    
    public NecesidadesBrutas() {
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
    
    private int sumarPlazos(Item oItem){
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
            Logger.getLogger(NecesidadesBrutas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private int[][] ubicarNecesidadesBrutas(int[][] t , Item oItem){
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
        
        EstructuraNB e = new EstructuraNB(listaItems, listaTablas, this.semanasProceso, this.semanasPedidos);
    }   
    
    public void resolverMatriz(Item oItem){
        //lotificacion LAL/EOQ
        
//        int Fecha Requerida = 0;//i=0
//        int Fecha Lanzamiento Orden = 0;//i=1

        int Plazo = oItem.getPlazo();
        
        int[][] tabla = new int[2][totalSemanas];
        
        //ubicamos las necesidades Netas de cada producto: semana y cantidad
        ubicarNecesidadesBrutas(tabla,oItem);
        //ubicamos la recpcion programada: semana y cantidad
        tabla[1][1]=0;
        //calculamos disponible previsto
        boolean operacion = true;
        for (int i = 0; i < tabla[0].length; i++) {
            
            if(tabla[0][i]!=0){
                tabla[1][i - Plazo] = tabla[0][i];
            }
            
        }
        
        guardarOrdenes(tabla, oItem.getId_item(), oItem.getTamaño_lote(), oItem.getPlazo());
        GuardarTabla(tabla, oItem.getId_item());
    }
    
    private Item getPadre(Item oItem){
        for (int i = 0; i < listaItems.size(); i++) {
            if(oItem.getoItem().getId_item() == listaItems.get(i).getId_item()){
                return listaItems.get(i);
            }
        }
        return null;
    }
    
    private List<Item> getComponentesNivel(int nivel){
        List<Item> l = new ArrayList<>();
        for (int i = 0; i < listaItems.size(); i++) {
            if(listaItems.get(i).getCodigo_nivel() == nivel){
                l.add(listaItems.get(i));
            }
        }
        return l;
    }
    
    private List<Datos> getDatosPadre(Item oItem){
        List<Datos> l = new ArrayList<>();
        for (int i = 0; i < listaDatos.size(); i++) {
            if(oItem.getoItem().getId_item() == listaDatos.get(i).getId_componente()){
                l.add(listaDatos.get(i));
            }
        }
        return l;
    }
    
    private void guardarOrdenes(int[][] t,int id_componente, String lot, int plazo){
        for (int i = 0; i < t[0].length; i++) {
            if(t[1][i]!=0){
                if(lot.equals("LAL")){
                    listaDatos.add(new Datos(id_componente, i, t[1][i]));
                }else{
                    listaDatos.add(new Datos(id_componente, (i-plazo), t[0][i]));
                }
            }
        }
    }
    
    private void GuardarTabla(int tabla[][], int id_componente){
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
