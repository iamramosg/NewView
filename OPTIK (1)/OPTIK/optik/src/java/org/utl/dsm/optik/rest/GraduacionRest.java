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
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.utl.dsm.optik.controller.ControllerGraduacionLentes;
import org.utl.dsm.optik.model.Graduacion;

@Path("restoptik")
public class GraduacionRest extends Application{
    @Path("guardarGraduacion")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertGraduacion(@FormParam("datosGraduacion") @DefaultValue("") String datosGraduacion) {
        Gson gson = new Gson();
        Graduacion g = new Graduacion();
        g = gson.fromJson(datosGraduacion, Graduacion.class);
        ControllerGraduacionLentes objCGL = new ControllerGraduacionLentes();
        try {
            objCGL.insertar(g);
        } catch (Exception ex) {
            Logger.getLogger(GraduacionRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        String out = gson.toJson(g);

        return Response.status(Response.Status.CREATED).entity(out).build();
    }
    
    @Path("updateGraduacion")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateGraduacion(@FormParam ("datosGraduacion") @DefaultValue ("") String datosGraduacion){
        Gson gson = new Gson();
        Graduacion g = new Graduacion();
        g = gson.fromJson(datosGraduacion, Graduacion.class);
        ControllerGraduacionLentes objCGL = new ControllerGraduacionLentes();
        String out = "";
        try {
            objCGL.actualizar(g);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
        }
        out = gson.toJson(g);
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("getAllGraduacion")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGraduacion(@FormParam("estatus") @DefaultValue("1") String estatus) {
        String out = "";
        Gson gson = new Gson();
        try {
            ControllerGraduacionLentes objCGL = new ControllerGraduacionLentes();
            List<Graduacion> graduaciones;
            graduaciones = objCGL.getAll(estatus);
            out = gson.toJson(graduaciones);

        } catch (Exception ex) {
            out = "{\"error\": \""+ex.toString()+"\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("eliminarGraduacion")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarGraduacion(@FormParam ("idGraduacion") @DefaultValue ("") String idGraduacion){
        Gson gson = new Gson();
        ControllerGraduacionLentes objG = new ControllerGraduacionLentes();
        String out = "";
        try {
            objG.eliminar(idGraduacion);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
        }
        out = gson.toJson(idGraduacion);
        return Response.status(Response.Status.OK).entity(out).build();
    }
    @Path("agregarGraduacion")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarArmazon(@FormParam ("idGraduacion") @DefaultValue ("") String idGraduacion){
        Gson gson = new Gson();
        ControllerGraduacionLentes objG = new ControllerGraduacionLentes();
        String out = "";
        try {
            objG.agregar(idGraduacion);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
        }
        out = gson.toJson(idGraduacion);
        return Response.status(Response.Status.OK).entity(out).build();
    }
}