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
import Util.RutasArchivos;

public class TicketDAO {
    private static final String RUTA_ARCHIVO = RutasArchivos.TICKETS;
 
    public void guardar(Ticket ticket) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, true))) {
            writer.write(ticket.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("[ERROR] No se pudo guardar el ticket: " + e.getMessage());
        }
    }
 
    
    public void guardarTodos(List<Ticket> tickets) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, false))) {
        for (Ticket t : tickets) {
            writer.write(t.toString());
            writer.newLine();
        }
    } catch (IOException e) {
        System.out.println("[ERROR] No se pudo actualizar tickets.txt: " + e.getMessage());
    }
}
 
    public List<Ticket> cargarTodos(List<Pasajero> pasajeros, List<Vehiculo> vehiculos) {
        List<Ticket> lista = new ArrayList<>();
        File archivo = new File(RUTA_ARCHIVO);
 
        if (!archivo.exists()) return lista;
 
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    String[] campos = linea.split(";");
                    
                    if (campos.length == 9) {
                        String cedulaPasajero = campos[0];
                        String placaVehiculo  = campos[3];
                        LocalDate fecha       = LocalDate.parse(campos[4]);
                        String origen         = campos[5];
                        String destino        = campos[6];
                        double valorFinal     = Double.parseDouble(campos[7]);
                        String estado         = campos[8]; 
 
                        Pasajero pasajero = buscarPasajeroPorCedula(pasajeros, cedulaPasajero);
                        Vehiculo vehiculo = buscarVehiculoPorPlaca(vehiculos, placaVehiculo);
 
                        if (pasajero != null && vehiculo != null) {
                            Ticket t = new Ticket(pasajero, vehiculo, fecha,
                                                  origen, destino, valorFinal, estado);
                            lista.add(t);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("[ERROR] No se pudo leer tickets.txt: " + e.getMessage());
        }
 
        return lista;
    }
 
    private Pasajero buscarPasajeroPorCedula(List<Pasajero> lista, String cedula) {
        for (Pasajero p : lista) {
            if (p.getCedula().equals(cedula)) return p;
        }
        return null;
    }
 
    private Vehiculo buscarVehiculoPorPlaca(List<Vehiculo> lista, String placa) {
        for (Vehiculo v : lista) {
            if (v.getPlaca().equalsIgnoreCase(placa)) return v;
        }
        return null;
    }
}
