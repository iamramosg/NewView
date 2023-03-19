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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.utl.dsm.optik.controller.ControllerAlumno;
import org.utl.dsm.optik.model.Evaluacion;
import org.utl.dsm.optik.model.Alumno;

/**
 *
 * @author iamra
 */
@Path("restoptik")
public class AlumnoRest extends Application {

    @Path("insertEvaluacion")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertEvaluacion(@FormParam("datosEvaluacion") @DefaultValue("") String datosEvaluacion) {
        Gson gson = new Gson();
        Evaluacion evaluacion = new Evaluacion();
        evaluacion = gson.fromJson(datosEvaluacion, Evaluacion.class);
        ControllerAlumno objEv = new ControllerAlumno();
        String out = "";
        try {
            objEv.insertar(evaluacion);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString() + "}";
        }
        out = gson.toJson(evaluacion);
        return Response.status(Response.Status.OK).entity(out).build();
    }

    @Path("getAllAlumno")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAlumno(@FormParam("matricula") @DefaultValue("") String matricula) {
        String out = "";
        Gson gson = new Gson();
        try {
            ControllerAlumno objAl = new ControllerAlumno();
            out = gson.toJson(objAl.buscarAlumnosPorMatricula(matricula));
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString() + "}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();

        }
        return Response.status(Response.Status.OK).entity(out).build();

    }

}
