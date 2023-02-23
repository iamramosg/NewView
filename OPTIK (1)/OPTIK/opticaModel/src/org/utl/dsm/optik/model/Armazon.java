
package org.utl.dsm.optik.model;

/**
 *
 * @author Gabriel
 */
public class Armazon {
    private int idArmazon;
    private String modelo;
    private String color;
    private String dimensiones;
    private String descripcion;
    private String fotografia;
    private int estatus;
    private Producto producto;

    public Armazon() {
    }

    public Armazon(String modelo, String color, String dimensiones, String descripcion, String fotografia, int estatus, Producto producto) {
        this.modelo = modelo;
        this.color = color;
        this.dimensiones = dimensiones;
        this.descripcion = descripcion;
        this.fotografia = fotografia;
        this.estatus = estatus;
        this.producto = producto;
    }

    public Armazon(int idArmazon, String modelo, String color, String dimensiones, String descripcion, String fotografia, int estatus, Producto producto) {
        this.idArmazon = idArmazon;
        this.modelo = modelo;
        this.color = color;
        this.dimensiones = dimensiones;
        this.descripcion = descripcion;
        this.fotografia = fotografia;
        this.estatus = estatus;
        this.producto = producto;
    }

    public int getIdArmazon() {
        return idArmazon;
    }

    public void setIdArmazon(int idArmazon) {
        this.idArmazon = idArmazon;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "Armazon{" + "idArmazon=" + idArmazon + ", modelo=" + modelo + ", color=" + color + ", dimensiones=" + dimensiones + ", descripcion=" + descripcion + ", fotografia=" + fotografia + ", estatus=" + estatus + ", producto=" + producto + '}';
    }
    
 
}
