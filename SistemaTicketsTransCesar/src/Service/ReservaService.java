package Service;

import java.util.List;

import Model.Pasajero;
import Model.Reserva;
import Model.Vehiculo;

public class ReservaService {
private final ReservaDAO   reservaDAO;
    private final TicketService ticketService;   
    private List<Reserva>      reservas;
 
    public ReservaService(List<Pasajero> pasajeros, List<Vehiculo> vehiculos,
                          TicketService ticketService) {
        this.reservaDAO   = new ReservaDAO();
        this.ticketService = ticketService;
        this.reservas     = reservaDAO.cargarTodas(pasajeros, vehiculos);
 
        
    }
}
