/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;

/**
 *
 * @author Gabriel
 */
public class CompraProducto {
    private int idCompra;
    private Producto idProducto;
    private double precioUnitario;
    private int cantidad;  

    public CompraProducto() {
    }

    public CompraProducto(Producto idProducto, double precioUnitario, int cantidad) {
        this.idProducto = idProducto;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }

    public CompraProducto(int idCompra, Producto idProducto, double precioUnitario, int cantidad) {
        this.idCompra = idCompra;
        this.idProducto = idProducto;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "CompraProducto{" + "idCompra=" + idCompra + ", idProducto=" + idProducto + ", precioUnitario=" + precioUnitario + ", cantidad=" + cantidad + '}';
    }
    
    
}
