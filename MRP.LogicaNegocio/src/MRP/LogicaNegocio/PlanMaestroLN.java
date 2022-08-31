/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MRP.LogicaNegocio;

import MRP.AccesoDatos.PlanMaestroAD;
import MRP.Entidades.PlanMaestro;
import java.util.List;

/**
 *
 * @author Alex T. Rivera
 */
public class PlanMaestroLN {
    PlanMaestroAD oPlanMaestroAD;

    public PlanMaestroLN() {
        oPlanMaestroAD = new PlanMaestroAD();
    }
    
    
    public void Insertar(PlanMaestro oPlanMaestro) throws Exception{
        try {
            oPlanMaestroAD.Insertar(oPlanMaestro);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void Modificar(PlanMaestro oPlanMaestro) throws Exception{
        try {
            oPlanMaestroAD.Modificar(oPlanMaestro);
            
        } catch (Exception e) {
            throw e;
        }
    }
        
    public void Eliminar(PlanMaestro oPlanMaestro) throws Exception{
        try {
            oPlanMaestroAD.Eliminar(oPlanMaestro);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public List<PlanMaestro> Consultar() throws Exception{
        try {
            return oPlanMaestroAD.consultar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public PlanMaestro Consultar1() throws Exception{
        try {
            return oPlanMaestroAD.consultar1();
        } catch (Exception e) {
            throw e;
        }
    }

}
