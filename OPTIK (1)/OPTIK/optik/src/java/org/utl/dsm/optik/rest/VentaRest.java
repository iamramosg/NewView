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
import org.utl.dsm.optik.controller.ControllerVenta;
import org.utl.dsm.optik.model.Producto;
import org.utl.dsm.optik.model.DetalleVentaProducto;

/**
 *
 * @author iamra
 */
@Path("restoptik")
public class VentaRest extends Application{
    @Path("getAllProducto")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducto(@FormParam("estatus") @DefaultValue("") String estatus){
        String out = "";
        Gson gson = new Gson();
        try{
            ControllerVenta objVE = new ControllerVenta();
            List<Producto> productos;
            productos = objVE.getAll(estatus);
            out = gson.toJson(productos);
        }catch(Exception ex){
            out="{\"error\":" + ex.toString()+"}";
            
        }
        return Response.status(Response.Status.OK).entity(out).build();
        
    }
    @Path("validarCompra")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response validarCompra(String detalleVentaProducto){
//        Gson gson = new Gson();
//        DetalleVentaProducto dvp = new DetalleVentaProducto();
//        dvp = gson.fromJson(detalleVentaProducto, DetalleVentaProducto.class);
        ControllerVenta objVe = new ControllerVenta();
//        boolean resultado = objVe.generarVentaP(dvp);
        DetalleVentaProducto dvp = new Gson().fromJson(detalleVentaProducto, DetalleVentaProducto.class);

        boolean resultado = objVe.generarVentaP(dvp);
        
        if(resultado) {
            return Response.status(Response.Status.OK).entity("Transacción exitosa").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Transacción fallida").build();
        }
    }
}
