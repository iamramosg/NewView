/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.model;

/**
 *
 * @author iamra
 */
public class Alumno {
    private String nombre;
    private String fechaNacimiento;
    private int matricula;
    private Evaluacion evaluacion;

    public Alumno() {
    }

    public Alumno(String nombre, String fechaNacimiento, int matricula) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.matricula = matricula;
    }

    public Alumno(String nombre, String fechaNacimiento, int matricula, Evaluacion evaluacion) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.matricula = matricula;
        this.evaluacion = evaluacion;
    }

    public Alumno(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    @Override
    public String toString() {
        return "Alumno{" + "nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + ", matricula=" + matricula + evaluacion.toString() + '}';
    }

    
    
}
