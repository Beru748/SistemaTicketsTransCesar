package DAO;

import Model.Pasajero;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PasajeroDAO {
    private static final String RUTA_ARCHIVO = "pasajeros.txt";
    
    public void guardar(Pasajero pasajero) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, true))) {
            writer.write(pasajero.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("[ERROR] No se pudo guardar el pasajero: " + e.getMessage());
        }
    }
}
