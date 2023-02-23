/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;

/**
 *
 * @author Gabriel
 */
public class VentaPresupuesto {
    private int     idVenta;
    private Presupuesto idPresupuesto;
    private int cantidad;
    private double precioUnitario;
    private double descuento;

    public VentaPresupuesto() {
    }

    public VentaPresupuesto(Presupuesto idPresupuesto, int cantidad, double precioUnitario, double descuento) {
        this.idPresupuesto = idPresupuesto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
    }

    public VentaPresupuesto(int idVenta, Presupuesto idPresupuesto, int cantidad, double precioUnitario, double descuento) {
        this.idVenta = idVenta;
        this.idPresupuesto = idPresupuesto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Presupuesto getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(Presupuesto idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    @Override
    public String toString() {
        return "VentaPresupuesto{" + "idVenta=" + idVenta + ", idPresupuesto=" + idPresupuesto + ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + ", descuento=" + descuento + '}';
    }
    
    
}
