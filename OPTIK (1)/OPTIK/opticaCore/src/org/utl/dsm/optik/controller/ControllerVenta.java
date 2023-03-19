/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.controller;
import org.utl.dsm.optik.db.ConexionMySQL;
import org.utl.dsm.optik.model.Producto;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.utl.dsm.optik.model.DetalleVentaProducto;
import org.utl.dsm.optik.model.Empleado;
import org.utl.dsm.optik.model.Persona;
import org.utl.dsm.optik.model.Usuario;
import org.utl.dsm.optik.model.Venta;
import org.utl.dsm.optik.model.VentaProducto;

/**
 *
 * @author iamra
 */
public class ControllerVenta {
    public List<Producto> getAll(String filtro) throws SQLException{
        String query = "SELECT * from vistaP WHERE nombre = '"+filtro+"' or marca = '"+filtro+"' or codigoBarras = '"+filtro+"';";
        
        ConexionMySQL objConexion = new ConexionMySQL();
        // Abro la conexion
        Connection conn = objConexion.open();
        // Preparo el envio
        PreparedStatement pstmt = conn.prepareStatement(query);
        
        List<Producto> productos = new ArrayList<>();
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            Producto p = new Producto();
            p.setIdProducto(rs.getInt("idProducto"));
            p.setCodigoBarras(rs.getString("codigoBarras"));
            p.setNombre(rs.getString("nombre"));
            p.setMarca(rs.getString("marca"));
            p.setPrecioVenta(rs.getDouble("precioVenta"));
            p.setPrecioCompra(rs.getDouble("precioCompra"));
            p.setExistencias(rs.getInt("existencias"));
            p.setEstatus(rs.getInt("estatus"));
            
            productos.add(p);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return productos;
    }
    
    public boolean generarVentaP(DetalleVentaProducto dvp){
        boolean r = false;
        ConexionMySQL conexionMySQL = new ConexionMySQL();
        Connection connection = conexionMySQL.open();
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            //String query1 = "INSERT INTO venta(idEmpleado, clave) VALUES("+dvp.getVenta().getEmpleado().getIdEmpleado()+","+dvp.getVenta().getClave()+");";
            String query1 = "INSERT INTO venta(idEmpleado, clave) VALUES(" + dvp.getVenta().getEmpleado().getIdEmpleado() + ",'" + dvp.getVenta().getClave() + "');";

            stmt.execute(query1);
            
            String query2 = "SELECT LAST_INSERT_ID();";
            rs = stmt.executeQuery(query2);
            
            if(rs.next()){
                dvp.getVenta().setIdVenta(rs.getInt(1));
            }
            
            for(int i = 0; i < dvp.getListaVP().size(); i++){
                String query3 = "INSERT INTO venta_producto VALUES("
                        +dvp.getVenta().getIdVenta()+","
                        +dvp.getListaVP().get(i).getProducto().getIdProducto()+","
                        +dvp.getListaVP().get(i).getCantidad()+","
                        +dvp.getListaVP().get(i).getPrecioUnitario()+","
                        +dvp.getListaVP().get(i).getDescuento()+");";
                stmt.execute(query3);
            }
            connection.commit();
            connection.setAutoCommit(true);
            
            rs.close();
            stmt.close();
            connection.close();
            conexionMySQL.close();
            r = true;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ControllerVenta.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
                connection.setAutoCommit(true);
                connection.close();
                conexionMySQL.close();
            } catch (SQLException ex1) {
                Logger.getLogger(ControllerVenta.class.getName()).log(Level.SEVERE, null, ex1);
                r = false;
            }
        }
        
        return r;
    }
    
    
}
