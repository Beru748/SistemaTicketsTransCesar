package Service;
 
import DAO.ReservaDAO;
import Model.Reserva;
import Model.Reserva.EstadoReserva;
import Model.Pasajero;
import Model.Vehiculo;
 
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReservaService {
private final ReservaDAO    reservaDAO;
    private final TicketService ticketService;
    private List<Reserva>       reservas;
 
    public ReservaService(List<Pasajero> pasajeros, List<Vehiculo> vehiculos,
                          TicketService ticketService) {
        this.reservaDAO    = new ReservaDAO();
        this.ticketService = ticketService;
        this.reservas      = reservaDAO.cargarTodas(pasajeros, vehiculos);
 
        // Al arrancar verifica vencidas automáticamente sin mostrar mensajes
        verificarVencidasSilencioso();
    }
 
    // ─────────────────────── CREAR ───────────────────────
 
    public boolean crearReserva(Pasajero pasajero, Vehiculo vehiculo, LocalDate fechaViaje) {
 
        // REGLA 1: tickets vendidos + reservas activas no puede superar la capacidad
        int reservasActivas   = contarReservasActivasPorVehiculo(vehiculo.getPlaca());
        int pasajerosActuales = vehiculo.getPasajerosActuales();
        if (pasajerosActuales + reservasActivas >= vehiculo.getCapacidadMaxima()) {
            System.out.println("[ERROR] El vehiculo " + vehiculo.getPlaca()
                    + " no tiene cupos disponibles para reservar.");
            return false;
        }
 
        // REGLA 2: un pasajero no puede tener más de una reserva activa para el mismo vehículo y fecha
        if (tieneReservaActivaParaVehiculoYFecha(pasajero.getCedula(),
                                                  vehiculo.getPlaca(), fechaViaje)) {
            System.out.println("[ERROR] " + pasajero.getNombre()
                    + " ya tiene una reserva activa para ese vehiculo en esa fecha.");
            return false;
        }
 
        // El estado que se pasa al constructor es ignorado (la clase siempre pone ACTIVA)
        Reserva nueva = new Reserva(generarCodigo(), pasajero, vehiculo,
                                    LocalDateTime.now(), fechaViaje, EstadoReserva.ACTIVA);
        reservas.add(nueva);
        reservaDAO.guardar(nueva);
 
        System.out.println("[OK] Reserva creada exitosamente.");
        System.out.println(nueva.toString());
        return true;
    }
 
    
}
