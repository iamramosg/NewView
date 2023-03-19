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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.utl.dsm.optik.model.DetalleVentaPresupuesto;
import org.utl.dsm.optik.model.Cliente;
import org.utl.dsm.optik.model.Empleado;
import org.utl.dsm.optik.model.Persona;
import org.utl.dsm.optik.model.ExamenVista;
import org.utl.dsm.optik.model.Graduacion;
import org.utl.dsm.optik.model.LenteContacto;
import org.utl.dsm.optik.model.Producto;
import org.utl.dsm.optik.model.DetalleVentaPresupuestoLente;

/**
 *
 * @author iamra
 */
public class ControllerVentaLC {

    public List<Cliente> getAll(String filtro) throws SQLException {
        String query = "SELECT * from vistaC WHERE nombre = '" + filtro + "' or apellidoPaterno = '" + filtro + "' or apellidoMaterno = '" + filtro + "' or calle = '" + filtro + "' or colonia = '" + filtro + "'or genero = '" + filtro + "';";

        ConexionMySQL objConexion = new ConexionMySQL();
        // Abro la conexion
        Connection conn = objConexion.open();
        // Preparo el envio
        PreparedStatement pstmt = conn.prepareStatement(query);

        List<Cliente> clientes = new ArrayList<>();
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Cliente c = new Cliente();
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
            c.setPersona(p);

            c.setIdCliente(rs.getInt("idCliente"));
            c.setNumeroUnico(rs.getString("numeroUnico"));
            c.setEstatus(rs.getInt("estatus"));
            clientes.add(c);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return clientes;
    }

    public boolean generarVentaLC(DetalleVentaPresupuesto dvp) {
        boolean r = false;
        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn = connMySQL.open();
        Statement stmnt = null;
        ResultSet rs = null;

        try {
            conn.setAutoCommit(false);
            stmnt = conn.createStatement();
            String query1 = "INSERT INTO venta (idEmpleado, clave) "
                    + "VALUES (" + dvp.getVenta().getEmpleado().getIdEmpleado()
                    + ",'" + dvp.getVenta().getClave() + "');";
            // Se inserta la venta
            stmnt.execute(query1);
            // Se obtiene el id de la venta que se ha insertado
            String query2 = "SELECT LAST_INSERT_ID()";
            rs = stmnt.executeQuery(query2);
            if (rs.next()) {
                dvp.getVenta().setIdVenta(rs.getInt(1));
            }

            for (int i = 0; i < dvp.getListaVentaPresupuestoLC().size(); i++) {
                //Se inserta el presupuesto
                String query3 = "INSERT INTO presupuesto"
                        + "(idExamenVista, clave)"
                        + "VALUES (" + dvp.getListaVentaPresupuestoLC().get(i).getPresupuestoLenteContacto().getPresupuesto().getExamenVista().getIdExamenVista()
                        + ",'" + dvp.getListaVentaPresupuestoLC().get(i).getPresupuestoLenteContacto().getPresupuesto().getClave() + "' );";

                stmnt.execute(query3);
                //Se obtiene el id del presupuesto generado
                rs = stmnt.executeQuery(query2);
                if (rs.next()) {
                    dvp.getListaVentaPresupuestoLC().get(i).getPresupuestoLenteContacto().getPresupuesto().setIdPresupuesto(rs.getInt(1));
                }

                //Se inserta en presupuesto_lentescontacto 
                String query4 = "INSERT INTO presupuesto_lentescontacto"
                        + "(idExamenVista, idLenteContacto, clave)"
                        + "VALUES (" + dvp.getListaVentaPresupuestoLC().get(i).getPresupuestoLenteContacto().getPresupuesto().getExamenVista().getIdExamenVista() + ","
                        + dvp.getListaVentaPresupuestoLC().get(i).getPresupuestoLenteContacto().getLenteContacto().getIdLenteContacto() + ","
                        + "'" + dvp.getListaVentaPresupuestoLC().get(i).getPresupuestoLenteContacto().getClave() + "');";
                stmnt.execute(query4);
                //Se obtiene el id del presupuesto_lentescontacto generado
                rs = stmnt.executeQuery(query2);
                if (rs.next()) {
                    dvp.getListaVentaPresupuestoLC().get(i).getPresupuestoLenteContacto().setIdPresupuestoLentesContacto(rs.getInt(1));
                }

                //Se insera en venta_presupuesto la relación entre la venta y el presupuesto
                String query5 = "INSERT INTO venta_presupuesto "
                        + "(idVenta, idPresupuesto, cantidad, precioUnitario, descuento) "
                        + "VALUES ("
                        + dvp.getVenta().getIdVenta() + ","
                        + dvp.getListaVentaPresupuestoLC().get(i).getPresupuestoLenteContacto().getPresupuesto().getIdPresupuesto() + ","
                        + dvp.getListaVentaPresupuestoLC().get(i).getCantidad() + ","
                        + dvp.getListaVentaPresupuestoLC().get(i).getPrecioUnitario() + ","
                        + dvp.getListaVentaPresupuestoLC().get(i).getDescuento() + ");";
                stmnt.execute(query5);
            }
            conn.commit();
            conn.setAutoCommit(true);
            stmnt.close();
            conn.close();
            r = true;

        } catch (SQLException ex) {
            Logger.getLogger(ControllerVentaLC.class.getName()).log(Level.SEVERE, null, ex);
            try {

                conn.rollback();
                conn.setAutoCommit(true);
                conn.close();
                r = false;
            } catch (SQLException ex1) {
                r = false;
            }
        }

        return r;
    }

