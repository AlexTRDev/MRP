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
public class PlanMaestro {
    private int id_plan;
    private String nombre_plan;
    private Double totalSem;
    private String Cantidad;

    public PlanMaestro() {
    }

    public PlanMaestro(int id_plan) {
        this.id_plan = id_plan;
    }

    public PlanMaestro(int id_plan, String nombre_plan, Double totalSem, String Cantidad) {
        this.id_plan = id_plan;
        this.nombre_plan = nombre_plan;
        this.totalSem = totalSem;
        this.Cantidad = Cantidad;
    }

    public int getId_plan() {
        return id_plan;
    }

    public void setId_plan(int id_plan) {
        this.id_plan = id_plan;
    }

    public String getNombre_plan() {
        return nombre_plan;
    }

    public void setNombre_plan(String nombre_plan) {
        this.nombre_plan = nombre_plan;
    }

    public Double getTotalSem() {
        return totalSem;
    }

    public void setTotalSem(Double totalSem) {
        this.totalSem = totalSem;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String Cantidad) {
        this.Cantidad = Cantidad;
    }
    
    @Override
    public String toString() {
        return nombre_plan;
    }

}
