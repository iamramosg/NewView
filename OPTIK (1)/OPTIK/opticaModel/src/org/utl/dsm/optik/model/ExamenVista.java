/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;

/**
 *
 * @author Gabriel
 */
public class ExamenVista {
    private int idExamenVista;
    private String clave;
    private Empleado idEmpleado;
    private Cliente idCliente;
    private Graduacion idGraduacion;
    private String fecha;

    public ExamenVista() {
    }

    public ExamenVista(String clave, Empleado idEmpleado, Cliente idCliente, Graduacion idGraduacion, String fecha) {
        this.clave = clave;
        this.idEmpleado = idEmpleado;
        this.idCliente = idCliente;
        this.idGraduacion = idGraduacion;
        this.fecha = fecha;
    }

    public ExamenVista(int idExamenVista, String clave, Empleado idEmpleado, Cliente idCliente, Graduacion idGraduacion, String fecha) {
        this.idExamenVista = idExamenVista;
        this.clave = clave;
        this.idEmpleado = idEmpleado;
        this.idCliente = idCliente;
        this.idGraduacion = idGraduacion;
        this.fecha = fecha;
    }

    public int getIdExamenVista() {
        return idExamenVista;
    }

    public void setIdExamenVista(int idExamenVista) {
        this.idExamenVista = idExamenVista;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Graduacion getIdGraduacion() {
        return idGraduacion;
    }

    public void setIdGraduacion(Graduacion idGraduacion) {
        this.idGraduacion = idGraduacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "ExamenVista{" + "idExamenVista=" + idExamenVista + ", clave=" + clave + ", idEmpleado=" + idEmpleado + ", idCliente=" + idCliente + ", idGraduacion=" + idGraduacion + ", fecha=" + fecha + '}';
    }
    
    
}
