package org.utl.dsm.optik.controller;
import org.utl.dsm.optik.db.ConexionMySQL;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.utl.dsm.optik.model.Material;

/**
 *
 * @author iamra
 */
public class ControllerMaterial {
    public int insertar(Material material) throws Exception{
        String query = "CALL insertarMaterial(?,?,?,?)";
        int idMaterialG = 0;
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        cstmt.setString(1, material.getNombre());
        cstmt.setDouble(2, material.getPrecioCompra());
        cstmt.setDouble(3, material.getPrecioVenta());
        
        cstmt.registerOutParameter(4, Types.INTEGER);
        
        cstmt.executeUpdate();
        
        idMaterialG = cstmt.getInt(4);
        material.setIdMaterial(idMaterialG);
        cstmt.close();
        conn.close();
        conexion.close();
        
        return idMaterialG;
    }
    
    public void actualizar(Material material) throws Exception{
        String query = "CALL actualizarMaterial(?,?,?,?)";
        
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        CallableStatement cstmt = conn.prepareCall(query);
        
        cstmt.setString(1, material.getNombre());
        cstmt.setDouble(2, material.getPrecioCompra());
        cstmt.setDouble(3, material.getPrecioVenta());
        cstmt.setInt(4, material.getIdMaterial());
        
        cstmt.executeUpdate();
        cstmt.close();
        conn.close();
        conexion.close();
    }
    public void eliminar(Material material) throws Exception{
        String query = "call eliminarMaterial(?)";
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        cstmt.setInt(1, material.getIdMaterial());
        cstmt.executeUpdate();
        cstmt.close();
        conn.close();
        conexion.close();        
    }
    public void activar(Material material) throws Exception{
        String query = "call activarMaterial(?)";
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        cstmt.setInt(1, material.getIdMaterial());
        cstmt.executeUpdate();
        cstmt.close();
        conn.close();
        conexion.close();        
    }    
    
    public List<Material> getAll(String filtro) throws SQLException{
        String query = "SELECT * FROM vistaMateriales WHERE estatus="+filtro+";";
        ConexionMySQL objConexion = new ConexionMySQL();
        Connection conn = objConexion.open();
        PreparedStatement pstmt = conn.prepareStatement(query);
        List<Material> materiales = new ArrayList<>();
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            Material m = new Material();
            m.setIdMaterial(rs.getInt("idMaterial"));
            m.setNombre(rs.getString("nombre"));
            m.setPrecioCompra(rs.getDouble("precioCompra"));
            m.setPrecioVenta(rs.getDouble("precioVenta"));
            m.setEstatus(rs.getInt("estatus"));
            materiales.add(m);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return materiales;
    }

}
