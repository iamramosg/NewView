/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.optik.controller;

import org.python.util.PythonInterpreter;
import java.io.File;
import org.python.core.Py;
import org.python.core.PyObject;

/**
 *
 * @author iamra
 */
public class ControllerRegresion {

    public static void callPythonScript(String x, String y) {

        // crea un objeto PythonInterpreter
        PythonInterpreter interpreter = new PythonInterpreter();

        // carga el archivo diabetes.csv en el objeto File
        File file = new File("E:/regresionLineal/diabetes.csv");

        // especifica la ruta del archivo diabetes.csv en el script de Python
        String script = "import pandas as pd\n"
                + "df = pd.read_csv('" + file.getAbsolutePath() + "')\n"
                + "df = df[['preg', 'plas', 'pres', 'skin', 'insu', 'mass', 'pedi', 'age']]\n"
                + "x = '" + x + "'\n"
                + "y = '" + y + "'\n"
                + "df[[x,y]]\n"
                + "x_mean = df[x].mean()\n"
                + "y_mean = df[y].mean()\n"
                + "df['X_dev'] = df[x].apply(lambda x: x-x_mean)\n"
                + "df['Y_dev'] = df[y].apply(lambda y:y-y_mean)\n"
                + "x_dev_sq = df['X_dev'].apply(lambda x: x**2).sum()\n"
                + "y_dev_sq = df['Y_dev'].apply(lambda y: y**2).sum()\n"
                + "xy_dev = (df['X_dev']*df['Y_dev']).sum()\n"
                + "b1 = xy_dev / x_dev_sq\n"
                + "b0 = y_mean - b1 * x_mean\n"
                + "print('El coeficiente b0 es ' + str(b0))\n"
                + "print('El coeficiente b1 es ' + str(b1))\n"
                + "print('la ecuacion de regresion lineal es Y = ' + str(b0) + ' + ' + str(b1) + ' *X')";

        // ejecuta el script de Python
        interpreter.exec(script);
    }

}
