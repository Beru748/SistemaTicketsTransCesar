package DAO;

import Model.Ticket;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
}
