/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;

/**
 *
 * @author Gabriel
 */
public class Solucion {
    private int idSolucion;
    private Producto idProducto;  

    public Solucion() {
    }

    public Solucion(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public Solucion(int idSolucion, Producto idProducto) {
        this.idSolucion = idSolucion;
        this.idProducto = idProducto;
    }

    public int getIdSolucion() {
        return idSolucion;
    }

    public void setIdSolucion(int idSolucion) {
        this.idSolucion = idSolucion;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public String toString() {
        return "Solucion{" + "idSolucion=" + idSolucion + ", idProducto=" + idProducto + '}';
    }
    
    
}
