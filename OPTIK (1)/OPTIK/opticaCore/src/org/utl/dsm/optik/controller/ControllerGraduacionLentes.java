
package org.utl.dsm.optik.controller;

/**
 *
 * @author lalo
 */
import com.mysql.cj.MysqlType;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.io.IOException;
import java.sql.SQLException;
import org.utl.dsm.optik.db.ConexionMySQL;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.utl.dsm.optik.model.Graduacion;

public class ControllerGraduacionLentes {
    public int insertar(Graduacion graduacion) throws SQLException, IOException{
        //Paso1: Preparar la setencia sql
        String query = "{call insertarGraduacion(?,?,?,?,?,?,?,?)}";
        int idGraduacion=0;
        
        //Paso2: Conectarse a la BD
        ConexionMySQL conexion= new ConexionMySQL();
        Connection conn = (Connection) conexion.open();
        
        //Paso3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        //Paso4: Asignar los valores para cada parametro
        cstmt.setDouble(1, graduacion.getEsferaod());
        cstmt.setDouble(2, graduacion.getEsferaoi());
        cstmt.setInt(3, graduacion.getCilindrood());
        cstmt.setInt(4, graduacion.getCilindrooi());
        cstmt.setInt(5, graduacion.getEjeoi());
        cstmt.setInt(6, graduacion.getEjeod());
        cstmt.setString(7, graduacion.getDip());
        
        //PASO5: Registrar parametros de salida del procedure
        cstmt.registerOutParameter(8, Types.INTEGER);
        
        //Paso6: Ejecutar la llamada al procedure
        cstmt.executeUpdate();
        
        idGraduacion=cstmt.getInt(8);
       
        
        //Paso8: insertar los valores en el objeto
        graduacion.setIdGraduacion(idGraduacion);
        
        //Paso9: Cerrar los objetos de conexion
        cstmt.close();
        conn.close();
        conexion.close();
        //Paso10: Devolver el id generado
        return idGraduacion;
        
    }
    
    public void actualizar(Graduacion graduacion) throws SQLException{

        //Paso 1: Preparar la sentencia sql
        String query = "call actualizarGraduacion(?,?,?,?,?,?,?,?)";
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        //Paso 4: Asignar los valores para cada parametro
        cstmt.setDouble(1, graduacion.getEsferaod());
        cstmt.setDouble(2, graduacion.getEsferaoi());
        cstmt.setInt(3, graduacion.getCilindrood());
        cstmt.setInt(4, graduacion.getCilindrooi());
        cstmt.setInt(5, graduacion.getEjeoi());
        cstmt.setInt(6, graduacion.getEjeod());
        cstmt.setString(7, graduacion.getDip());
        cstmt.setInt(8, graduacion.getIdGraduacion());

        //Paso 5: Registrar parametros de salida del procedure
        //Paso 6: Ejecutar la llamada al procedure
        cstmt.executeUpdate();
        // Paso 7 recuperar los parametros de salida
        // Paso 8 insertar los valores en el objeto
        // Paso 9 cerrar objetos de conexion
        cstmt.close();
        conn.close();
        conexion.close();
        // Paso 10 devolver o return el id generado
    }
    
    public List<Graduacion> getAll(String filtro) throws SQLException{
        String query = "SELECT * FROM vistaG WHERE estatus = "+filtro+";";
        ConexionMySQL objConexion = new ConexionMySQL();
        Connection conn = objConexion.open();
        PreparedStatement pstmt = conn.prepareStatement(query);
        List<Graduacion> graduaciones = new ArrayList<>();
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            Graduacion g = new Graduacion();
            
            
            g.setIdGraduacion(rs.getInt("idGraduacion"));
            g.setCilindrood(rs.getInt("cilindrood"));
            g.setCilindrooi(rs.getInt("cilindrooi"));
            g.setEsferaod(rs.getDouble("esferaod"));
            g.setEsferaoi(rs.getDouble("esferaoi"));
            g.setEjeoi(rs.getInt("ejeoi"));
            g.setEjeod(rs.getInt("ejeod"));
            g.setDip(rs.getString("dip"));
            g.setEstatus(rs.getInt("estatus"));
            
            graduaciones.add(g);
        }
        rs.close();
        pstmt.close();
        conn.close();
        
        return graduaciones;
    }
    
    public void eliminar(String idGraduacion) throws SQLException{

        //Paso 1: Preparar la sentencia sql
        String query = "UPDATE graduacion SET estatus = 0 WHERE idGraduacion = ?";
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        PreparedStatement cstmt = conn.prepareCall(query);
        cstmt.setString(1, idGraduacion);
        cstmt.executeUpdate();
        // Paso 7 recuperar los parametros de salida
        // Paso 8 insertar los valores en el objeto
        // Paso 9 cerrar objetos de conexion
        cstmt.close();
        conn.close();
        conexion.close();
        // Paso 10 devolver o return el id generado
    }
    public void agregar(String idGraduacion) throws SQLException{

        //Paso 1: Preparar la sentencia sql
        String query = "UPDATE graduacion SET estatus = 1 WHERE idGraduacion = ?";
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        PreparedStatement cstmt = conn.prepareCall(query);
        cstmt.setString(1, idGraduacion);
        cstmt.executeUpdate();
        // Paso 7 recuperar los parametros de salida
        // Paso 8 insertar los valores en el objeto
        // Paso 9 cerrar objetos de conexion
        cstmt.close();
        conn.close();
        conexion.close();
        // Paso 10 devolver o return el id generado
    }
}
