/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.rest;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
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
import org.utl.dsm.optik.controller.ControllerAcceso;
import org.utl.dsm.optik.model.Empleado;
import org.utl.dsm.optik.model.Usuario;
/**
 *
 * @author iamra
 */
@Path("restoptik")
public class AccesoRest extends Application{
    
    @Path("acceder")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response acceder(@FormParam("datosAcceso") @DefaultValue("") String datosAcceso){
        Gson gson = new Gson();
        Usuario u = new Usuario();
        Empleado emp = new Empleado();
        u = gson.fromJson(datosAcceso, Usuario.class);
        ControllerAcceso objCa = new ControllerAcceso();
        try{
            emp = objCa.entrar(u);
            emp.getUsuario().setLastToken(); //1
            objCa.guardarToken(emp); //2
        }catch(Exception ex){
            Logger.getLogger(AccesoRest.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        String out = gson.toJson(emp);
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("in")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response logIn(@FormParam("datosAcceso") @DefaultValue("") String datosAcceso) throws Exception{
        String out = null;
        Empleado empleado = null;
        Usuario usuario = null;
        ControllerAcceso ca = null;
        Gson gson = new Gson();
        try
        {
            usuario = gson.fromJson(datosAcceso, Usuario.class);
            
            ca = new ControllerAcceso();
            
            empleado = ca.entrar(usuario);
            
            if(empleado!=null){
                empleado.getUsuario().setLastToken();
                ca.guardarToken(empleado);
                out = gson.toJson(empleado);
            }else
                out = """
                      {"error":"Acceso no concedido"}
                      """;
        }
        catch(JsonParseException jpe)
        {
            out = """
                  {"error":"Formato de datos no valido"}
                  """;
            jpe.printStackTrace();
        }
        catch(Exception e)
        {
            out = """
                  {"error":"Acceso no concedido"}
                  """;
            e.printStackTrace();
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("out")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response logOut(@FormParam("empleado") @DefaultValue("") String e)  throws Exception{
        String out = null;
        Empleado empleado = null;
        ControllerAcceso ca = null;
        Gson gson = new Gson();
        
        try{
            empleado=gson.fromJson(e, Empleado.class);
            ca = new ControllerAcceso();
            if(ca.eliminarToken(empleado))
            {
                out = """
                      {"ok":"Eliminacion de token correcta"}
                      """;
            }else{
                out = """
                      {"error":"Eliminacion de token no realizada"}
                      """;
            }
        }catch (JsonParseException jpe){
            out = """
                  {"error":"formato de datos no valido."}
                  """;
            jpe.printStackTrace();
        }catch(Exception ex){
            out = """
                  {"error":"Eliminacion no concedida"}
                  """;
            ex.printStackTrace();
        }
        return Response.status(Response.Status.OK).entity(out).build();
                
    }
    
}
