/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MRP.Entidades;

/**
 *
 * @author Alex T. Rivera
 */
public class TablaComponente {
    int idItem;
    String[][] tabla;

    public TablaComponente() {
    }

    public TablaComponente(int id_componente, String[][] tabla) {
        this.idItem = id_componente;
        this.tabla = tabla;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String[][] getTabla() {
        return tabla;
    }

    public void setTabla(String[][] tabla) {
        this.tabla = tabla;
    }

    @Override
    public String toString() {
        return "El ID del componente en la base de datos es: "+idItem;
    }
    
    
}
