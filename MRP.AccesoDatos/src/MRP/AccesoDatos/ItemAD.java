
package MRP.AccesoDatos;

import MRP.Entidades.Item;
import MRP.Entidades.PlanMaestro;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alex T. Rivera
 */
public class ItemAD {
    AccesoDatos oAccesoDatos = new AccesoDatos();
    
    public void Insertar(Item oItem) throws Exception {
        try {
            String call =   "";
            call += "INSERT INTO\n";
            call += "item(plazo, disponible, stock,reservado, codigo_nivel, indentificador, cantidad, costo_pedido, costo_almacen, nombre, \"tamaño_lote\", id_padre, id_plan, imagen, recep_programadas, semana_rec_programadas)\n";
            call += "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?,?);";
                    
            Object[] parametros = new Object[16];
            parametros[0]=oItem.getPlazo();
            parametros[1]=oItem.getDisponible();
            parametros[2]=oItem.getStock();
            parametros[3]=oItem.getReservado();
            parametros[4]=oItem.getCodigo_nivel();
            parametros[5]=oItem.getIdentificador();
            parametros[6]=oItem.getCantidad();
            parametros[7]=oItem.getPrecio_pedido();
            parametros[8]=oItem.getPrecio_almacen();
            parametros[9]=oItem.getNombre();
            parametros[10]=oItem.getTamaño_lote();
            parametros[11]=oItem.getoItem();
            parametros[12]=oItem.getId_plan();
            parametros[13]=oItem.getImagen();
            parametros[14]=oItem.getRecepProgramadas();
            parametros[15]=oItem.getSemana_RPP();

            oAccesoDatos.EjecutaProc(call, parametros);
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public void Modificar(Item oItem) throws Exception {
        try {
            String call ="";
            call +="plazo=?, disponible=?, stock=?, reservado=?, codigo_nivel=?, indentificador=?, cantidad=?, costo_pedido=?, costo_almacen=?, \n";
            call +="nombre=?, \"tamaño_lote\"=?, id_padre=?, id_plan=?, imagen=?, recep_programadas=?, semana_rec_programadas=? \n";
            call +="WHERE id_item = ?";
            
            Object[] parametros = new Object[17];
            parametros[0]=oItem.getPlazo();
            parametros[1]=oItem.getDisponible();
            parametros[2]=oItem.getStock();
            parametros[3]=oItem.getReservado();
            parametros[4]=oItem.getCodigo_nivel();
            parametros[5]=oItem.getIdentificador();
            parametros[6]=oItem.getCantidad();
            parametros[7]=oItem.getPrecio_pedido();
            parametros[8]=oItem.getPrecio_almacen();
            parametros[9]=oItem.getNombre();
            parametros[10]=oItem.getTamaño_lote();
            parametros[11]=oItem.getoItem();
            parametros[12]=oItem.getId_plan();
            parametros[13]=oItem.getImagen();
            parametros[14]=oItem.getRecepProgramadas();
            parametros[15]=oItem.getSemana_RPP();
            parametros[16]=oItem.getId_item();
            
            oAccesoDatos.EjecutaProc(call, parametros);
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public void Eliminar(Item oItem) throws Exception {
        try {
            String call =   "DELETE FROM item WHERE id_item = ?;";
            Object[] parametros = new Object[1];
            parametros[0]=oItem.getId_item();
            oAccesoDatos.EjecutaProc(call, parametros);
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public List<Item> consultar(PlanMaestro oPlanMaestro) throws Exception {
        try {
            String call =   "SELECT * FROM item where id_plan = "+oPlanMaestro.getId_plan()+" order by id_item , codigo_nivel, id_plan;";
    
            System.out.println(call);
            ResultSet rs = oAccesoDatos.RecuperaProc(call);
            
            List<Item> lista = new ArrayList<>();
            Item objeto;
            while(rs.next()){
                objeto = new Item();
                objeto.setId_item(rs.getInt(1));
                objeto.setPlazo(rs.getInt(2));
                objeto.setDisponible(rs.getInt(3));
                objeto.setStock(rs.getInt(4));
                objeto.setReservado(rs.getInt(5));
                objeto.setCodigo_nivel(rs.getInt(6));
                objeto.setIdentificador(rs.getString(7));
                objeto.setCantidad(rs.getInt(8));
                objeto.setPrecio_pedido(rs.getDouble(9));
                objeto.setPrecio_almacen(rs.getDouble(10));
                objeto.setNombre(rs.getString(11));
                objeto.setTamaño_lote(rs.getString(12));
                objeto.setoItem(new Item(rs.getInt(13)));
                objeto.setId_plan(rs.getInt(14));
                objeto.setImagen(rs.getString(15));
                objeto.setRecepProgramadas(rs.getString(16));
                objeto.setSemana_RPP(rs.getString(17));

                lista.add(objeto);
            }
            return lista;
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public List<Item> consultarPorCodigo(String codigo, PlanMaestro oPlanMaestro) throws Exception {
        try {
            String call =   "SELECT * FROM item where id_plan = "+oPlanMaestro.getId_plan()+" and indentificador like '%"+codigo+"%';";
            ResultSet rs = oAccesoDatos.RecuperaProc(call);
            
            List<Item> lista = new ArrayList<>();
            Item objeto;
            while(rs.next()){
                objeto = new Item();
                objeto.setId_item(rs.getInt(1));
                objeto.setPlazo(rs.getInt(2));
                objeto.setDisponible(rs.getInt(3));
                objeto.setStock(rs.getInt(4));
                objeto.setReservado(rs.getInt(5));
                objeto.setCodigo_nivel(rs.getInt(6));
                objeto.setIdentificador(rs.getString(7));
                objeto.setCantidad(rs.getInt(8));
                objeto.setPrecio_pedido(rs.getDouble(9));
                objeto.setPrecio_almacen(rs.getDouble(10));
                objeto.setNombre(rs.getString(11));
                objeto.setTamaño_lote(rs.getString(12));
                objeto.setoItem(new Item(rs.getInt(13)));
                objeto.setId_plan(rs.getInt(14));
                objeto.setImagen(rs.getString(15));
                objeto.setRecepProgramadas(rs.getString(16));
                objeto.setSemana_RPP(rs.getString(17));

                lista.add(objeto);
            }
            return lista;
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public List<Item> consultarPorNombre(String codigo,PlanMaestro oPlanMaestro) throws Exception {
        try {
            String call =   "SELECT * FROM item where id_plan = "+oPlanMaestro.getId_plan()+" and nombre like '"+codigo+"%';";
            ResultSet rs = oAccesoDatos.RecuperaProc(call);
            
            List<Item> lista = new ArrayList<>();
            Item objeto;
            while(rs.next()){
                objeto = new Item();
                objeto.setId_item(rs.getInt(1));
                objeto.setPlazo(rs.getInt(2));
                objeto.setDisponible(rs.getInt(3));
                objeto.setStock(rs.getInt(4));
                objeto.setReservado(rs.getInt(5));
                objeto.setCodigo_nivel(rs.getInt(6));
                objeto.setIdentificador(rs.getString(7));
                objeto.setCantidad(rs.getInt(8));
                objeto.setPrecio_pedido(rs.getDouble(9));
                objeto.setPrecio_almacen(rs.getDouble(10));
                objeto.setNombre(rs.getString(11));
                objeto.setTamaño_lote(rs.getString(12));
                objeto.setoItem(new Item(rs.getInt(13)));
                objeto.setId_plan(rs.getInt(14));
                objeto.setImagen(rs.getString(15));
                objeto.setRecepProgramadas(rs.getString(16));
                objeto.setSemana_RPP(rs.getString(17));

                lista.add(objeto);
            }
            return lista;
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public List<Item> consultarPorPlazo(String plazo, PlanMaestro oPlanMaestro) throws Exception {
        try {
            String call =   "SELECT * FROM item where id_plan = "+oPlanMaestro.getId_plan()+" and plazo = "+plazo;
            ResultSet rs = oAccesoDatos.RecuperaProc(call);
            
            List<Item> lista = new ArrayList<>();
            Item objeto;
            while(rs.next()){
                objeto = new Item();
                objeto.setId_item(rs.getInt(1));
                objeto.setPlazo(rs.getInt(2));
                objeto.setDisponible(rs.getInt(3));
                objeto.setStock(rs.getInt(4));
                objeto.setReservado(rs.getInt(5));
                objeto.setCodigo_nivel(rs.getInt(6));
                objeto.setIdentificador(rs.getString(7));
                objeto.setCantidad(rs.getInt(8));
                objeto.setPrecio_pedido(rs.getDouble(9));
                objeto.setPrecio_almacen(rs.getDouble(10));
                objeto.setNombre(rs.getString(11));
                objeto.setTamaño_lote(rs.getString(12));
                objeto.setoItem(new Item(rs.getInt(13)));
                objeto.setId_plan(rs.getInt(14));
                objeto.setImagen(rs.getString(15));
                objeto.setRecepProgramadas(rs.getString(16));
                objeto.setSemana_RPP(rs.getString(17));

                lista.add(objeto);
            }
            
            return lista;
        }
        catch(Exception e) {
            throw e;
        }
    }
    
        public List<Item> consultarPorNivel(int codigo_nivel, PlanMaestro oPlanMaestro) throws Exception {
        try {
            String call =   "SELECT * FROM item where id_plan = "+oPlanMaestro.getId_plan()+" and codigo_nivel = "+codigo_nivel+";";
            ResultSet rs = oAccesoDatos.RecuperaProc(call);
            
            List<Item> lista = new ArrayList<>();
            Item objeto;
            while(rs.next()){
                objeto = new Item();
                objeto.setId_item(rs.getInt(1));
                objeto.setPlazo(rs.getInt(2));
                objeto.setDisponible(rs.getInt(3));
                objeto.setStock(rs.getInt(4));
                objeto.setReservado(rs.getInt(5));
                objeto.setCodigo_nivel(rs.getInt(6));
                objeto.setIdentificador(rs.getString(7));
                objeto.setCantidad(rs.getInt(8));
                objeto.setPrecio_pedido(rs.getDouble(9));
                objeto.setPrecio_almacen(rs.getDouble(10));
                objeto.setNombre(rs.getString(11));
                objeto.setTamaño_lote(rs.getString(12));
                objeto.setoItem(new Item(rs.getInt(13)));
                objeto.setId_plan(rs.getInt(14));
                objeto.setImagen(rs.getString(15));
                objeto.setRecepProgramadas(rs.getString(16));
                objeto.setSemana_RPP(rs.getString(17));

                lista.add(objeto);
            }
            
            return lista;
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public Item consultarPadre(Item oItem) throws Exception {
        try {

            String call =   "SELECT * FROM item where id_item = "+oItem.getId_item()+";";
//            System.out.println(call);
            ResultSet rs = oAccesoDatos.RecuperaProc(call);
            
            Item objeto = new Item();
            
            if(rs.next()){
                objeto = new Item();
                objeto.setId_item(rs.getInt(1));
                objeto.setPlazo(rs.getInt(2));
                objeto.setDisponible(rs.getInt(3));
                objeto.setStock(rs.getInt(4));
                objeto.setReservado(rs.getInt(5));
                objeto.setCodigo_nivel(rs.getInt(6));
                objeto.setIdentificador(rs.getString(7));
                objeto.setCantidad(rs.getInt(8));
                objeto.setPrecio_pedido(rs.getDouble(9));
                objeto.setPrecio_almacen(rs.getDouble(10));
                objeto.setNombre(rs.getString(11));
                objeto.setTamaño_lote(rs.getString(12));
                objeto.setoItem(new Item(rs.getInt(13)));
                objeto.setId_plan(rs.getInt(14));
                objeto.setImagen(rs.getString(15));
                objeto.setRecepProgramadas(rs.getString(16));
                objeto.setSemana_RPP(rs.getString(17));
            }
            return objeto;
        }
        catch(Exception e) {
            throw e;
        }
    }
        
    public int nivelMax(PlanMaestro oPlanMaestro) throws Exception {
        try {
            String call =   "SELECT max(codigo_nivel) FROM item where id_plan = "+oPlanMaestro.getId_plan();
            ResultSet rs = oAccesoDatos.RecuperaProc(call);
            
            int max_nivel = 0;
            if(rs.next()){
                max_nivel = rs.getInt(1);
            }
            return max_nivel;
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public Item consultarPrincipal(PlanMaestro oPlanMaestro) throws Exception {
        try {
            String call =   "SELECT * FROM componente where id_plan = "+oPlanMaestro.getId_plan()+" and codigo_nivel = 0;";
            ResultSet rs = oAccesoDatos.RecuperaProc(call);
            
            Item objeto = new Item();
            
            if(rs.next()){
                objeto.setId_item(rs.getInt(1));
                objeto.setPlazo(rs.getInt(2));
                objeto.setDisponible(rs.getInt(3));
                objeto.setStock(rs.getInt(4));
                objeto.setReservado(rs.getInt(5));
                objeto.setCodigo_nivel(rs.getInt(6));
                objeto.setIdentificador(rs.getString(7));
                objeto.setCantidad(rs.getInt(8));
                objeto.setPrecio_pedido(rs.getDouble(9));
                objeto.setPrecio_almacen(rs.getDouble(10));
                objeto.setNombre(rs.getString(11));
                objeto.setTamaño_lote(rs.getString(12));
                objeto.setoItem(new Item(rs.getInt(13)));
                objeto.setId_plan(rs.getInt(14));
                objeto.setImagen(rs.getString(15));
            }
            
            return objeto;
        }
        catch(Exception e) {
            throw e;
        }
    }

    public void InsertarPrincipal(Item oItem) throws Exception {
        try {
            String call =   "";
            call += "INSERT INTO ";
            call += "item(plazo, disponible, stock, reservado, codigo_nivel, indentificador, cantidad, costo_pedido, costo_almacen, nombre, \"tamaño_lote\",id_padre, id_plan, imagen ";
            call += "VALUES (?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?);";
            
            Object[] parametros = new Object[14];
            parametros[0]=oItem.getPlazo();
            parametros[1]=oItem.getDisponible();
            parametros[2]=oItem.getStock();
            parametros[3]=oItem.getReservado();
            parametros[4]=oItem.getCodigo_nivel();
            parametros[5]=oItem.getIdentificador();
            parametros[6]=oItem.getCantidad();
            parametros[7]=oItem.getPrecio_pedido();
            parametros[8]=oItem.getPrecio_almacen();
            parametros[9]=oItem.getNombre();
            parametros[10]=oItem.getTamaño_lote();
            parametros[11]=oItem.getoItem();
            parametros[12]=oItem.getId_plan();
            parametros[13]=oItem.getImagen();
            
            oAccesoDatos.EjecutaProc(call, parametros);
        }
        catch(Exception e) {
            throw e;
        }
    }
}
