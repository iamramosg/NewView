
package org.utl.dsm.optik.model;

/**
 *
 * @author iamra
 */
public class Auto {
    private int idAuto;
    private int clave;
    private double costo;
    private double monto;
    private String fecha;

    public Auto() {
    }

    public Auto(int clave, double costo, double monto, String fecha) {
        this.clave = clave;
        this.costo = costo;
        this.monto = monto;
        this.fecha = fecha;
    }

    public Auto(int idAuto, int clave, double costo, double monto, String fecha) {
        this.idAuto = idAuto;
        this.clave = clave;
        this.costo = costo;
        this.monto = monto;
        this.fecha = fecha;
    }

    public int getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(int idAuto) {
        this.idAuto = idAuto;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Auto{" + "idAuto=" + idAuto + ", clave=" + clave + ", costo=" + costo + ", monto=" + monto + ", fecha=" + fecha + '}';
    }
    
    
}
