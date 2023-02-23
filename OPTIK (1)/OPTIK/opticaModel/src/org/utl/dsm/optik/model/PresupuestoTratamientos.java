/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;

/**
 *
 * @author Gabriel
 * Tengo dudas en los nombres de las declaraciones
 */
public class PresupuestoTratamientos {
    private int idPresupuestoLentes;
    private Tratamiento idTratamiento;

    public PresupuestoTratamientos() {
    }

    public PresupuestoTratamientos(Tratamiento idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public PresupuestoTratamientos(int idPresupuestoLentes, Tratamiento idTratamiento) {
        this.idPresupuestoLentes = idPresupuestoLentes;
        this.idTratamiento = idTratamiento;
    }

    public int getIdPresupuestoLentes() {
        return idPresupuestoLentes;
    }

    public void setIdPresupuestoLentes(int idPresupuestoLentes) {
        this.idPresupuestoLentes = idPresupuestoLentes;
    }

    public Tratamiento getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(Tratamiento idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    @Override
    public String toString() {
        return "PresupuestoTratamientos{" + "idPresupuestoLentes=" + idPresupuestoLentes + ", idTratamiento=" + idTratamiento + '}';
    }
    
    
}
