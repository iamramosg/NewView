/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.controller;

import java.util.logging.Logger;
import java.util.logging.Level;
import org.utl.dsm.optik.db.ConexionMySQL;
import org.utl.dsm.optik.db.ConexionMongoDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.bson.Document;
import org.utl.dsm.optik.model.Alumno;
import org.utl.dsm.optik.model.Empleado;
import org.utl.dsm.optik.model.Persona;
import org.utl.dsm.optik.model.Usuario;
import org.utl.dsm.optik.model.Producto;
import org.utl.dsm.optik.model.Armazon;
import org.utl.dsm.optik.model.Cliente;
import org.utl.dsm.optik.model.Evaluacion;
import org.utl.dsm.optik.model.ExamenVista;
import org.utl.dsm.optik.model.TicketProducto;

/**
 *
 * @author garni
 */
public class prueba {

    public static void main(String[] args) throws Exception {
        probarCatalago("OQ-83544494");
        //mostrar("21000012");
        //insertarAlumno();
        //probarCatalago(1);
        //probarInseratar();
        //probarAcceso();
        //probarConexion();
        //ConexionMongoDB objCo = new ConexionMongoDB();
        //objCo.open();

    }


    public static void probarCatalago(String filtro) {
        // Paso 1 invocar el controlador 
        ControllerTickets objTK= new ControllerTickets();
        try {
            // Paso 2 invocar el metodo
            List<TicketProducto> ticketProductos= objTK.getAllP(filtro);
            // recorrer y mostrar los resultados
            for (int i = 0; i <ticketProductos.size(); i++) {
                System.out.println(ticketProductos.get(i).toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void probarConexion() {
        try {
            ConexionMySQL objConexion = new ConexionMySQL();
            Connection conexion = objConexion.open();
            System.out.println(conexion.toString());
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void probarInsert() {
        // Paso 1 genero un objeto de persona 
        Producto p = new Producto();
        p.setCodigoBarras("5678");
        p.setNombre("prueba2");
        p.setMarca("prueba3");
        p.setPrecioCompra(130);
        p.setPrecioVenta(140);
        p.setExistencias(20);

        // Crear un onjeto de usuario 
        Armazon a = new Armazon();
        a.setModelo("x");
        a.setColor("blue");
        a.setDimensiones("45-60");
        a.setDescripcion("Hola");
        a.setFotografia("5678900vbvgb");
        a.setProducto(p);

        // creamos una clase del objeto controllador empleado 
        ControllerArmazon objAr = new ControllerArmazon();
        try {
            objAr.insertar(a);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(a.toString());

    }

    public static void insertarAlumno() throws Exception {
        Evaluacion e = new Evaluacion();
        ControllerAlumno objAl = new ControllerAlumno();

        e.setCalificacion(9);
        e.setFecha("2023-03-12");
        e.setMateria("Matematicas");
        e.setComentario("Buen trabajo");
        e.setSeleccion(2);

        try {
            objAl.insertar(e);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(e.toString());

    }

    public static void probarUpdate() {
        // Paso 1 genero un objeto de persona 
        Persona p = new Persona();
        p.setIdPersona(26);
        p.setNombre("Xiomara");
        p.setApellidoPaterno("Ponce");
        p.setApellidoMaterno("Ponce");
        p.setGenero("F");
        p.setFechaNacimiento("2003/08/06");
        p.setCalle("Paracho");
        p.setNumero("112");
        p.setColonia("La Brisa");
        p.setCp("37358");
        p.setCiudad("León");
        p.setEstado("Guanajuato");
        p.setTelcasa("12");
        p.setTelmovil("123");
        p.setEmail("ponce@gmail.com");

        // Crear un onjeto de usuario 
        Cliente u = new Cliente();
        u.setIdCliente(1);
        u.setPersona(p);

//        Empleado emp = new Empleado();
//        emp.setIdEmpleado(4);
//        emp.setPersona(p);
//        emp.setUsuario(u);
        // creamos una clase del objeto controllador empleado 
        ControllerCliente objCE = new ControllerCliente();
        try {
            objCE.actualizar(u);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(u.toString());

    }

    public static void probarAcceso() {
        Usuario u = new Usuario();
        u.setNombre("1");
        u.setContrasenia("1");
        ControllerAcceso ca = new ControllerAcceso();
        try {
            Empleado e = ca.entrar(u);
            e.toString();
            System.out.println("Acceso Concedido");
        } catch (Exception ex) {
            System.out.println("Acceso Denegado");
        }
    }

    public static void probarInseratar() {
        // Paso 1 genero un objeto de persona 
        Persona p = new Persona();
        p.setNombre("Xiomara");
        p.setApellidoPaterno("Ponce");
        p.setApellidoMaterno("Ponce");
        p.setGenero("F");
        p.setFechaNacimiento("2003/08/06");
        p.setCalle("Paracho");
        p.setNumero("112");
        p.setColonia("La Brisa");
        p.setCp("37358");
        p.setCiudad("León");
        p.setEstado("Guanajuato");
        p.setTelcasa("12");
        p.setTelmovil("123");
        p.setEmail("ponce@gmail.com");

        // Crear un onjeto de usuario 
        Usuario u = new Usuario();
        u.setNombre("PonceS");
        u.setContrasenia("1234");
        u.setRol("Gerente");

        Empleado emp = new Empleado();
        emp.setPersona(p);
        emp.setUsuario(u);

        // creamos una clase del objeto controllador empleado 
        ControllerEmpleado objCE = new ControllerEmpleado();
        try {
            objCE.insertar(emp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(emp.toString());

    }

    public static void mostrar(String filtro) {
        ControllerAlumno objAl = new ControllerAlumno();
        List<Alumno> alumnos = objAl.buscarAlumnosPorMatricula(filtro);

        for (Alumno alumno : alumnos) {
            System.out.println("Nombre: " + alumno.getNombre());
            System.out.println("Matrícula: " + alumno.getMatricula());
            System.out.println("Fecha de nacimiento: " + alumno.getFechaNacimiento());
            System.out.println("Materia: " + alumno.getEvaluacion().getMateria());
            System.out.println("Calificación: " + alumno.getEvaluacion().getCalificacion());
            System.out.println("Fecha de evaluación: " + alumno.getEvaluacion().getFecha());
            System.out.println("Comentario: " + alumno.getEvaluacion().getComentario());
            System.out.println();
        }
    }
}
