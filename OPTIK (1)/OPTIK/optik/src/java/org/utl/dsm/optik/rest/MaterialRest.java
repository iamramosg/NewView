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
import org.utl.dsm.optik.controller.ControllerMaterial;
import org.utl.dsm.optik.controller.ControllerAcceso;
import org.utl.dsm.optik.model.Material;

/**
 *
 * @author iamra
 */
@Path("restoptik")
public class MaterialRest extends Application {

    @Path("insertarMaterial")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertMaterial(@FormParam("datosMaterial") @DefaultValue("") String datosMaterial,
            @FormParam("lastToken") @DefaultValue("") String lastToken) {
        try {
            if (!new ControllerAcceso().validarToken(lastToken)) {
                String Out = "{\"error\":'Acceso no valido'}";
                return Response.status(Response.Status.BAD_REQUEST).entity(Out).build();
            }

            Gson gson = new Gson();
            Material material = new Material();
            material = gson.fromJson(datosMaterial, Material.class);
            ControllerMaterial objMa = new ControllerMaterial();
            String out = "";
            try {
                objMa.insertar(material);
            } catch (Exception ex) {
                out = "{\"error\":" + ex.toString() + "}";
            }
            out = gson.toJson(material);
            return Response.status(Response.Status.OK).entity(out).build();
        } catch (Exception ex) {
            Logger.getLogger(MaterialRest.class.getName()).log(Level.SEVERE, null, ex);
            String Out = "{\"error\":'Acceso no valido'}";
            return Response.status(Response.Status.BAD_REQUEST).entity(Out).build();
        }
    }

    @Path("updateMaterial")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmpleado(@FormParam("datosMaterial") @DefaultValue("") String datosMaterial,
            @FormParam("lastToken") @DefaultValue("") String lastToken) {
        try {
            if (!new ControllerAcceso().validarToken(lastToken)) {
                String Out = "{\"error\":'Acceso no valido'}";
                return Response.status(Response.Status.BAD_REQUEST).entity(Out).build();
            }
            Gson gson = new Gson();
            Material material = new Material();
            material = gson.fromJson(datosMaterial, Material.class);
            ControllerMaterial objM = new ControllerMaterial();
            String out = "";
            try {
                objM.actualizar(material);
            } catch (Exception ex) {
                out = "{\"error\":" + ex.toString() + "}";
            }
            out = gson.toJson(material);
            return Response.status(Response.Status.OK).entity(out).build();
        } catch (Exception ex) {
            Logger.getLogger(MaterialRest.class.getName()).log(Level.SEVERE, null, ex);
            String Out = "{\"error\":'Acceso no valido'}";
            return Response.status(Response.Status.BAD_REQUEST).entity(Out).build();
        }
    }

    @Path("getAllMaterial")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMaterial(@FormParam("estatus") @DefaultValue("1") String estatus,
            @FormParam("lastToken") @DefaultValue("") String lastToken) {
        try {
            if (!new ControllerAcceso().validarToken(lastToken)) {
                String Out = "{\"error\":'Acceso no valido'}";
                return Response.status(Response.Status.BAD_REQUEST).entity(Out).build();
            }
            String out = "";
            Gson gson = new Gson();
            try {
                ControllerMaterial objM = new ControllerMaterial();
                List<Material> materiales;
                materiales = objM.getAll(estatus);
                out = gson.toJson(materiales);
            } catch (Exception ex) {
                out = "{\"error\":" + ex.toString() + "}";

            }
            return Response.status(Response.Status.OK).entity(out).build();
        } catch (Exception ex) {
            Logger.getLogger(MaterialRest.class.getName()).log(Level.SEVERE, null, ex);
            String Out = "{\"error\":'Acceso no valido'}";
            return Response.status(Response.Status.BAD_REQUEST).entity(Out).build();
        }
    }

    @Path("eliminarMaterial")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarMaterial(@FormParam("datosMaterial") @DefaultValue("") String datosMaterial) {
        Gson gson = new Gson();
        Material material = new Material();
        material = gson.fromJson(datosMaterial, Material.class);
        ControllerMaterial objM = new ControllerMaterial();
        String out = "";
        try {
            objM.eliminar(material);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString() + "}";
        }
        out = gson.toJson(material);
        return Response.status(Response.Status.OK).entity(out).build();
    }

    @Path("activarMaterial")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response activarMaterial(@FormParam("datosMaterial") @DefaultValue("") String datosMaterial) {
        Gson gson = new Gson();
        Material material = new Material();
        material = gson.fromJson(datosMaterial, Material.class);
        ControllerMaterial objM = new ControllerMaterial();
        String out = "";
        try {
            objM.activar(material);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString() + "}";
        }
        out = gson.toJson(material);
        return Response.status(Response.Status.OK).entity(out).build();
    }

    @Path("getAllM")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllM(@FormParam("estatus") @DefaultValue("1") String estatus) {
        String out = "";
        Gson gson = new Gson();
        try {
            ControllerMaterial objCA = new ControllerMaterial();
            List<Material> materiales;
            materiales = objCA.getAll(estatus);
            out = gson.toJson(materiales);

        } catch (Exception ex) {
            out = "{\"error\": \"" + ex.toString() + "\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }

}
