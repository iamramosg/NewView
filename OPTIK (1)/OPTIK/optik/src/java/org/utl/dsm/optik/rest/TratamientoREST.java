/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.utl.dsm.optik.controller.ControllerTratamiento;
import org.utl.dsm.optik.model.Tratamiento;

/**
 *
 * @author iamra
 */
@Path("restoptik")
public class TratamientoREST extends Application {

    @Path("getAllTratamiento")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTratamiento(@FormParam("estatus") @DefaultValue("1") String estatus) {
        String out = "";
        Gson gson = new Gson();
        try {
            ControllerTratamiento objCT = new ControllerTratamiento();
            List<Tratamiento> tratamientos;
            tratamientos = objCT.getAll(estatus);
            out = gson.toJson(tratamientos); //Convertimos la lista de empleados a un json
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString() + "}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
