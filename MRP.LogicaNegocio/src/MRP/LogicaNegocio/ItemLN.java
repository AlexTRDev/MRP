/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MRP.LogicaNegocio;


import MRP.AccesoDatos.ItemAD;
import MRP.Entidades.Item;
import MRP.Entidades.PlanMaestro;
import java.util.List;

/**
 *
 * @author Alex T. Rivera
 */
public class ItemLN {
    ItemAD oItemAD;

    public ItemLN() {
        oItemAD = new ItemAD();
    }
    
    
    public void Insertar(Item oItem) throws Exception{
        try {
            oItemAD.Insertar(oItem);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void Modificar(Item oItem) throws Exception{
        try {
            oItemAD.Modificar(oItem);
        } catch (Exception e) {
            throw e;
        }
    }
        
    public void Eliminar(Item oItem) throws Exception{
        try {
            oItemAD.Eliminar(oItem);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public List<Item> Consultar(PlanMaestro oPlanMaestro) throws Exception{
        try {
            return oItemAD.consultar(oPlanMaestro);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public List<Item> ConsultarPorCodigo(String codigo, PlanMaestro oPlanMaestro) throws Exception{
        try {
            return oItemAD.consultarPorCodigo(codigo, oPlanMaestro);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public List<Item> ConsultarPorNombre(String codigo, PlanMaestro oPlanMaestro) throws Exception{
        try {
            return oItemAD.consultarPorNombre(codigo, oPlanMaestro);
        } catch (Exception e) {
            throw e;
        }
    }
    public List<Item> ConsultarPorPlazo(String plazo, PlanMaestro oPlanMaestro) throws Exception{
        try {
            return oItemAD.consultarPorPlazo(plazo, oPlanMaestro);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public List<Item> ConsultarPorNivel(int codigo_nivel, PlanMaestro oPlanMaestro) throws Exception{
        try {
            return oItemAD.consultarPorNivel(codigo_nivel, oPlanMaestro);
        } catch (Exception e) {
            throw e;
        }
    }
    public Item ConsultarPadre(Item oItem) throws Exception{
        try {
            return oItemAD.consultarPadre(oItem);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public int nivelMax(PlanMaestro oPlanMaestro) throws Exception{
        try {
            return oItemAD.nivelMax(oPlanMaestro);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Item ConsultarPrincipal(PlanMaestro oPlanMaestro) throws Exception{
        try {
            return oItemAD.consultarPrincipal(oPlanMaestro);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void InsertarPrincipal(Item oItem) throws Exception{
        try {
            oItemAD.InsertarPrincipal(oItem);
        } catch (Exception e) {
            throw e;
        }
    }
}
