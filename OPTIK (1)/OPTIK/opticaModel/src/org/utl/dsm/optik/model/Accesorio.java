/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;

/**
 *
 * @author Gabriel
 */
public class Accesorio {
    private int idAccesorio;
    private Producto idProducto;

    public Accesorio() {
    }

    public Accesorio(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public Accesorio(int idAccesorio, Producto idProducto) {
        this.idAccesorio = idAccesorio;
        this.idProducto = idProducto;
    }

    public int getIdAccesorio() {
        return idAccesorio;
    }

    public void setIdAccesorio(int idAccesorio) {
        this.idAccesorio = idAccesorio;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public String toString() {
        return "Accesorio{" + "idAccesorio=" + idAccesorio + ", idProducto=" + idProducto + '}';
    }
    
    
}
