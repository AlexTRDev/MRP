
package MRP.AccesoDatos;

import java.math.BigDecimal;
import java.sql.*;

public class AccesoDatos{

   
    public Connection Conexion() throws Exception {
        Connection cnx;

        try {
            strHost= "localhost";
            strPort= "5432";
            strDatabase= "MRP";
            strUser= "postgres";
            strPassword= "postgres";
//            
            strCadenaConexion = "jdbc:postgresql://" + strHost + ":" + strPort + "/" + strDatabase;
            Class.forName("org.postgresql.Driver");
            cnx = DriverManager.getConnection(strCadenaConexion,strUser,strPassword);
            return cnx;
        }
        catch(Exception ex) {
            throw new Exception("ERROR ===> " + ex.getMessage());
        }
    }
    
    public static boolean provarConexion(){
        Connection c ;
        try {
            strCadenaConexion = "jdbc:postgresql://" + strHost + ":" + strPort + "/" + strDatabase;
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(strCadenaConexion,strUser,strPassword);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
  
    //ejecuta procedimiento almacenado con CallableStatement
    public boolean EjecutaProc(String call, Object param[]) throws Exception{
        CallableStatement ca;
        try(Connection Conexion = Conexion()){
            ca = Conexion.prepareCall(call);
            for(int x=0;x<param.length;x++)
            {
                if(param[x].getClass().toString().equals("class java.lang.String")){
                   ca.setString(x+1, param[x].toString());
                }else if(param[x].getClass().toString().equals("class java.lang.Integer")){
                   ca.setInt(x+1, Integer.parseInt(param[x].toString()));
                }else if(param[x].getClass().toString().equals("class java.lang.Double")){
                   ca.setDouble(x+1, Double.parseDouble(param[x].toString()));
                }else if(param[x].getClass().toString().equals("class java.lang.Float")){
                   ca.setFloat(x+1, Float.parseFloat(param[x].toString()));
                }else if(param[x].getClass().toString().equals("class java.math.BigDecimal")){
                   ca.setBigDecimal(x+1, new BigDecimal(param[x].toString()));
                }else if(param[x].getClass().toString().equals("class java.lang.Boolean")){
                   ca.setBoolean(x+1, Boolean.parseBoolean(param[x].toString()));
                }
            }
            ca.executeUpdate();
            Conexion.close();
            return true;
        }catch(Exception e){
            throw e;
        }
    }

    //devuelve cursor al ejecutar procedimiento almacenado con CallableStatement
    public ResultSet RecuperaProc(String call) throws Exception{
        CallableStatement ca;
        ResultSet rs;
        try(Connection Conexion = Conexion()) {
            ca = Conexion().prepareCall(call, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = ca.executeQuery();
            Conexion.close();
            return rs;
        }catch(Exception e){
            throw e;
        }
    }

    //devuelve cursor al ejecutar procedimiento almacenado con CallableStatement con filtros
    public ResultSet RecuperaProc(String call, Object param[]) throws SQLException{
        CallableStatement ca;
        ResultSet rs;
        try(Connection Conexion = Conexion()){
            ca = Conexion().prepareCall(call, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            for(int x=0;x<param.length;x++)
            {
               if(param[x].getClass().toString().equals("class java.lang.String"))
                   ca.setString(x+1, param[x].toString());
               if(param[x].getClass().toString().equals("class java.lang.Integer"))
                   ca.setInt(x+1, Integer.parseInt(param[x].toString()));
               if(param[x].getClass().toString().equals("class java.lang.Double"))
                   ca.setDouble(x+1, Double.parseDouble(param[x].toString()));
               if(param[x].getClass().toString().equals("class java.lang.Float"))
                   ca.setFloat(x+1, Float.parseFloat(param[x].toString()));
               if(param[x].getClass().toString().equals("class java.lang.Boolean"))
                   ca.setBoolean(x+1, Boolean.parseBoolean(param[x].toString()));
            }
            rs = ca.executeQuery();
            Conexion.close();
            return rs;
        }catch(Exception ex){
            return null;
        }
    }
    
    public static String strHost;
    public static String strPort;
    public static String strDatabase;
    public static String strUser;
    public static String strPassword;
    public static String strCadenaConexion;
}
