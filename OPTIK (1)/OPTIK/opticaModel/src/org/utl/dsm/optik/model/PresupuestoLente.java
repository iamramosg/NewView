/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;

/**
 *
 * @author Gabriel
 */
public class PresupuestoLente {
    private int idPresupuestoLentes;
    private Presupuesto idPresupuesto;    
    private int alturaOblea;
    private TipoMica idTipoMica;
    private Material idMaterial;
    private Armazon idArmazon;  

    public PresupuestoLente() {
    }

    public PresupuestoLente(Presupuesto idPresupuesto, int alturaOblea, TipoMica idTipoMica, Material idMaterial, Armazon idArmazon) {
        this.idPresupuesto = idPresupuesto;
        this.alturaOblea = alturaOblea;
        this.idTipoMica = idTipoMica;
        this.idMaterial = idMaterial;
        this.idArmazon = idArmazon;
    }

    public PresupuestoLente(int idPresupuestoLentes, Presupuesto idPresupuesto, int alturaOblea, TipoMica idTipoMica, Material idMaterial, Armazon idArmazon) {
        this.idPresupuestoLentes = idPresupuestoLentes;
        this.idPresupuesto = idPresupuesto;
        this.alturaOblea = alturaOblea;
        this.idTipoMica = idTipoMica;
        this.idMaterial = idMaterial;
        this.idArmazon = idArmazon;
    }

    public int getIdPresupuestoLentes() {
        return idPresupuestoLentes;
    }

    public void setIdPresupuestoLentes(int idPresupuestoLentes) {
        this.idPresupuestoLentes = idPresupuestoLentes;
    }

    public Presupuesto getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(Presupuesto idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public int getAlturaOblea() {
        return alturaOblea;
    }

    public void setAlturaOblea(int alturaOblea) {
        this.alturaOblea = alturaOblea;
    }

    public TipoMica getIdTipoMica() {
        return idTipoMica;
    }

    public void setIdTipoMica(TipoMica idTipoMica) {
        this.idTipoMica = idTipoMica;
    }

    public Material getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Material idMaterial) {
        this.idMaterial = idMaterial;
    }

    public Armazon getIdArmazon() {
        return idArmazon;
    }

    public void setIdArmazon(Armazon idArmazon) {
        this.idArmazon = idArmazon;
    }

    @Override
    public String toString() {
        return "PresupuestoLente{" + "idPresupuestoLentes=" + idPresupuestoLentes + ", idPresupuesto=" + idPresupuesto + ", alturaOblea=" + alturaOblea + ", idTipoMica=" + idTipoMica + ", idMaterial=" + idMaterial + ", idArmazon=" + idArmazon + '}';
    }
    
    
}
