package Service;

import DAO.TicketDAO;
import Model.Pasajero;
import Model.Ticket;
import Model.Vehiculo;
import java.time.LocalDate;
import java.time.MonthDay;    
import java.util.ArrayList;
import java.util.Arrays;     
import java.util.HashMap;
import java.util.HashSet;     
import java.util.List;
import java.util.Map;
import java.util.Set;        

public class TicketService {
    private final TicketDAO ticketDAO;
    private List<Ticket> tickets;

    // ← NUEVO REQ 2: festivos y constantes
    private static final Set<MonthDay> FESTIVOS = new HashSet<>(Arrays.asList(
        MonthDay.of(1,  1),   // Año Nuevo
        MonthDay.of(1,  6),   // Reyes Magos
        MonthDay.of(5,  1),   // Día del Trabajo
        MonthDay.of(7,  20),  // Independencia de Colombia
        MonthDay.of(8,  7),   // Batalla de Boyacá
        MonthDay.of(12, 8),   // Inmaculada Concepción
        MonthDay.of(12, 25)   // Navidad
    ));

    private static final double RECARGO_FESTIVO = 0.20;
    private static final int    MAX_TICKETS_DIA = 3;

    public TicketService(List<Pasajero> pasajeros, List<Vehiculo> vehiculos) {
        this.ticketDAO = new TicketDAO();
        this.tickets = ticketDAO.cargarTodos(pasajeros, vehiculos);
    }

    // ─────────────────────── VENTA ───────────────────────

    /**
     * Vende un ticket para un pasajero en un vehículo dado.
     *
     * REGLAS DE NEGOCIO aplicadas aquí (capa service, no en ninguna otra):
     *   1. El vehículo debe tener cupos disponibles.
     *   2. El valor final se calcula usando polimorfismo: pasajero.calcularDescuento()
     *   3. REQ 2: máximo 3 tickets por pasajero por día.
     *   4. REQ 2: recargo del 20% si es día festivo.
     */
    public boolean venderTicket(Pasajero pasajero, Vehiculo vehiculo,
                                 String origen, String destino) {

        // REGLA 1: verificar cupos disponibles
        if (!vehiculo.tieneCupo()) {
            System.out.println("[ERROR] El vehículo " + vehiculo.getPlaca()
                    + " no tiene cupos disponibles. Venta cancelada.");
            return false;
        }

        // REGLA 2 (REQ 2): máximo 3 tickets por día
        int ticketsHoy = contarTicketsHoy(pasajero.getCedula());
        if (ticketsHoy >= MAX_TICKETS_DIA) {
            System.out.println("[ERROR] " + pasajero.getNombre()
                    + " ya tiene " + ticketsHoy + " ticket(s) hoy."
                    + " No se permite más de " + MAX_TICKETS_DIA + " tickets por día.");
            return false;
        }

        // REGLA 3 (REQ 2): recargo del 20% en festivos
        double tarifaFinal = vehiculo.getPrecioBaseTicket();
        if (esFestivo(LocalDate.now())) {
            tarifaFinal = tarifaFinal * (1 + RECARGO_FESTIVO);
            System.out.println("[INFO] Día festivo. Tarifa con recargo 20%: $"
                    + String.format("%.0f", tarifaFinal));
        }

        // Crear ticket con la tarifa calculada
        Ticket ticket = new Ticket(pasajero, vehiculo, origen, destino, tarifaFinal);

        // Registrar el pasajero en el vehículo
        vehiculo.ocuparAsiento();

        // Guardar en memoria y en archivo
        tickets.add(ticket);
        ticketDAO.guardar(ticket);

        System.out.println("[OK] Ticket vendido exitosamente.");
        ticket.imprimirDetalle();
        return true;
    }

    // ← NUEVO REQ 2: cuenta tickets del pasajero hoy
    private int contarTicketsHoy(String cedulaPasajero) {
        LocalDate hoy = LocalDate.now();
        int contador = 0;
        for (Ticket t : tickets) {
            if (t.getPasajero().getCedula().equals(cedulaPasajero)
                    && t.getFechaCompra().equals(hoy)) {
                contador++;
            }
        }
        return contador;
    }

    // ← NUEVO REQ 2: verifica si una fecha es festivo
    public boolean esFestivo(LocalDate fecha) {
        return FESTIVOS.contains(MonthDay.of(fecha.getMonth(), fecha.getDayOfMonth()));
    }

