package DAO;

import Model.Conductor;
import java.io.BufferedWriter;
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
        return null;
        
    }
}
