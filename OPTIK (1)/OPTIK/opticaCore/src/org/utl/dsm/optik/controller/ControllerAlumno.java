/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import org.utl.dsm.optik.db.ConexionMongoDB;
import org.utl.dsm.optik.model.Alumno;
import org.utl.dsm.optik.model.Evaluacion;
import java.util.Date;
import java.util.TimeZone;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCursor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author iamra
 */
public class ControllerAlumno {

    public void insertar(Evaluacion e) throws Exception {
        MongoClient mongo = null;
        try {
            ConexionMongoDB conexionMongoDB = new ConexionMongoDB();
            mongo = conexionMongoDB.open();
            String dbName = "escuela";
            String collectionName = "evaluacion";

            MongoDatabase database = mongo.getDatabase(dbName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = dateFormat.parse(e.getFecha());

            Date fechaGabriel = dateFormat.parse("2003-02-01");
            Date fechaLizbeth = dateFormat.parse("2002-10-04");
            Date fechaAngel = dateFormat.parse("2003-12-06");

            String comentario = e.getComentario();
            Document evaluacion = null;

            if (comentario != null && !comentario.isEmpty()) {
                evaluacion = new Document("materia", e.getMateria())
                        .append("calificacion", e.getCalificacion())
                        .append("fecha", fecha)
                        .append("comentario", comentario);
            } else {
                evaluacion = new Document("materia", e.getMateria())
                        .append("calificacion", e.getCalificacion())
                        .append("fecha", fecha);
            }

            Document Gabriel = new Document("nombre", "Gabriel")
                    .append("fechaNacimiento", fechaGabriel)
                    .append("matricula", 21000006);
            Document lizbeth = new Document("nombre", "Lizbeth")
                    .append("fechaNacimiento", fechaLizbeth)
                    .append("matricula", 21000012);
            Document angel = new Document("nombre", "Ángel")
                    .append("fechaNacimiento", fechaAngel)
                    .append("matricula", 21000381);

            if (e.getSeleccion() == 21000006) {
                evaluacion.append("alumno", Gabriel);
                collection.insertOne(evaluacion);
                //JOptionPane.showInputDialog(evaluacion.toJson());
            } else if (e.getSeleccion() == 21000012) {
                evaluacion.append("alumno", lizbeth);
                collection.insertOne(evaluacion);
            } else if (e.getSeleccion() == 21000381) {
                evaluacion.append("alumno", angel);
                collection.insertOne(evaluacion);
            }
            //collection.insertOne(alumnoDoc);
            //JOptionPane.showMessageDialog(null, "Se insertó correctamente el alumno en MongoDB");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar el cliente en MongoDB: " + ex.getMessage());
        } finally {
            if (mongo != null) {
                mongo.close();
            }
        }
    }

    public List<Alumno> buscarAlumnosPorMatricula(String matricula) {
        ConexionMongoDB conexion = new ConexionMongoDB();
        int matriculaNum = Integer.parseInt(matricula);

        // Abrir la conexión con MongoDB
        MongoClient mongoClient = conexion.open();

        // Obtener la base de datos "escuela"
        MongoDatabase database = mongoClient.getDatabase("escuela");

        // Obtener la colección "alumno"
        MongoCollection<Document> collection = database.getCollection("evaluacion");

        // Buscar los documentos donde la matrícula sea igual a la matrícula especificada
        Document filter = new Document("alumno.matricula", matriculaNum);
        List<Document> documentos = collection.find(filter).into(new ArrayList<>());

        // Cerrar la conexión
        mongoClient.close();

        // Crear una lista para almacenar los objetos Alumno
        List<Alumno> alumnos = new ArrayList<>();

        // Recorrer la lista de documentos y crear objetos Alumno a partir de ellos
        for (Document doc : documentos) {
            // Obtener los valores de las propiedades del documento
            String materia = doc.getString("materia");
            int calificacion = doc.getInteger("calificacion");
            Date fecha = doc.getDate("fecha");
            String comentario = doc.getString("comentario");

            // Convertir la fecha a una cadena de caracteres
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fechaStr = sdf.format(fecha);

            // Crear un objeto Evaluacion a partir del subdocumento "evaluacion"
            Document alumnoDoc = (Document) doc.get("alumno");
            String nombre = alumnoDoc.getString("nombre");
            Date fechaNacimiento = alumnoDoc.getDate("fechaNacimiento");
            int matricula2 = alumnoDoc.getInteger("matricula");

            // Convertir la fecha de nacimiento a una cadena de caracteres
            String fechaNacimientoStr = sdf.format(fechaNacimiento);

            Evaluacion evaluacion = new Evaluacion();
            evaluacion.setMateria(materia);
            evaluacion.setCalificacion(calificacion);
            evaluacion.setFecha(fechaStr);
            evaluacion.setComentario(comentario);

            // Crear un objeto Alumno con los valores obtenidos
            Alumno alumno = new Alumno();
            alumno.setNombre(nombre);
            alumno.setMatricula(matricula2);
            alumno.setFechaNacimiento(fechaNacimientoStr);
            alumno.setEvaluacion(evaluacion);

            // Agregar el objeto Alumno a la lista de alumnos
            alumnos.add(alumno);
        }

        return alumnos;
    }

}