    // ─────────────────────── GESTIÓN DE ESTADOS ───────────────────────

    // ← NUEVO: cambia estado de un ticket
    public boolean cambiarEstadoTicket(String cedulaPasajero, LocalDate fecha,
                                        String nuevoEstado) {
        Ticket ticket = buscarTicket(cedulaPasajero, fecha);
        if (ticket == null) {
            System.out.println("[ERROR] No se encontró ticket para cédula "
                    + cedulaPasajero + " en fecha " + fecha + ".");
            return false;
        }
        return ticket.cambiarEstado(nuevoEstado);
    }

    // ← NUEVO: consulta estado de un ticket
    public void consultarEstadoTicket(String cedulaPasajero, LocalDate fecha) {
        Ticket ticket = buscarTicket(cedulaPasajero, fecha);
        if (ticket == null) {
            System.out.println("[ERROR] No se encontró ticket para cédula "
                    + cedulaPasajero + " en fecha " + fecha + ".");
            return;
        }
        System.out.println("===== ESTADO DEL TICKET =====");
        System.out.println("Pasajero : " + ticket.getPasajero().getNombre());
        System.out.println("Fecha    : " + ticket.getFechaCompra());
        System.out.println("Ruta     : " + ticket.getOrigen() + " -> " + ticket.getDestino());
        System.out.println("Estado   : " + ticket.getEstado());
        System.out.println("=============================");
    }

    // ← NUEVO: método privado para buscar ticket por cédula y fecha
    private Ticket buscarTicket(String cedulaPasajero, LocalDate fecha) {
        for (Ticket t : tickets) {
            if (t.getPasajero().getCedula().equals(cedulaPasajero)
                    && t.getFechaCompra().equals(fecha)) {
                return t;
            }
        }
        return null;
    }

    // ─────────────────────── SOPORTE REQ 3 (reportes para el Líder) ───────────────────────

    // ← NUEVO REQ 3: tickets por fecha
    public List<Ticket> getTicketsPorFecha(LocalDate fecha) {
        List<Ticket> resultado = new ArrayList<>();
        for (Ticket t : tickets) {
            if (t.getFechaCompra().equals(fecha)) resultado.add(t);
        }
        return resultado;
    }

    // ← NUEVO REQ 3: tickets por tipo de pasajero
    public List<Ticket> getTicketsPorTipoPasajero(String tipo) {
        List<Ticket> resultado = new ArrayList<>();
        for (Ticket t : tickets) {
            if (t.getPasajero().getTipoPasajero().equalsIgnoreCase(tipo)) resultado.add(t);
        }
        return resultado;
    }

    // ← NUEVO REQ 3: tickets por tipo de vehículo
    public List<Ticket> getTicketsPorTipoVehiculo(String tipo) {
        List<Ticket> resultado = new ArrayList<>();
        for (Ticket t : tickets) {
            if (t.getVehiculo().getClass().getSimpleName().equalsIgnoreCase(tipo))
                resultado.add(t);
        }
        return resultado;
    }

    // ← NUEVO REQ 3: resumen del día actual
    public void mostrarResumenHoy() {
        LocalDate hoy = LocalDate.now();
        List<Ticket> hoyTickets = getTicketsPorFecha(hoy);
        double totalHoy = 0;
        for (Ticket t : hoyTickets) totalHoy += t.getValorFinal();

        System.out.println("===== RESUMEN DEL DÍA " + hoy + " =====");
        System.out.println("Tickets vendidos : " + hoyTickets.size());
        System.out.printf ("Total recaudado  : $%.0f%n", totalHoy);
        if (esFestivo(hoy))
            System.out.println("[INFO] Hoy es festivo — recargo del 20% aplicado.");
        System.out.println("==========================================");
    }

    // ─────────────────────── CONSULTAS Y ESTADÍSTICAS ───────────────────────

    /**
     * Lista todos los tickets vendidos con su información completa.
     */
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

    /**
     * Calcula el total de dinero recaudado en todas las ventas.
     */
    public double calcularTotalRecaudado() {
        double total = 0;
        for (Ticket t : tickets) {
            total += t.getValorFinal();
        }
        return total;
    }

    /**
     * Muestra cuántos pasajeros de cada tipo han comprado tickets.
     */
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

    /**
     * Identifica cuál es el vehículo con más tickets vendidos.
     */
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

    public List<Ticket> getTickets() {
        return tickets;
    }
}