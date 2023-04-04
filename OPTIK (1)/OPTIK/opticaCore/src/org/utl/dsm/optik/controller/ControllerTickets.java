/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.controller;
import org.utl.dsm.optik.db.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.utl.dsm.optik.model.TicketProducto;
/**
 *
 * @author iamra
 */
public class ControllerTickets {
        public List<TicketProducto> getAllP(String filtro) throws SQLException {
        String query = "SELECT * from view_ventap WHERE empleado = '" + filtro + "' or apellidoPaterno = '" + filtro + "' or apellidoMaterno = '" + filtro + "' or clave = '" + filtro + "';";

        ConexionMySQL objConexion = new ConexionMySQL();
        // Abro la conexion
        Connection conn = objConexion.open();
        // Preparo el envio
        PreparedStatement pstmt = conn.prepareStatement(query);

        List<TicketProducto> ticketProductos = new ArrayList<>();
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            TicketProducto tkp = new TicketProducto();
            tkp.setEmpleado(rs.getString("Empleado"));
            tkp.setApellidoPaterno(rs.getString("apellidoPaterno"));
            tkp.setApellidoMaterno(rs.getString("apellidoMaterno"));
            tkp.setNumeroUnico(rs.getString("numeroUnico"));
            tkp.setCodigoBarras(rs.getString("codigoBarras"));
            tkp.setNombre(rs.getString("nombre"));
            tkp.setMarca(rs.getString("marca"));
            tkp.setClave(rs.getString("clave"));
            tkp.setCantidad(rs.getInt("cantidad"));
            tkp.setPrecioUnitario(rs.getDouble("precioUnitario"));
            tkp.setDescuento(rs.getDouble("descuento"));
            ticketProductos.add(tkp);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return ticketProductos;
    }
}
