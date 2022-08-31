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
public class RecepProgramadas {
    
    private int id_recep_programadas;
    private Semana oSemana;
    private String cantidad;
    

    public RecepProgramadas() {
    }

    public RecepProgramadas(int id_recep_programadas) {
        this.id_recep_programadas = id_recep_programadas;
    }

    public RecepProgramadas(int id_recep_programadas, Semana oSemana, String cantidad) {
        this.id_recep_programadas = id_recep_programadas;
        this.oSemana = oSemana;
        this.cantidad = cantidad;
    }

    public int getId_recep_programadas() {
        return id_recep_programadas;
    }

    public void setId_recep_programadas(int id_recep_programadas) {
        this.id_recep_programadas = id_recep_programadas;
    }

    public String getCantSemana() {
        return cantidad;
    }

    public void setCantSemana(String cantSemana) {
        this.cantidad = cantSemana;
    }

    public Semana getoSemana() {
        return oSemana;
    }

    public void setoSemana(Semana oSemana) {
        this.oSemana = oSemana;
    }

    @Override
    public String toString() {
        return ""+this.oSemana.getId_semana() + " = " + this.cantidad;
    }

}
