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
    private LenteContacto LenteContacto;
    private String clave; 
    private Presupuesto presupuesto;

    public PresupuestoLenteContacto() {
    }

    public PresupuestoLenteContacto(LenteContacto LenteContacto, String clave, Presupuesto presupuesto) {
        this.LenteContacto = LenteContacto;
        this.clave = clave;
        this.presupuesto = presupuesto;
    }

    public PresupuestoLenteContacto(int idPresupuestoLentesContacto, LenteContacto LenteContacto, String clave, Presupuesto presupuesto) {
        this.idPresupuestoLentesContacto = idPresupuestoLentesContacto;
        this.LenteContacto = LenteContacto;
        this.clave = clave;
        this.presupuesto = presupuesto;
    }
    
    

    public int getIdPresupuestoLentesContacto() {
        return idPresupuestoLentesContacto;
    }

    public void setIdPresupuestoLentesContacto(int idPresupuestoLentesContacto) {
        this.idPresupuestoLentesContacto = idPresupuestoLentesContacto;
    }

    public LenteContacto getLenteContacto() {
        return LenteContacto;
    }

    public void setLenteContacto(LenteContacto LenteContacto) {
        this.LenteContacto = LenteContacto;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }

    @Override
    public String toString() {
        return "PresupuestoLenteContacto{" + "idPresupuestoLentesContacto=" + idPresupuestoLentesContacto + ", LenteContacto=" + LenteContacto + ", clave=" + clave + ", presupuesto=" + presupuesto + '}';
    }
    
    
}