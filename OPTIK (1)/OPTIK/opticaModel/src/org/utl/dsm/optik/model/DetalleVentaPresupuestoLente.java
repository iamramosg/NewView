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
public class DetalleVentaPresupuestoLente {
    private Venta venta;
    private List<VentaPresupuestoLente> listaVentaPresupuestoL;

    public DetalleVentaPresupuestoLente() {
    }

    public DetalleVentaPresupuestoLente(Venta venta, List<VentaPresupuestoLente> listaVentaPresupuestoL) {
        this.venta = venta;
        this.listaVentaPresupuestoL = listaVentaPresupuestoL;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public List<VentaPresupuestoLente> getListaVentaPresupuestoL() {
        return listaVentaPresupuestoL;
    }

    public void setListaVentaPresupuestoL(List<VentaPresupuestoLente> listaVentaPresupuestoL) {
        this.listaVentaPresupuestoL = listaVentaPresupuestoL;
    }

    @Override
    public String toString() {
        return "DetalleVentaPresupuestoLente{" + "venta=" + venta + ", listaVentaPresupuestoL=" + listaVentaPresupuestoL + '}';
    }
    
    
}
