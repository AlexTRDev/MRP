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
public class Semana {
    
    private int id_semana;
    private String cantSemana;
    private PlanMaestro oPlanMaestro;
    private String numSemana;

    public Semana() {
    }

    public Semana(int id_semana) {
        this.id_semana = id_semana;
    }

    public Semana(int id_semana, String cantSemana, PlanMaestro oPlanMaestro,String numSemana) {
        this.id_semana = id_semana;
        this.cantSemana = cantSemana;
        this.oPlanMaestro = oPlanMaestro;
        this.numSemana = numSemana;
    }

    public int getId_semana() {
        return id_semana;
    }

    public void setId_semana(int id_semana) {
        this.id_semana = id_semana;
    }

    public String getCantSemana() {
        return cantSemana;
    }

    public void setCantSemana(String cantSemana) {
        this.cantSemana = cantSemana;
    }

    public PlanMaestro getoPlanMaestro() {
        return oPlanMaestro;
    }

    public void setoPlanMaestro(PlanMaestro oPlanMaestro) {
        this.oPlanMaestro = oPlanMaestro;
    }

    public String getNumSemana() {
        return numSemana;
    }

    public void setNumSemana(String numSemana) {
        this.numSemana = numSemana;
    }
    
    @Override
    public String toString() {
        return ""+this.id_semana;
    }
    
}
