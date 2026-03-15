package DAO;
 
import Model.Pasajero;
import Model.PasajeroAdultoMayor;
import Model.PasajeroEstudiante;
import Model.PasajeroRegular;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
 
public class PasajeroDAO {
 
    private static final String RUTA_ARCHIVO = "Data/pasajeros.txt";
 
    public void guardar(Pasajero pasajero) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, true))) {
            writer.write(pasajero.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("[ERROR] No se pudo guardar el pasajero: " + e.getMessage());
        }
    }
 
    public List<Pasajero> cargarTodos() {
        List<Pasajero> lista = new ArrayList<>();
        File archivo = new File(RUTA_ARCHIVO);
 
        if (!archivo.exists()) return lista;
 
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    String[] campos = linea.split(";");
                    if (campos.length == 4) {
                        String cedula      = campos[0];
                        String nombre      = campos[1];
                        String tipo        = campos[2];
                        LocalDate fechaNac = LocalDate.parse(campos[3]);
 
                        Pasajero p = switch (tipo) {
                            case "Estudiante"  -> new PasajeroEstudiante(cedula, nombre, fechaNac);
                            case "AdultoMayor" -> new PasajeroAdultoMayor(cedula, nombre, fechaNac);
                            default            -> new PasajeroRegular(cedula, nombre, fechaNac);
                        };
 
                        lista.add(p);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("[ERROR] No se pudo leer pasajeros.txt: " + e.getMessage());
        }
 
        return lista;
    }
}