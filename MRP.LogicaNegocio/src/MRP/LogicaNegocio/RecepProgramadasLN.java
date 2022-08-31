/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MRP.LogicaNegocio;


import MRP.AccesoDatos.RecepProgramadasAD;
import MRP.Entidades.RecepProgramadas;
import java.util.List;

/**
 *
 * @author Alex T. Rivera
 */
public class RecepProgramadasLN {
    RecepProgramadasAD oRecepProgramadasAD;

    public RecepProgramadasLN() {
        oRecepProgramadasAD = new RecepProgramadasAD();
    }
    
    
    public void Insertar(RecepProgramadas oRecepProgramadas) throws Exception{
        try {
            oRecepProgramadasAD.Insertar(oRecepProgramadas);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void Modificar(RecepProgramadas oRecepProgramadas) throws Exception{
        try {
            oRecepProgramadasAD.Modificar(oRecepProgramadas);
        } catch (Exception e) {
            throw e;
        }
    }
        
    public void Eliminar(RecepProgramadas oRecepProgramadas) throws Exception{
        try {
            oRecepProgramadasAD.Eliminar(oRecepProgramadas);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public List<RecepProgramadas> Consultar() throws Exception{
        try {
            return oRecepProgramadasAD.consultar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public RecepProgramadas Consultar1(RecepProgramadas oRecepProgramadas) throws Exception{
        try {
            return oRecepProgramadasAD.consultar1(oRecepProgramadas);
        } catch (Exception e) {
            throw e;
        }
    }
    
}
