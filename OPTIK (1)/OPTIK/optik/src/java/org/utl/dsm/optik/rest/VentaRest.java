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
import org.utl.dsm.optik.controller.ControllerVentaLC;
import org.utl.dsm.optik.model.Cliente;
import org.utl.dsm.optik.model.DetalleVentaPresupuesto;
import org.utl.dsm.optik.model.DetalleVentaPresupuestoLente;
import org.utl.dsm.optik.model.Producto;
import org.utl.dsm.optik.model.DetalleVentaProducto;
import org.utl.dsm.optik.model.ExamenVista;
import org.utl.dsm.optik.model.LenteContacto;

/**
 *
 * @author iamra
 */
@Path("restoptik")
public class VentaRest extends Application {

    @Path("getAllProducto")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducto(@FormParam("estatus") @DefaultValue("") String estatus) {
        String out = "";
        Gson gson = new Gson();
        try {
            ControllerVenta objVE = new ControllerVenta();
            List<Producto> productos;
            productos = objVE.getAll(estatus);
            out = gson.toJson(productos);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString() + "}";

        }
        return Response.status(Response.Status.OK).entity(out).build();

    }

    @Path("validarCompra")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response validarCompra(String detalleVentaProducto) {
//        Gson gson = new Gson();
//        DetalleVentaProducto dvp = new DetalleVentaProducto();
//        dvp = gson.fromJson(detalleVentaProducto, DetalleVentaProducto.class);
        ControllerVenta objVe = new ControllerVenta();
//        boolean resultado = objVe.generarVentaP(dvp);
        DetalleVentaProducto dvp = new Gson().fromJson(detalleVentaProducto, DetalleVentaProducto.class);

        boolean resultado = objVe.generarVentaP(dvp);

        if (resultado) {
            return Response.status(Response.Status.OK).entity("Transacción exitosa").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Transacción fallida").build();
        }
    }

    @Path("ventaProducto")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response generarVenta(@FormParam("datos") @DefaultValue("") String datosVenta) {
        Gson gson = new Gson();
        DetalleVentaProducto dvproducto = gson.fromJson(datosVenta, DetalleVentaProducto.class);

        ControllerVenta cv = new ControllerVenta();

        boolean r = cv.generarVentaP(dvproducto);
        String out = "";

        if (r) {
            out = """
                  {"result":"venta exitosa"}
                  """;
        } else {
            out = """
                  {"error":"ocurrio un error en la venta"}
                  """;
        }
        return Response.status(Response.Status.OK).entity(out).build();

    }

    @Path("ventaLC")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response generarVentaLC(@FormParam("datos") @DefaultValue("") String datosVentaLC) {
        Gson gson = new Gson();
        DetalleVentaPresupuesto dvPresupuesto = gson.fromJson(datosVentaLC, DetalleVentaPresupuesto.class);

        ControllerVentaLC cv = new ControllerVentaLC();

        boolean r = cv.generarVentaLC(dvPresupuesto);
        String out = "";

        if (r) {
            out = """
                  {"result":"venta presupuesto exitosa"}
                  """;
        } else {
            out = """
                  {"error":"ocurrio un error en la venta presupuesto"}
                  """;
        }
        return Response.status(Response.Status.OK).entity(out).build();

    }

    @Path("getAllCliente2")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCliente(@FormParam("filtro") @DefaultValue("") String filtro) {
        String out = "";
        Gson gson = new Gson();
        try {
            ControllerVentaLC objLC = new ControllerVentaLC();
            List<Cliente> clientes;
            clientes = objLC.getAll(filtro);
            out = gson.toJson(clientes);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString() + "}";

        }
        return Response.status(Response.Status.OK).entity(out).build();

    }

    @Path("getAllExamenVista")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllExamenVista(@FormParam("id") int id) {
        String out = "";
        Gson gson = new Gson();
        try {
            ControllerVentaLC objLC = new ControllerVentaLC();
            List<ExamenVista> examenVistas;
            examenVistas = objLC.getAllExamen(id);
            out = gson.toJson(examenVistas);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString() + "}";

        }
        return Response.status(Response.Status.OK).entity(out).build();

    }

    @Path("getAllLenteContacto")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLenteContacto() {
        String out = "";
        Gson gson = new Gson();
        try {
            ControllerVentaLC objLC = new ControllerVentaLC();
            List<LenteContacto> lenteContactos;
            lenteContactos = objLC.getAllLenteContacto();
            out = gson.toJson(lenteContactos);
        } catch (Exception ex) {
            out = "{\"error\":" + ex.toString() + "}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }

    @Path("ventaLente")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response generarVentaLente(@FormParam("datos") @DefaultValue("") String datosVentaLente) {
        Gson gson = new Gson();
        DetalleVentaPresupuestoLente dvPreLen = gson.fromJson(datosVentaLente, DetalleVentaPresupuestoLente.class);

        ControllerVentaLC cv = new ControllerVentaLC();

        boolean r = cv.generarVentaLentes(dvPreLen);
        String out = "";

        if (r) {
            out = """
                  {"result":"venta lente exitosa"}
                  """;
        } else {
            out = """
                  {"error":"ocurrio un error en la venta del lente"}
                  """;
        }
        return Response.status(Response.Status.OK).entity(out).build();

    }

}
