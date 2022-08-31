/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MRP.AccesoDatos;

import MRP.Entidades.PlanMaestro;
import MRP.Entidades.Semana;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alex T. Rivera
 */
public class SemanaAD {
    AccesoDatos AccesoDatos = new AccesoDatos();
    
    public void Insertar(Semana oSemana) throws Exception {
        try {
           
            String call = "INSERT INTO semana(cantsemana, id_plan, numsemana) VALUES (?, ?, ?);";
            
            Object[] parametros = new Object[3];
            parametros[0]=oSemana.getCantSemana();
            parametros[1]=oSemana.getoPlanMaestro().getId_plan();
            parametros[2]=oSemana.getNumSemana();
            
            AccesoDatos.EjecutaProc(call, parametros);
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public void Modificar(Semana oSemana) throws Exception {
        try {
            String call = "";
            call += "UPDATE semana\n";
            call += "SET cantsemana=?, id_plan=?\n, numsemana=?";
            call += "WHERE id_semana = ?;";
            
            Object[] parametros = new Object[4];
            parametros[0]=oSemana.getCantSemana();
            parametros[1]=oSemana.getoPlanMaestro().getId_plan();
            parametros[2]=oSemana.getNumSemana();
            parametros[3]=oSemana.getId_semana();
            
            AccesoDatos.EjecutaProc(call, parametros);
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public void Eliminar(Semana oSemana) throws Exception {
        try {
            String call =   "DELETE FROM semana WHERE id_semana = ?;";
            Object[] parametros = new Object[1];
            parametros[0]=oSemana.getId_semana();
            AccesoDatos.EjecutaProc(call, parametros);
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public List<Semana> consultar() throws Exception {
        try {
            String call =   "SELECT * FROM semana;";
            ResultSet rs = AccesoDatos.RecuperaProc(call);
            
            List<Semana> lista = new ArrayList<>();
            Semana objeto;
            
            while(rs.next()){
                objeto = new Semana();
                objeto.setId_semana(rs.getInt(1));
                objeto.setCantSemana(rs.getString(2));
                objeto.setoPlanMaestro(new PlanMaestro(rs.getInt(3)));
                objeto.setNumSemana(rs.getString(4));
                lista.add(objeto);
            }
            return lista;
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public List<Semana> consultarXPlan(PlanMaestro oPlanMaestro) throws Exception {
        try {
            String call =   "SELECT * FROM semana where id_plan="+oPlanMaestro.getId_plan()+" order by numsemana;";
            ResultSet rs = AccesoDatos.RecuperaProc(call);
            
            List<Semana> lista = new ArrayList<>();
            Semana objeto;
            
            while(rs.next()){
                objeto = new Semana();
                objeto.setId_semana(rs.getInt(1));
                objeto.setCantSemana(rs.getString(2));
                objeto.setoPlanMaestro(new PlanMaestro(rs.getInt(3)));
                objeto.setNumSemana(rs.getString(4));
                lista.add(objeto);
            }
            return lista;
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public Semana consultar1(Semana oSemana) throws Exception {
        try {
            String call =   "SELECT * FROM semana where id_semana = 1"+oSemana.getId_semana()+";";
            ResultSet rs = AccesoDatos.RecuperaProc(call);

            Semana objeto = new Semana();
            
            if(rs.next()){
                objeto.setId_semana(rs.getInt(1));
                objeto.setCantSemana(rs.getString(2));
                objeto.setoPlanMaestro(new PlanMaestro(rs.getInt(3)));
                objeto.setNumSemana(rs.getString(4));
            }
            
            return objeto;
        }
        catch(Exception e) {
            throw e;
        }
    }
}
