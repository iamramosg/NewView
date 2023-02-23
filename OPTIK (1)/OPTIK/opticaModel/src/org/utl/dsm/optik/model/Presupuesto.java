/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;

/**
 *
 * @author Gabriel
 */
public class Presupuesto {
    private int idPresupuesto;
    private ExamenVista idExamenVista;
    private String clave;

    public Presupuesto() {
    }

    public Presupuesto(ExamenVista idExamenVista, String clave) {
        this.idExamenVista = idExamenVista;
        this.clave = clave;
    }

    public Presupuesto(int idPresupuesto, ExamenVista idExamenVista, String clave) {
        this.idPresupuesto = idPresupuesto;
        this.idExamenVista = idExamenVista;
        this.clave = clave;
    }

    public int getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(int idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public ExamenVista getIdExamenVista() {
        return idExamenVista;
    }

    public void setIdExamenVista(ExamenVista idExamenVista) {
        this.idExamenVista = idExamenVista;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public String toString() {
        return "Presupuesto{" + "idPresupuesto=" + idPresupuesto + ", idExamenVista=" + idExamenVista + ", clave=" + clave + '}';
    }
    
    
 
}
