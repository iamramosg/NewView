
package org.utl.dsm.optik.controller;
import com.mysql.cj.MysqlType;
import java.io.IOException;
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
import org.utl.dsm.optik.model.Producto;
import org.utl.dsm.optik.model.Armazon;
/**
 *
 * @author iamra
 */
public class ControllerArmazon {

    public int insertar(Armazon armazon) throws SQLException, IOException {
        //Paso1: Preparar la setencia sql
        String query = "{call insertarArmazon(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        int idArmazonG = 0;
        int idProducto = 0;
        String codigoBarrasG = "";

        //Paso2: Conectarse a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = (Connection) conexion.open();

        //Paso3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        //Paso4: Asignar los valores para cada parametro
        cstmt.setString(1, armazon.getModelo());
        cstmt.setString(2, armazon.getColor());
        cstmt.setString(3, armazon.getDimensiones());
        cstmt.setString(4, armazon.getDescripcion());
        cstmt.setString(5, armazon.getFotografia());
        cstmt.setString(6, armazon.getProducto().getNombre());
        cstmt.setString(7, armazon.getProducto().getMarca());
        cstmt.setDouble(8, armazon.getProducto().getPrecioCompra());
        cstmt.setDouble(9,armazon.getProducto().getPrecioVenta());
        cstmt.setInt(10, armazon.getProducto().getExistencias());

        //PASO5: Registrar parametros de salida del procedure
        cstmt.registerOutParameter(11, MysqlType.FIELD_TYPE_VARCHAR);
        cstmt.registerOutParameter(12, Types.INTEGER);
        cstmt.registerOutParameter(13, Types.INTEGER);

        //Paso6: Ejecutar la llamada al procedure
        cstmt.executeUpdate();

        idArmazonG = cstmt.getInt(12);
        idProducto = cstmt.getInt(13);
        codigoBarrasG = cstmt.getString(11);

        //Paso8: insertar los valores en el objeto
        armazon.getProducto().setIdProducto(idProducto);
        armazon.getProducto().setCodigoBarras(codigoBarrasG);
        armazon.setIdArmazon(idArmazonG);

        //Paso9: Cerrar los objetos de conexion
        cstmt.close();
        conn.close();
        conexion.close();
        //Paso10: Devolver el id generado
        return idArmazonG;

    }

    public void actualizar(Armazon armazon) throws SQLException {

        //Paso 1: Preparar la sentencia sql
        String query = "call actualizarArmazon(?,?,?,?,?,?,?,?,?,?,?)";
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        CallableStatement cstmt = conn.prepareCall(query);
        //Paso 4: Asignar los valores para cada parametro
        cstmt.setString(1, armazon.getModelo());
        cstmt.setString(2, armazon.getColor());
        cstmt.setString(3, armazon.getDimensiones());
        cstmt.setString(4, armazon.getDescripcion());
        cstmt.setString(5, armazon.getFotografia());
        cstmt.setString(6, armazon.getProducto().getNombre());
        cstmt.setString(7, armazon.getProducto().getMarca());
        cstmt.setDouble(8, armazon.getProducto().getPrecioCompra());
        cstmt.setDouble(9, armazon.getProducto().getPrecioVenta());
        cstmt.setInt(10, armazon.getProducto().getExistencias());
        cstmt.setInt(11, armazon.getIdArmazon());

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

    public List<Armazon> getAll(String filtro) throws SQLException {
        String query = "SELECT * FROM vistaAR WHERE estatusA = " + filtro + ";";
        ConexionMySQL objConexion = new ConexionMySQL();
        Connection conn = objConexion.open();
        PreparedStatement pstmt = conn.prepareStatement(query);
        List<Armazon> armazones = new ArrayList<>();
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Armazon e = new Armazon();
            Producto p = new Producto();

            p.setIdProducto(rs.getInt("idProducto"));
            p.setNombre(rs.getString("nombre"));
            p.setCodigoBarras(rs.getString("codigoBarras"));
            p.setMarca(rs.getString("marca"));
            p.setPrecioCompra(rs.getDouble("precioCompra"));
            p.setPrecioVenta(rs.getDouble("precioVenta"));
            p.setExistencias(rs.getInt("existencias"));
            p.setEstatus(rs.getInt("estatusP"));

            e.setProducto(p);

            e.setIdArmazon(rs.getInt("idArmazon"));
            e.setModelo(rs.getString("Modelo"));
            e.setColor(rs.getString("color"));
            e.setDimensiones(rs.getString("dimensiones"));
            e.setDescripcion(rs.getString("descripcion"));
            e.setFotografia(rs.getString("fotografia"));
            e.setEstatus(rs.getInt("estatusA"));
            armazones.add(e);
        }
        rs.close();
        pstmt.close();
        conn.close();

        return armazones;
    }

    public void eliminar(String idArm) throws SQLException {

        //Paso 1: Preparar la sentencia sql
        String query = "UPDATE armazon SET estatus = 0 WHERE idArmazon = ?";
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        PreparedStatement cstmt = conn.prepareCall(query);
        cstmt.setString(1, idArm);
        cstmt.executeUpdate();
        // Paso 7 recuperar los parametros de salida
        // Paso 8 insertar los valores en el objeto
        // Paso 9 cerrar objetos de conexion
        cstmt.close();
        conn.close();
        conexion.close();
        // Paso 10 devolver o return el id generado
    }

    public void agregar(String idArm) throws SQLException {

        //Paso 1: Preparar la sentencia sql
        String query = "UPDATE armazon SET estatus = 1 WHERE idArmazon = ?";
        //Paso 2: Conectar a la BD
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        PreparedStatement cstmt = conn.prepareCall(query);
        cstmt.setString(1, idArm);
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
