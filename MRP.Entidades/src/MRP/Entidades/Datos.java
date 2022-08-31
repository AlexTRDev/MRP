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
public class Datos {
    private int id_componente;
    private int semana;
    private int cantidad;

    public Datos() {
    }

    public Datos(int id_componente, int semana, int cantidad) {
        this.id_componente = id_componente;
        this.semana = semana;
        this.cantidad = cantidad;
    }

    public int getId_componente() {
        return id_componente;
    }

    public void setId_componente(int id_componente) {
        this.id_componente = id_componente;
    }

    public int getSemana() {
        return semana;
    }

    public void setSemana(int semana) {
        this.semana = semana;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "El id del componente en la base de datos es: "+this.id_componente;
    }
    
    
    
}
