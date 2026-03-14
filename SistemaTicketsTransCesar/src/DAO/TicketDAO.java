package DAO;

import Model.Pasajero;
import Model.Ticket;
import Model.Vehiculo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        return null;
        
    }
}
