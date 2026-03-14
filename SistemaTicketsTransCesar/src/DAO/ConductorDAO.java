package DAO;

import Model.Conductor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConductorDAO {
    private static final String RUTA_ARCHIVO = "conductores.txt";
    
    public void guardar(Conductor conductor) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, true))) {
            writer.write(conductor.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("[ERROR] No se pudo guardar el conductor: " + e.getMessage());
        }
    }
    
    public List<Conductor> cargarTodos() {
        List<Conductor> lista = new ArrayList<>();
        File archivo = new File(RUTA_ARCHIVO);

        if (!archivo.exists()) {
            return lista;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    String[] campos = linea.split(";");
                    if (campos.length == 4) {
                        Conductor c = new Conductor(campos[2], campos[3], campos[0], campos[1]);
                        lista.add(c);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("[ERROR] No se pudo leer conductores.txt: " + e.getMessage());
        }

        return lista;
        
    }
}
