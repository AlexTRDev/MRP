/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MRP.AccesoDatos;

import MRP.Entidades.RecepProgramadas;
import MRP.Entidades.Semana;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alex T. Rivera
 */
public class RecepProgramadasAD {
    AccesoDatos AccesoDatos = new AccesoDatos();
    
    public void Insertar(RecepProgramadas oRecepProgramadas) throws Exception {
        try {
           
            String call = "";
            call += "INSERT INTO recep_programadas(\n";
            call += "id_semana, cantidad)\n";
            call += "VALUES (?, ?);";
            
            Object[] parametros = new Object[2];
            parametros[0]=oRecepProgramadas.getoSemana().getId_semana();
            parametros[1]=oRecepProgramadas.getCantSemana();
            
            AccesoDatos.EjecutaProc(call, parametros);
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public void Modificar(RecepProgramadas oRecepProgramadas) throws Exception {
        try {
            String call = "UPDATE recep_programadas SET id_semana=?, cantidad=?  WHERE id_recep_programadas=?;";
            
            Object[] parametros = new Object[3];
            parametros[0]=oRecepProgramadas.getoSemana().getId_semana();
            parametros[1]=oRecepProgramadas.getCantSemana();
            parametros[2]=oRecepProgramadas.getId_recep_programadas();
            
            AccesoDatos.EjecutaProc(call, parametros);
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public void Eliminar(RecepProgramadas oRecepProgramadas) throws Exception {
        try {
            String call =   "DELETE FROM recep_programadas WHERE id_recep_programadas=?;";
            Object[] parametros = new Object[1];
            parametros[0]=oRecepProgramadas.getId_recep_programadas();
            AccesoDatos.EjecutaProc(call, parametros);
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public List<RecepProgramadas> consultar() throws Exception {
        try {
            String call =   "SELECT * FROM recep_programadas;";
            ResultSet rs = AccesoDatos.RecuperaProc(call);
            
            List<RecepProgramadas> lista = new ArrayList<>();
            RecepProgramadas objeto;
            
            while(rs.next()){
                objeto = new RecepProgramadas();
                objeto.setId_recep_programadas(rs.getInt(1));
                objeto.setoSemana(new Semana(rs.getInt(2)));
                objeto.setCantSemana(rs.getString(3));
                lista.add(objeto);
            }
            return lista;
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public RecepProgramadas consultar1(RecepProgramadas oRecepProgramadas) throws Exception {
        try {
            String call =   "SELECT * FROM recepprogramadas where id_recep_programadas="+oRecepProgramadas.getId_recep_programadas()+";";
            ResultSet rs = AccesoDatos.RecuperaProc(call);

            RecepProgramadas objeto = new RecepProgramadas();
            
            if(rs.next()){
                objeto.setId_recep_programadas(rs.getInt(1));
                objeto.setoSemana(new Semana(rs.getInt(2)));
                objeto.setCantSemana(rs.getString(3));            }
            return objeto;
        }
        catch(Exception e) {
            throw e;
        }
    }
}
