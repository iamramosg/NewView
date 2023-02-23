/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;

/**
 *
 * @author Gabriel
 */
public class LenteContacto {
    private int idLenteContacto;
    private Producto idProducto;
    private int keratometria;
    private String fotografia;

    public LenteContacto() {
    }
    
    public LenteContacto(Producto idProducto, int keratometria, String fotografia) {
        this.idProducto = idProducto;
        this.keratometria = keratometria;
        this.fotografia = fotografia;
    }

    public LenteContacto(int idLenteContacto, Producto idProducto, int keratometria, String fotografia) {
        this.idLenteContacto = idLenteContacto;
        this.idProducto = idProducto;
        this.keratometria = keratometria;
        this.fotografia = fotografia;
    }

    public int getIdLenteContacto() {
        return idLenteContacto;
    }

    public void setIdLenteContacto(int idLenteContacto) {
        this.idLenteContacto = idLenteContacto;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public int getKeratometria() {
        return keratometria;
    }

    public void setKeratometria(int keratometria) {
        this.keratometria = keratometria;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    @Override
    public String toString() {
        return "LenteContacto{" + "idLenteContacto=" + idLenteContacto + ", idProducto=" + idProducto + ", keratometria=" + keratometria + ", fotografia=" + fotografia + '}';
    }
    
    
    
    
}