    public List<ExamenVista> getAllExamen(int id) throws SQLException {
        String query = "SELECT * FROM examen_vista WHERE idCliente ='" + id + "';";

        ConexionMySQL objConexion = new ConexionMySQL();
        // Abro la conexion
        Connection conn = objConexion.open();
        // Preparo el envio
        PreparedStatement pstmt = conn.prepareStatement(query);
        List<ExamenVista> examenVistas = new ArrayList<>();
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            ExamenVista ex = new ExamenVista();
            Empleado e = new Empleado();
            Cliente c = new Cliente();
            Graduacion g = new Graduacion();
            ex.setIdExamenVista(rs.getInt("idExamenVista"));
            c.setIdCliente(rs.getInt("idCliente"));
            e.setIdEmpleado(rs.getInt("idEmpleado"));
            g.setIdGraduacion(rs.getInt("idGraduacion"));
            ex.setClave(rs.getString("clave"));
            ex.setCliente(c);
            ex.setEmpleado(e);
            ex.setGraduacion(g);

            // Obtener la fecha como un objeto Timestamp
            Timestamp timestamp = rs.getTimestamp("fecha");
            // Convertir el objeto Timestamp a un objeto Date
            Date date = new Date(timestamp.getTime());
            // Crear un objeto SimpleDateFormat con el patrón "yyyy-MM-dd"
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            // Formatear la fecha Date con el objeto SimpleDateFormat y obtener el String resultante
            String fechaFormateada = dateFormat.format(date);
            // Establecer la fecha formateada en el objeto ExamenVista
            ex.setFecha(fechaFormateada);

            examenVistas.add(ex);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return examenVistas;
    }

    public List<LenteContacto> getAllLenteContacto() throws SQLException {
        String query = "SELECT * from vistaLenteContacto ;";

        ConexionMySQL objConexion = new ConexionMySQL();
        // Abro la conexion
        Connection conn = objConexion.open();
        // Preparo el envio
        PreparedStatement pstmt = conn.prepareStatement(query);

        List<LenteContacto> lenteContactos = new ArrayList<>();
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            LenteContacto lc = new LenteContacto();
            Producto p = new Producto();
            p.setIdProducto(rs.getInt("idProducto"));
            p.setCodigoBarras(rs.getString("codigoBarras"));
            p.setExistencias(rs.getInt("existencias"));
            p.setEstatus(rs.getInt("estatus"));
            p.setMarca(rs.getString("marca"));
            p.setNombre(rs.getString("nombre"));
            p.setPrecioCompra(rs.getDouble("preciocompra"));
            p.setPrecioVenta(rs.getDouble("precioventa"));
            lc.setProducto(p); //revisar si necesito enviar un objeto de producto
            lc.setIdLenteContacto(rs.getInt("idLenteContacto"));
            lc.setFotografia(rs.getString("fotografia"));
            lc.setKeratometria(rs.getInt("keratometria"));
            lenteContactos.add(lc);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return lenteContactos;
    }

