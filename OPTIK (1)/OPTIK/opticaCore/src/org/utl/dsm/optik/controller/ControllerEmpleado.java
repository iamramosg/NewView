package org.utl.dsm.optik.controller;

import org.utl.dsm.optik.db.ConexionMySQL;
import org.utl.dsm.optik.model.Empleado;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.utl.dsm.optik.model.Persona;
import org.utl.dsm.optik.model.Usuario;

/**
 *
 * @author Gabriel
 */
public class ControllerEmpleado {
    public int insertar(Empleado empleado) throws Exception{
        //Paso 1: Preparar la sentencia sql
        String query = "call insertarEmpleado(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int idPersonaG = 0;
        int idUsuarioG = 0;
        int idEmpleadoG = 0;
        String numeroUnicoG = "";
        String lastTokenG = "";
        
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        
        //Paso 4: Asignar los valores para cada parametro
        cstmt.setString(1, empleado.getPersona().getNombre());
        cstmt.setString(2, empleado.getPersona().getApellidoPaterno());
        cstmt.setString(3, empleado.getPersona().getApellidoMaterno());
        cstmt.setString(4, empleado.getPersona().getGenero());
        cstmt.setString(5, empleado.getPersona().getFechaNacimiento());
        cstmt.setString(6, empleado.getPersona().getCalle());
        cstmt.setString(7, empleado.getPersona().getNumero());
        cstmt.setString(8, empleado.getPersona().getColonia());
        cstmt.setString(9, empleado.getPersona().getCp());
        cstmt.setString(10, empleado.getPersona().getCiudad());
        cstmt.setString(11, empleado.getPersona().getEstado());
        cstmt.setString(12, empleado.getPersona().getTelcasa());
        cstmt.setString(13, empleado.getPersona().getTelmovil());
        cstmt.setString(14, empleado.getPersona().getEmail());
        cstmt.setString(15, empleado.getUsuario().getNombre());
        cstmt.setString(16, empleado.getUsuario().getContrasenia());
        cstmt.setString(17, empleado.getUsuario().getRol());
        
        //Paso 5: Registrar parametros de salida del procedure
        cstmt.registerOutParameter(18, Types.INTEGER);
        cstmt.registerOutParameter(19, Types.INTEGER);
        cstmt.registerOutParameter(20, Types.INTEGER);
        cstmt.registerOutParameter(21, Types.VARCHAR);
        cstmt.registerOutParameter(22, Types.VARCHAR);
        
        //Paso 6: Ejecutar la llamada al procedure
        cstmt.executeUpdate();
        
        // Paso 7 recuperar los parametros de salida 
        idPersonaG = cstmt.getInt(18);
        idUsuarioG = cstmt.getInt(19);
        idEmpleadoG = cstmt.getInt(20);
        numeroUnicoG = cstmt.getString(21);
        lastTokenG = cstmt.getString(22);
        
        // Paso 8 insertar los valores en el objeto 
        empleado.getPersona().setIdPersona(idPersonaG);
        empleado.getUsuario().setIdUsuario(idUsuarioG);
        empleado.setIdEmpleado(idEmpleadoG);
        empleado.setNumeroUnico(numeroUnicoG);
        empleado.getUsuario().setLastToken(lastTokenG);
        
        // Paso 9 cerrar objetos de conexion 
        cstmt.close();
        conn.close();
        conexion.close();
        
        // Paso 10 devolver o return el id generado
        return idEmpleadoG;
    }
    public List<Empleado> getAll(String filtro) throws SQLException{
        // paso uno declaramos la query que vamos a llamar
        String query = "SELECT * FROM vistaE WHERE estatus="+filtro+";";
   
        //Paso 2 generar conexion Mysql
        ConexionMySQL objConexion = new ConexionMySQL();
        // Abro la conexion
        Connection conn = objConexion.open();
        // Preparo el envio
        PreparedStatement pstmt = conn.prepareStatement(query);
        //Declaramos la lista
        List<Empleado> empleados = new ArrayList<>();
        // nos devuelve la informacion
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
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
            empleados.add(e);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return empleados;
        
    }
    
    public void actualizar(Empleado empleado) throws Exception{
        //Paso 1: Preparar la sentencia sql
        String query = "call actualizarEmpleado(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        
        //Paso 4: Asignar los valores para cada parametro
        cstmt.setString(1, empleado.getPersona().getNombre());
        cstmt.setString(2, empleado.getPersona().getApellidoPaterno());
        cstmt.setString(3, empleado.getPersona().getApellidoMaterno());
        cstmt.setString(4, empleado.getPersona().getGenero());
        cstmt.setString(5, empleado.getPersona().getFechaNacimiento());
        cstmt.setString(6, empleado.getPersona().getCalle());
        cstmt.setString(7, empleado.getPersona().getNumero());
        cstmt.setString(8, empleado.getPersona().getColonia());
        cstmt.setString(9, empleado.getPersona().getCp());
        cstmt.setString(10, empleado.getPersona().getCiudad());
        cstmt.setString(11, empleado.getPersona().getEstado());
        cstmt.setString(12, empleado.getPersona().getTelcasa());
        cstmt.setString(13, empleado.getPersona().getTelmovil());
        cstmt.setString(14, empleado.getPersona().getEmail());
        cstmt.setString(15, empleado.getUsuario().getNombre());
        cstmt.setString(16, empleado.getUsuario().getContrasenia());
        cstmt.setString(17, empleado.getUsuario().getRol());
        
        cstmt.setInt(18, empleado.getPersona().getIdPersona());
        cstmt.setInt(19, empleado.getUsuario().getIdUsuario());
        cstmt.setInt(20, empleado.getIdEmpleado());
        
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
    public void eliminar(Empleado empleado) throws Exception{
        String query = "call eliminarEmpleado(?)";
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        cstmt.setInt(1, empleado.getIdEmpleado());
        cstmt.executeUpdate();
        cstmt.close();
        conn.close();
        conexion.close();
    }
    public void activar(Empleado empleado) throws Exception{
        String query = "call activarEmpleado(?)";
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        CallableStatement cstmt = conn.prepareCall(query);
        cstmt.setInt(1, empleado.getIdEmpleado());
        cstmt.executeUpdate();
        cstmt.close();
        conn.close();
        conexion.close();
    }
}
