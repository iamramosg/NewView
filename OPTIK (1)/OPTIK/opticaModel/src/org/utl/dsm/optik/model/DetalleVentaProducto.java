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
public class DetalleVentaProducto {
    private Venta venta;
    private List<VentaProducto> listaVP;

    public DetalleVentaProducto() {
    }

    public DetalleVentaProducto(Venta venta, List<VentaProducto> listaVP) {
        this.venta = venta;
        this.listaVP = listaVP;
    }

    public List<VentaProducto> getListaVP() {
        return listaVP;
    }

    public void setListaVP(List<VentaProducto> listaVP) {
        this.listaVP = listaVP;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    @Override
    public String toString() {
        String mensaje = "";
        for (int i =0; i < listaVP.size(); i++){
            mensaje = listaVP.get(i).toString();
        }
        return "DetalleVentaProducto{" + "venta=" + venta.toString() + ", listaVP=" + mensaje + '}';
    }
    
    
}
