/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;
import java.util.List;

/**
 *
 * @author iamra
 */
public class DetalleVentaPresupuesto {
    private Venta venta;
    private List<VentaPresupuestoLC> listaVentaPresupuestoLC;//lista presupuesto lente contacto

    public DetalleVentaPresupuesto() {
    }

    public DetalleVentaPresupuesto(Venta venta, List<VentaPresupuestoLC> listaVentaPresupuestoLC) {
        this.venta = venta;
        this.listaVentaPresupuestoLC = listaVentaPresupuestoLC;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public List<VentaPresupuestoLC> getListaVentaPresupuestoLC() {
        return listaVentaPresupuestoLC;
    }

    public void setListaVentaPresupuestoLC(List<VentaPresupuestoLC> listaVentaPresupuestoLC) {
        this.listaVentaPresupuestoLC = listaVentaPresupuestoLC;
    }

    @Override
    public String toString() {
        return "DetalleVentaPresupuesto{" + "venta=" + venta + ", listaVentaPresupuestoLC=" + listaVentaPresupuestoLC + '}';
    }

    
}
