package Service;

import DAO.TicketDao;
import Model.Pasajero;
import Model.Ticket;
import Model.Vehiculo;
import java.util.List;

public class TicketService {
    private final TicketDao ticketDAO;
    private List<Ticket> tickets;
    
    public TicketService(List<Pasajero> pasajeros, List<Vehiculo> vehiculos) {
        this.ticketDAO = new TicketDao();
        // Carga automática al iniciar: sesiones anteriores disponibles desde el primer momento
        this.tickets = ticketDAO.cargarTodos(pasajeros, vehiculos);
    }
    
    
    public boolean venderTicket(Pasajero pasajero, Vehiculo vehiculo,
                                 String origen, String destino) {

        // REGLA: verificar cupos disponibles
        if (!vehiculo.tieneCuposDisponibles()) {
            System.out.println("[ERROR] El vehículo " + vehiculo.getPlaca()
                    + " no tiene cupos disponibles. Venta cancelada.");
            return false;
        }

        // Crear ticket (el valor final se calcula automáticamente con polimorfismo)
        Ticket ticket = new Ticket(pasajero, vehiculo, origen, destino);

        // Registrar el pasajero en el vehículo
        vehiculo.agregarPasajero();

        // Guardar en memoria y en archivo
        tickets.add(ticket);
        ticketDAO.guardar(ticket);

        System.out.println("[OK] Ticket vendido exitosamente.");
        ticket.imprimirDetalle();
        return true;
    }
    
    public void listarTickets() {
        if (tickets.isEmpty()) {
            System.out.println("No hay tickets registrados.");
            return;
        }
        System.out.println("===== TODOS LOS TICKETS (" + tickets.size() + ") =====");
        for (Ticket t : tickets) {
            t.imprimirDetalle();
        }
    }
    
    public double calcularTotalRecaudado() {
        double total = 0;
        for (Ticket t : tickets) {
            total += t.getValorFinal();
        }
        return total;
    }

}