    public boolean generarVentaLentes(DetalleVentaPresupuestoLente dvp) {
        boolean r = false;

        //Creamos un objeto conexión con MySQL
        ConexionMySQL connMySQL = new ConexionMySQL();
        //objeto de tipo Connection que abre la conexión
        Connection conn = connMySQL.open();
        //objeto de Statement
        Statement stmt = null;
        //objeto de ResultSet
        ResultSet rs = null;
        /*NOTA: Los objetos quedan afuera del try catch, para poder utilizarlos en todo el método
         */
        try {
            //el autocommit se prepara para no ejecutar en automatico las sentencias
            //sino esperar a que termine la transaccion 
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            //esta query se utiliza varias veces
            String query = "SELECT LAST_INSERT_ID()";

            //Se genera la venta
            String query0 = "INSERT INTO venta (idEmpleado, clave) "
                    + "VALUES (" + dvp.getVenta().getEmpleado().getIdEmpleado()
                    + ",'" + dvp.getVenta().getClave() + "');";
            stmt.execute(query0);
            //Se obtiene el id genrado con la insercion de venta
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                dvp.getVenta().setIdVenta(rs.getInt(1));
            }

            //Se insertan varios presupuestos, por lo tanto se Cicla
            for (int i = 0; i < dvp.getListaVentaPresupuestoL().size(); i++) {

                //Se insertan los presupuestos
                String query1 = "INSERT INTO presupuesto(idExamenVista, clave)"
                        + "VALUES (" + dvp.getListaVentaPresupuestoL().get(i).getPresupuestoLente().getPresupuesto().getExamenVista().getIdExamenVista() + ","
                        + "'" + dvp.getListaVentaPresupuestoL().get(i).getPresupuestoLente().getPresupuesto().getClave()+ "'" + ");";

                stmt.execute(query1);
                rs = stmt.executeQuery(query);
                //Obtenemos el id de presupuesto y lo guardamos
                if (rs.next()) {
                    dvp.getListaVentaPresupuestoL().get(i).getPresupuestoLente().getPresupuesto().setIdPresupuesto(rs.getInt(1));
                }

                //Se insertan los presupuestos de lentes
                String query2 = "INSERT INTO presupuesto_Lentes(idPresupuesto, alturaOblea, idTipoMica, idMaterial, idArmazon)"
                        + "VALUES (" + dvp.getListaVentaPresupuestoL().get(i).getPresupuestoLente().getPresupuesto().getIdPresupuesto() + ","
                        + dvp.getListaVentaPresupuestoL().get(i).getPresupuestoLente().getAlturaOblea() + ","
                        + dvp.getListaVentaPresupuestoL().get(i).getPresupuestoLente().getTipoMica().getIdTipoMica() + ","
                        + dvp.getListaVentaPresupuestoL().get(i).getPresupuestoLente().getMaterial().getIdMaterial() + ","
                        + dvp.getListaVentaPresupuestoL().get(i).getPresupuestoLente().getArmazon().getIdArmazon() + ");";

                stmt.execute(query2);
                //Obtenermos el id de presupuesto_Lentes  y se guarda en su objeto
                rs = stmt.executeQuery(query);
                if (rs.next()) {
                    dvp.getListaVentaPresupuestoL().get(i).getPresupuestoLente().setIdPresupuestoLentes(rs.getInt(1));
                }

                //Se almacenana los tratamientos que tiene ese lente presupuestado
                //Va en un ciclo por que se tiene la posibilidad de elegir varios tratamientos
                for (int j = 0; j < dvp.getListaVentaPresupuestoL().get(i).getPresupuestoLente().getListTratamientos().size(); j++) {
                    String query3 = "INSERT INTO presupuesto_lentes_tratamientos VALUES("
                            + dvp.getListaVentaPresupuestoL().get(i).getPresupuestoLente().getIdPresupuestoLentes() + ","
                            + dvp.getListaVentaPresupuestoL().get(i).getPresupuestoLente().getListTratamientos().get(j).getIdTratamiento() + ");";
                    stmt.execute(query3);

                }
                //Query numero 4 para almacenar la relacion de venta_presupuesto
                String query4 = "INSERT INTO venta_presupuesto VALUES("
                        + dvp.getVenta().getIdVenta() + ","
                        + dvp.getListaVentaPresupuestoL().get(i).getPresupuestoLente().getPresupuesto().getIdPresupuesto() + ","
                        + dvp.getListaVentaPresupuestoL().get(i).getCantidad() + ","
                        + dvp.getListaVentaPresupuestoL().get(i).getPrecioUnitario() + ","
                        + dvp.getListaVentaPresupuestoL().get(i).getDescuento()+ ");";
                stmt.execute(query4);
            }

            //Ya con todas las sentencias ejecutadas, se envia la conformación de ejecutar la transaccion
            conn.commit();
            //Se cierran los objetos de BD
            conn.setAutoCommit(true);
            rs.close();
            stmt.close();
            r = true;

        } catch (SQLException ex) {
            Logger.getLogger(ControllerVentaLC.class.getName()).log(Level.SEVERE, null, ex);
            try {
                //En caso de error se indica un rollback a la transaccion. 
                conn.rollback();
                conn.setAutoCommit(true);
                r = false;

            } catch (SQLException ex1) {
                Logger.getLogger(ControllerVentaLC.class.getName()).log(Level.SEVERE, null, ex);
                r = false;
            }
        }
        return r;
    }
}
