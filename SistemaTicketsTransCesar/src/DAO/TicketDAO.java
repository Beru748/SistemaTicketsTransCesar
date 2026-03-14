package DAO;

import Model.Pasajero;
import Model.Ticket;
import Model.Vehiculo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    private static final String RUTA_ARCHIVO = "tickets.txt";
    public void guardar(Ticket ticket) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, true))) {
            writer.write(ticket.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("[ERROR] No se pudo guardar el ticket: " + e.getMessage());
        }
    }
    
    public List<Ticket> cargarTodos(List<Pasajero> pasajeros, List<Vehiculo> vehiculos) {
        List<Ticket> lista = new ArrayList<>();
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
                    if (campos.length == 8) {
                        String cedulaPasajero = campos[0];
                        String placaVehiculo  = campos[3];
                        LocalDate fecha       = LocalDate.parse(campos[4]);
                        String origen         = campos[5];
                        String destino        = campos[6];
                        double valorFinal     = Double.parseDouble(campos[7]);

                        // Buscar pasajero por cédula
                        Pasajero pasajero = buscarPasajeroPorCedula(pasajeros, cedulaPasajero);
                        // Buscar vehículo por placa
                        Vehiculo vehiculo = buscarVehiculoPorPlaca(vehiculos, placaVehiculo);

                        if (pasajero != null && vehiculo != null) {
                            Ticket t = new Ticket(pasajero, vehiculo, origen, destino);
                            lista.add(t);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("[ERROR] No se pudo leer tickets.txt: " + e.getMessage());
        }
        return null;
        
    }
}
