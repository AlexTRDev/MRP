/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MRP.AccesoDatos;

import MRP.Entidades.PlanMaestro;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alex T. Rivera
 */
public class PlanMaestroAD {
    AccesoDatos AccesoDatos = new AccesoDatos();
    
    public void Insertar(PlanMaestro oPlanMaestro) throws Exception {
        try {
            String call = "";
            call += "INSERT INTO plan_maestro(nombre_plan, \"totalSem\", Cantidad)\n";
            call += "VALUES (?, ?, ?);";
            
            Object[] parametros = new Object[3];
            parametros[0]=oPlanMaestro.getNombre_plan();
            parametros[1]=oPlanMaestro.getTotalSem();
            parametros[2]=oPlanMaestro.getCantidad();
            
            AccesoDatos.EjecutaProc(call, parametros);
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public void Modificar(PlanMaestro oPlanMaestro) throws Exception {
        try {

            String call ="";
            call += "UPDATE plan_maestro SET nombre_plan=?, \"totalSem\"=?, cantidad=?\n";
            call += "WHERE id_plan=?;";
            
            System.out.println(call);
            Object[] parametros = new Object[4];
            parametros[0]=oPlanMaestro.getNombre_plan();
            parametros[1]=oPlanMaestro.getTotalSem();
            parametros[2]=oPlanMaestro.getCantidad();
            parametros[3]=oPlanMaestro.getId_plan();
            
            
            AccesoDatos.EjecutaProc(call, parametros);
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public void Eliminar(PlanMaestro oPlanMaestro) throws Exception {
        try {
            String call =   "DELETE FROM plan_maestro WHERE id_plan = ?;";
            Object[] parametros = new Object[1];
            parametros[0]=oPlanMaestro.getId_plan();
            AccesoDatos.EjecutaProc(call, parametros);
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public List<PlanMaestro> consultar() throws Exception {
        try {
            String call =   "SELECT * FROM plan_maestro order by id_plan;";
            ResultSet rs = AccesoDatos.RecuperaProc(call);
            
            List<PlanMaestro> lista = new ArrayList<>();
            PlanMaestro objeto;
            while(rs.next()){
                objeto = new PlanMaestro();
                objeto.setId_plan(rs.getInt(1));
                objeto.setNombre_plan(rs.getString(2));
                objeto.setTotalSem(rs.getDouble(3));
                objeto.setCantidad(rs.getString(4));
                lista.add(objeto);
            }
            return lista;
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public PlanMaestro consultar1() throws Exception {
        try {
            String call =   "SELECT * FROM plan_maestro where id_plan = 1;";
            ResultSet rs = AccesoDatos.RecuperaProc(call);

            PlanMaestro objeto = new PlanMaestro();
            
            if(rs.next()){
                objeto.setId_plan(rs.getInt(1));
                objeto.setNombre_plan(rs.getString(2));
                objeto.setTotalSem(rs.getDouble(3));
                objeto.setCantidad(rs.getString(4));
            }
            
            return objeto;
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public PlanMaestro consultar1(PlanMaestro oPlanMaestro) throws Exception {
        try {
            String call =   "SELECT * FROM plan_maestro where id_plan = "+ oPlanMaestro.getId_plan()+";";
            ResultSet rs = AccesoDatos.RecuperaProc(call);

            PlanMaestro objeto = new PlanMaestro();
            
            if(rs.next()){
                objeto.setId_plan(rs.getInt(1));
                objeto.setNombre_plan(rs.getString(2));
                objeto.setTotalSem(rs.getDouble(3));
                objeto.setCantidad(rs.getString(4));
            }
            
            return objeto;
        }
        catch(Exception e) {
            throw e;
        }
    }
    
}
