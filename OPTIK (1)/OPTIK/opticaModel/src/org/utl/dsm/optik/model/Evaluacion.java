/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;

/**
 *
 * @author iamra
 */
public class Evaluacion {
    private int calificacion;
    private String fecha;
    private String comentario;
    private String materia;
    private int seleccion;

    public Evaluacion() {
    }

    public Evaluacion(int calificacion, String fecha, String materia, int seleccion) {
        this.calificacion = calificacion;
        this.fecha = fecha;
        this.materia = materia;
        this.seleccion = seleccion;
    }

    public Evaluacion(int calificacion, String fecha, String comentario, String materia, int seleccion) {
        this.calificacion = calificacion;
        this.fecha = fecha;
        this.comentario = comentario;
        this.materia = materia;
        this.seleccion = seleccion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public int getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(int seleccion) {
        this.seleccion = seleccion;
    }

    @Override
    public String toString() {
        return "Evaluacion{" + "calificacion=" + calificacion + ", fecha=" + fecha + ", comentario=" + comentario + ", materia=" + materia + ", seleccion=" + seleccion + '}';
    }
    
    
}