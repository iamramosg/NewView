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
    private Producto producto;
    private int keratometria;
    private String fotografia;

    public LenteContacto() {
    }

    public LenteContacto(Producto producto, int keratometria, String fotografia) {
        this.producto = producto;
        this.keratometria = keratometria;
        this.fotografia = fotografia;
    }

    public LenteContacto(int idLenteContacto, Producto producto, int keratometria, String fotografia) {
        this.idLenteContacto = idLenteContacto;
        this.producto = producto;
        this.keratometria = keratometria;
        this.fotografia = fotografia;
    }

    public int getIdLenteContacto() {
        return idLenteContacto;
    }

    public void setIdLenteContacto(int idLenteContacto) {
        this.idLenteContacto = idLenteContacto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
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
        return "LenteContacto{" + "idLenteContacto=" + idLenteContacto + ", producto=" + producto + ", keratometria=" + keratometria + ", fotografia=" + fotografia + '}';
    }
    
    
    
    
}
