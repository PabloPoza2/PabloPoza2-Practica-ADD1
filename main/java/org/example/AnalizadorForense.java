package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

//URL del repositorio: https://github.com/PabloPoza2/PabloPoza2-Practica-ADD1.git

public class AnalizadorForense {
    public static void main(String[] args) {
        File dirSalida = new File("salida");
        if (!dirSalida.exists()) {
            boolean creado = dirSalida.mkdir();
            if (creado) {
                System.out.println("Directorio 'salida' creado correctamente.");
            }
        }
        try {
            for (String linea : Files.readAllLines(Paths.get("datos/operaciones.txt"))) {
                System.out.println("  > " + linea);
            }
        } catch (IOException e) {
            System.err.println("Error leyendo el TXT: " + e.getMessage());
        }
        try (RandomAccessFile raf = new RandomAccessFile("datos/telemetria.dat", "rw")){
            int idPuerta = raf.read();
            boolean vulnerada = raf.readBoolean();
            System.out.println("Puerta ID:" + idPuerta + vulnerada);
            if (vulnerada) {
                System.out.println("INCIDENTE ALERTA: Acceso no autorizado");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error");
        }
        try (PrintWriter informeForense = new PrintWriter(new FileWriter("salida/ informeForense.txt"))){
            informeForense.println("Acceso no autorizado");
        } catch (IOException e) {
            System.out.println("Error al escribir el informe");
        }
        try (RandomAccessFile rafOut = new RandomAccessFile("salida/anomalias.dat", "rw")) {
            rafOut.writeInt(404);
            rafOut.writeBoolean(true);
        } catch (IOException e) {
            System.err.println("Error escribiendo binario de salida: " + e.getMessage());
        }

        try (PrintWriter jsonOut = new PrintWriter(new FileWriter("salida/afectados.json"))) {
            jsonOut.println("Incidente_detectado:" + "Acceso no autorizado");
            jsonOut.println("Elemento_afectado" + "Puerta 404");
        } catch (IOException e) {
            System.err.println("Error escribiendo JSON de salida: " + e.getMessage());
        }
        System.out.println("Análisis completado");
    }
}