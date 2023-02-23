/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.utl.dsm.optik.controller.ControllerEmpleado;
import org.utl.dsm.optik.model.Empleado;

/**
 *
 * @author Gabriel
 */
@Path("restoptik")
public class EmpleadoREST extends Application{
    @Path("insertEmpleado")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertEmpleado(@FormParam ("datosEmpleado") @DefaultValue ("") String datosEmpleado){
        Gson gson = new Gson();
        Empleado empleado = new Empleado();
        empleado = gson.fromJson(datosEmpleado, Empleado.class);
        ControllerEmpleado objCE = new ControllerEmpleado();
        String out = "";
        try {
            objCE.insertar(empleado);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
        }
        out = gson.toJson(empleado);
        return Response.status(Response.Status.OK).entity(out).build();
    }
    @Path("updateEmpleado")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmpleado(@FormParam ("datosEmpleado") @DefaultValue ("") String datosEmpleado){
        Gson gson = new Gson();
        Empleado empleado = new Empleado();
        empleado = gson.fromJson(datosEmpleado, Empleado.class);
        ControllerEmpleado objCE = new ControllerEmpleado();
        String out = "";
        try {
            objCE.actualizar(empleado);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
        }
        out = gson.toJson(empleado);
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("getAllEmpleado")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmpleado(@FormParam("estatus") @DefaultValue("1") String estatus){
        String out = "";
        Gson gson = new Gson();
        try{
            ControllerEmpleado objCE = new ControllerEmpleado();
            List<Empleado> empleados;
            empleados = objCE.getAll(estatus);
            out = gson.toJson(empleados);
        }catch(Exception ex){
            out="{\"error\":" + ex.toString()+"}";
            
        }
        return Response.status(Response.Status.OK).entity(out).build();
        
    }
    @Path("eliminarEmpleado")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarEmpleado(@FormParam ("datosEmpleado") @DefaultValue ("") String datosEmpleado){
        Gson gson = new Gson();
        Empleado empleado = new Empleado();
        empleado = gson.fromJson(datosEmpleado, Empleado.class);
        ControllerEmpleado objCE = new ControllerEmpleado();
        String out = "";
        try {
            objCE.eliminar(empleado);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
        }
        out = gson.toJson(empleado);
        return Response.status(Response.Status.OK).entity(out).build();
    }
    @Path("activarEmpleado")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response activarEmpleado(@FormParam ("datosEmpleado") @DefaultValue ("") String datosEmpleado){
        Gson gson = new Gson();
        Empleado empleado = new Empleado();
        empleado = gson.fromJson(datosEmpleado, Empleado.class);
        ControllerEmpleado objCE = new ControllerEmpleado();
        String out = "";
        try {
            objCE.activar(empleado);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
        }
        out = gson.toJson(empleado);
        return Response.status(Response.Status.OK).entity(out).build();
    }
    @Path("saludar")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response saludar(){
        String out = """
                     {"result":"Hola desde un rest"}
                     """;
        return Response.status(Response.Status.OK).entity(out).build();
    }
}

