/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.controller;

import org.utl.dsm.optik.db.ConexionMySQL;
import org.utl.dsm.optik.model.Empleado;
import org.utl.dsm.optik.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.utl.dsm.optik.model.Persona;

/**
 *
 * @author iamra
 */
public class ControllerAcceso {
    public Empleado entrar(Usuario u) throws SQLException, Exception{
        String qr = "SELECT * FROM vistaE WHERE nombre = '"+u.getNombre()+"' AND contrasenia ='" + u.getContrasenia()+"'";
        
        ConexionMySQL mysql = new ConexionMySQL();
        Connection conn = mysql.open();
        
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(qr);
        
        Empleado e = new Empleado();
        if(rs.next()){
            e = fill(rs);
        }
        rs.close();
        stmt.close();
        conn.close();
        mysql.close();
        
        return e;
    }
    private Empleado fill(ResultSet rs) throws Exception{
            Empleado e = new Empleado();
            Usuario u = new Usuario();
            u.setIdUsuario(rs.getInt("idUsuario"));
            u.setNombre(rs.getString("nombreU"));
            u.setContrasenia(rs.getString("contrasenia"));
            u.setRol(rs.getString("rol"));
            e.setUsuario(u);
            
            Persona p = new Persona();
            p.setIdPersona(rs.getInt("idPersona"));
            p.setNombre(rs.getString("nombre"));
            p.setApellidoPaterno(rs.getString("apellidoPaterno"));
            p.setApellidoMaterno(rs.getString("apellidoMaterno"));
            p.setGenero(rs.getString("genero"));
            p.setFechaNacimiento(rs.getString("fechaNacimiento"));
            p.setCalle(rs.getString("calle"));
            p.setNumero(rs.getString("numero"));
            p.setColonia(rs.getString("colonia"));
            p.setCp(rs.getString("cp"));
            p.setCiudad(rs.getString("ciudad"));
            p.setEstado(rs.getString("estado"));
            p.setTelcasa(rs.getString("telcasa"));
            p.setTelmovil(rs.getString("telmovil"));
            p.setEmail(rs.getString("email"));
            e.setPersona(p);
            
            e.setIdEmpleado(rs.getInt("idEmpleado"));
            e.setNumeroUnico(rs.getString("numeroUnico"));
            e.setEstatus(rs.getInt("estatus"));
            return e;

        
        
    }
    public void guardarToken(Empleado empleado) throws Exception{
        String query = "UPDATE usuario set lastToken=?,dateLastToken=NOW() WHERE idUsuario =?";
        
        ConexionMySQL conexionMySQL = new ConexionMySQL();
        
        Connection connection = conexionMySQL.open();
        
        PreparedStatement preparedStatement = connection.prepareCall(query);
        
        preparedStatement.setString(1, empleado.getUsuario().getLastToken());
        preparedStatement.setInt(2, empleado.getUsuario().getIdUsuario());
        
        preparedStatement.execute();
        
        preparedStatement.close();
        connection.close();
        conexionMySQL.close();
    }
    
    public boolean eliminarToken(Empleado e) throws Exception
    {
        boolean r = false;
        String query = "UPDATE usuario SET lastToken='' WHERE idUsuario = ?";
        
        ConexionMySQL conexionMySQL = new ConexionMySQL();
        
        Connection connection = conexionMySQL.open();
        
        PreparedStatement preparedStatement = connection.prepareCall(query);
        preparedStatement.setInt(1, e.getUsuario().getIdUsuario());
        
        preparedStatement.execute();
        r=true;
        
        preparedStatement.close();
        connection.close();
        conexionMySQL.close();        
        
        
        return r;
    }
    
    public boolean validarToken(String t) throws Exception
    {
        boolean r = false;
        String query = "SELECT * from vistaE where lastToken = '"+t+"'";
        ConexionMySQL conexionMySQL = new ConexionMySQL();
        Connection connection = conexionMySQL.open();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        if(rs.next())
            r=true;
        stmt.close();
        connection.close();
        conexionMySQL.close();
        
        return r;
    }
    
}
