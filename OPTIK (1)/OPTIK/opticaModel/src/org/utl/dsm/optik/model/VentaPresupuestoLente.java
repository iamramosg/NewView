
package org.utl.dsm.optik.model;

/**
 *
 * @author iamra
 */
public class VentaPresupuestoLente {
    private PresupuestoLente presupuestoLente;
    private int cantidad;
    private float precioUnitario;
    private float descuento;

    public VentaPresupuestoLente() {
    }

    public VentaPresupuestoLente(PresupuestoLente presupuestoLente, int cantidad, float precioUnitario, float descuento) {
        this.presupuestoLente = presupuestoLente;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public PresupuestoLente getPresupuestoLente() {
        return presupuestoLente;
    }

    public void setPresupuestoLente(PresupuestoLente presupuestoLente) {
        this.presupuestoLente = presupuestoLente;
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

    @Override
    public String toString() {
        return "VentaPresupuestoLente{" + "presupuestoLente=" + presupuestoLente + ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + ", descuento=" + descuento + '}';
    }
    
    
}
