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

}
