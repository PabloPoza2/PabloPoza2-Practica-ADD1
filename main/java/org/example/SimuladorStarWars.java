package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//URL del repositorio: https://github.com/PabloPoza2/PabloPoza2-Practica-ADD1.git

public class SimuladorStarWars {
    public static void main(String[] args) {
        Path ruta = Paths.get("datos");
        try {
            if (!Files.exists(ruta)) {
                Files.createDirectory(ruta);
                System.out.println("Directorio creado correctamente.");
            }
        } catch (IOException e) {
            System.err.println("Error al crear el directorio: " + e.getMessage());
            return;
        }

        File ficheroTxt = new File("datos/operaciones.txt");
        try (PrintWriter pw = new PrintWriter(new FileWriter(ficheroTxt))) {
            pw.println("INFO: Control de acceso principal operativo en la base.");
            pw.println("ALERTA: Acceso no autorizado detectado en el sector de hangares.");
            System.out.println("Fichero txt generado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir el TXT: " + e.getMessage());
        }
        File ficheroBin = new File("datos/telemetria.dat");
        try (RandomAccessFile raf = new RandomAccessFile(ficheroBin, "rw")) {
            raf.writeInt(404);
            raf.writeBoolean(true);

            raf.seek(0);
            raf.writeInt(404);
            raf.writeBoolean(true);
            System.out.println("Fichero BIN generado");
        } catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
        }
        File ficheroJson = new File("datos/nave_estado.json");
        try (PrintWriter pwJson = new PrintWriter(new FileWriter(ficheroJson))) {
            pwJson.println("Zona: Hangares");
            pwJson.println("Elemento: Puerta");
            pwJson.println("EStado: Alerta");
            System.out.println("Ficheros Json generado correctamente");
        } catch (IOException e) {
            System.err.println("Error al escribir el JSON: " + e.getMessage());
        }
        File dirEvidencias = new File("logs/evidencias");
        dirEvidencias.mkdirs();
        String timestamp = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        File logEvidencia = new File("logs/evidencias/ejecucion_" + timestamp + ".log");
        try (PrintWriter LogPw = new PrintWriter(new FileWriter(logEvidencia))) {
            LogPw.println(timestamp + " INFO SimuladorStarWars - Ejecución correcta del simulador y datos persistidos.");
        } catch (IOException e) {
            System.err.println("Error creando log de evidencia: " + e.getMessage());
        }
    }
}