/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;

/**
 *
 * @author Gabriel
 */
public class PresupuestoLenteContacto {
    private int idPresupuestoLentesContacto;
    private ExamenVista idExamenVista;
    private LenteContacto idLenteContacto;
    private String clave; 

    public PresupuestoLenteContacto(ExamenVista idExamenVista, LenteContacto idLenteContacto, String clave) {
        this.idExamenVista = idExamenVista;
        this.idLenteContacto = idLenteContacto;
        this.clave = clave;
    }

    public PresupuestoLenteContacto(int idPresupuestoLentesContacto, ExamenVista idExamenVista, LenteContacto idLenteContacto, String clave) {
        this.idPresupuestoLentesContacto = idPresupuestoLentesContacto;
        this.idExamenVista = idExamenVista;
        this.idLenteContacto = idLenteContacto;
        this.clave = clave;
    }

    public int getIdPresupuestoLentesContacto() {
        return idPresupuestoLentesContacto;
    }

    public void setIdPresupuestoLentesContacto(int idPresupuestoLentesContacto) {
        this.idPresupuestoLentesContacto = idPresupuestoLentesContacto;
    }

    public ExamenVista getIdExamenVista() {
        return idExamenVista;
    }

    public void setIdExamenVista(ExamenVista idExamenVista) {
        this.idExamenVista = idExamenVista;
    }

    public LenteContacto getIdLenteContacto() {
        return idLenteContacto;
    }

    public void setIdLenteContacto(LenteContacto idLenteContacto) {
        this.idLenteContacto = idLenteContacto;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public String toString() {
        return "PresupuestoLenteContacto{" + "idPresupuestoLentesContacto=" + idPresupuestoLentesContacto + ", idExamenVista=" + idExamenVista + ", idLenteContacto=" + idLenteContacto + ", clave=" + clave + '}';
    }
    
    
}
