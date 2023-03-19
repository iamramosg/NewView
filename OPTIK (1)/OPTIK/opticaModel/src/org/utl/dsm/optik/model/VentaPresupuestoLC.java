/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;

/**
 *
 * @author iamra
 */
public class VentaPresupuestoLC {
    private PresupuestoLenteContacto presupuestoLenteContacto;
    private int cantidad;
    private float precioUnitario;
    private float descuento;

    public VentaPresupuestoLC() {
    }

    public VentaPresupuestoLC(PresupuestoLenteContacto presupuestoLenteContacto, int cantidad, float precioUnitario, float descuento) {
        this.presupuestoLenteContacto = presupuestoLenteContacto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
    }
    

    public PresupuestoLenteContacto getPresupuestoLenteContacto() {
        return presupuestoLenteContacto;
    }

    public void setPresupuestoLenteContacto(PresupuestoLenteContacto presupuestoLenteContacto) {
        this.presupuestoLenteContacto = presupuestoLenteContacto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    
    
    
}
