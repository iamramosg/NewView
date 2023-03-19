/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.utl.dsm.optik.db.ConexionMySQL;
import org.utl.dsm.optik.model.Tratamiento;

/**
 *
 * @author iamra
 */
public class ControllerTratamiento {

    public List<Tratamiento> getAll(String filtro) throws SQLException {
        //Paso 1: Preparar la sentencia sql
        String query = "SELECT * FROM vista_tratamiento WHERE estatus = " + filtro + ";";

        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();

        PreparedStatement pstmt = conn.prepareStatement(query);
        List<Tratamiento> tratamientos = new ArrayList<>();
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Tratamiento t = new Tratamiento();
            t.setIdTratamiento(rs.getInt("ID"));
            t.setEstatus(rs.getInt("Estatus"));
            t.setNombre(rs.getString("Nombre"));
            t.setPrecioCompra(rs.getDouble("Precio_Compra"));
            t.setPrecioVenta(rs.getDouble("Precio_Venta"));

            tratamientos.add(t);
        }
        rs.close();
        pstmt.close();
        conn.close();

        return tratamientos;
    }
}
