/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.db;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author iamra
 */
public class ConexionMongoDB {

    public MongoClient open() {
        MongoClient mongo = null;
        String Servidor = "localhost";
        int puerto = 27017;
        try{
            mongo = new MongoClient(Servidor,puerto);
            List<String> NombreDB = mongo.getDatabaseNames();
            //JOptionPane.showMessageDialog(null, "Se conecto a mongoDB, las bases disponibles son"+NombreDB.toString());
        }catch (MongoException e){
            JOptionPane.showMessageDialog(null, "error en la conexion" + e.toString());
        }
        return mongo;

    }
}
