
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
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.utl.dsm.optik.controller.ControllerArmazon;
import org.utl.dsm.optik.model.Armazon;

@Path("restoptik")
public class ArmazonRest extends Application{
    @Path("guardarArmazon")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertarArmazon(@FormParam("datosArmazon") @DefaultValue("") String datosArmazon) {
        Gson gson = new Gson();
        Armazon arm = new Armazon();
        arm = gson.fromJson(datosArmazon, Armazon.class);
        ControllerArmazon objCA = new ControllerArmazon();
        try {
            objCA.insertar(arm);
        } catch (Exception ex) {
            String out = "{\"error\":" + ex.toString()+"}";
            Logger.getLogger(ArmazonRest.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        String out = gson.toJson(arm);

        return Response.status(Response.Status.CREATED).entity(out).build();
    }
    
    @Path("updateArmazon")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateArmazon(@FormParam ("datosArmazon") @DefaultValue ("") String datosArmazon){
        Gson gson = new Gson();
        Armazon armazon = new Armazon();
        armazon = gson.fromJson(datosArmazon, Armazon.class);
        ControllerArmazon objCA = new ControllerArmazon();
        String out = "";
        try {
            objCA.actualizar(armazon);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
        }
        out = gson.toJson(armazon);
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("getAllArmazon")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllArmazon(@FormParam("estatus") @DefaultValue("1") String estatus) {
        String out = "";
        Gson gson = new Gson();
        try {
            ControllerArmazon objCA = new ControllerArmazon();
            List<Armazon> armazones;
            armazones=objCA.getAll(estatus);
            out = gson.toJson(armazones);

        } catch (Exception ex) {
            out = "{\"error\": \""+ex.toString()+"\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("eliminarArmazon")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarArmazon(@FormParam ("idArmazon") @DefaultValue ("") String idArmazon){
        Gson gson = new Gson();
        ControllerArmazon objCA = new ControllerArmazon();
        String out = "";
        try {
            objCA.eliminar(idArmazon);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
        }
        out = gson.toJson(idArmazon);
        return Response.status(Response.Status.OK).entity(out).build();
    }
    @Path("agregarArmazon")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarArmazon(@FormParam ("idArmazon") @DefaultValue ("") String idArmazon){
        Gson gson = new Gson();
        ControllerArmazon objCA = new ControllerArmazon();
        String out = "";
        try {
            objCA.agregar(idArmazon);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString()+"}";
        }
        out = gson.toJson(idArmazon);
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
