/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MRP.LogicaNegocio;

import MRP.AccesoDatos.SemanaAD;
import MRP.Entidades.PlanMaestro;
import MRP.Entidades.Semana;
import java.util.List;

/**
 *
 * @author Alex T. Rivera
 */
public class SemanaLN {
    SemanaAD oSemanaAD;

    public SemanaLN() {
        oSemanaAD = new SemanaAD();
    }
    
    
    public void Insertar(Semana oSemana) throws Exception{
        try {
            oSemanaAD.Insertar(oSemana);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void Modificar(Semana oSemana) throws Exception{
        try {
            oSemanaAD.Modificar(oSemana);
        } catch (Exception e) {
            throw e;
        }
    }
        
    public void Eliminar(Semana oSemana) throws Exception{
        try {
            oSemanaAD.Eliminar(oSemana);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public List<Semana> Consultar() throws Exception{
        try {
            return oSemanaAD.consultar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public List<Semana> ConsultarXPlan(PlanMaestro oPlanMaestro) throws Exception{
        try {
            return oSemanaAD.consultarXPlan(oPlanMaestro);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Semana Consultar1(Semana oSemana) throws Exception{
        try {
            return oSemanaAD.consultar1(oSemana);
        } catch (Exception e) {
            throw e;
        }
    }
    
}
