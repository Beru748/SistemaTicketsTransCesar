package Service;

import DAO.TicketDao;
import Model.Pasajero;
import Model.Ticket;
import Model.Vehiculo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    public void mostrarEstadisticasPorTipo() {
        Map<String, Integer> conteo = new HashMap<>();
        conteo.put("Regular",     0);
        conteo.put("Estudiante",  0);
        conteo.put("AdultoMayor", 0);

        for (Ticket t : tickets) {
            String tipo = t.getPasajero().getTipoPasajero();
            conteo.put(tipo, conteo.getOrDefault(tipo, 0) + 1);
        }

        System.out.println("===== PASAJEROS POR TIPO =====");
        System.out.println("Regular     : " + conteo.get("Regular"));
        System.out.println("Estudiante  : " + conteo.get("Estudiante"));
        System.out.println("Adulto Mayor: " + conteo.get("AdultoMayor"));
        System.out.println("==============================");
    }

    
    public void mostrarVehiculoMasTickets() {
        if (tickets.isEmpty()) {
            System.out.println("No hay tickets registrados.");
            return;
        }

        Map<String, Integer> conteo = new HashMap<>();
        for (Ticket t : tickets) {
            String placa = t.getVehiculo().getPlaca();
            conteo.put(placa, conteo.getOrDefault(placa, 0) + 1);
        }

        String placaGanadora = null;
        int maxTickets = 0;
        for (Map.Entry<String, Integer> entry : conteo.entrySet()) {
            if (entry.getValue() > maxTickets) {
                maxTickets    = entry.getValue();
                placaGanadora = entry.getKey();
            }
        }

        System.out.println("===== VEHÍCULO CON MÁS TICKETS =====");
        System.out.println("Placa   : " + placaGanadora);
        System.out.println("Tickets : " + maxTickets);
        System.out.println("=====================================");
    }

}
