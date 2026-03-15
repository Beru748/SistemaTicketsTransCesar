package View;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import Service.TicketService;
import Service.PersonaService;
import DAO.VehiculosDAO;
import Model.Pasajero;
import Model.Vehiculo;
import Util.MenuUtil;

/*en esta clase se va a gestionar las ventas de los tickts. 
* dejo el menu para que vean mas o menos cuales son las opciones que van a tener y se hagan
* una idea de los metodos que necesito llamar para mostrar las cosas en consola.*/

public class MenuVentas {
    private Scanner sc;
    private TicketService ticketService;
    private PersonaService personaService;
    private VehiculosDAO vehiculosDAO;

    // Actualizamos el constructor para recibir las dependencias necesarias
    public MenuVentas(TicketService ticketService, PersonaService personaService, VehiculosDAO vehiculosDAO) {
        this.sc = new Scanner(System.in);
        this.ticketService = ticketService;
        this.personaService = personaService;
        this.vehiculosDAO = vehiculosDAO;
    }

    public void menuVentas(){
        int opcion;

        do {
            System.out.println("\n====================================================");
            System.out.println("| |            OPERACIONES DE VENTAS             | |");
            System.out.println("====================================================");
            System.out.println("| |1. Venta de Ticket.                           | |");
            System.out.println("| |2. Historial de ventas.                       | |");
            System.out.println("| |3. Anulacion de Ticket.                       | |");
            System.out.println("| |4. Salir.                                     | |");
            System.out.println("====================================================");
            System.out.print("Escoja una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    realizarVenta();
                    break;
                case 2:
                    mostrarHistorial();
                    break;
                case 3:
                    anularTicket();
                    break;
                case 4:
                    System.out.println("Saliendo del menu de ventas...");
                    MenuUtil.esperarEnter(); 
                    break;
                default:
                    System.out.println("Esa opción es invalida. Intentalo de nuevo.");
                    break;
            }
        } while (opcion != 4);
    }

    private void realizarVenta() {
        System.out.println("\n=== NUEVA VENTA DE TICKET ===");
        
        System.out.print("Ingrese la cedula del pasajero: ");
        String cedula = sc.nextLine().trim();
        Pasajero pasajero = personaService.buscarPasajeroPorCedula(cedula);
        
        if (pasajero == null) {
            System.out.println("Error: Pasajero no encontrado. Registre al pasajero primero en el Menu de Personal.");
            return;
        }

        System.out.print("Ingrese la placa del vehiculo asignado: ");
        String placa = sc.nextLine().trim().toUpperCase();
        Vehiculo vehiculo = vehiculosDAO.buscarPorPlaca(placa);
        
        if (vehiculo == null) {
            System.out.println("Error: Vehiculo no encontrado. Verifique la placa.");
            return;
        }

        if (!vehiculo.isActivo()) {
            System.out.println("Error: El vehiculo se encuentra ARCHIVADO y no puede realizar viajes.");
            return;
        }

        String origen = vehiculo.getRutaAsignada().getCiudadOrigen();
        String destino = vehiculo.getRutaAsignada().getCiudadDestino();

        System.out.println("\nResumen previo:");
        System.out.println("- Pasajero: " + pasajero.getNombre() + " (" + pasajero.getTipoPasajero() + ")");
        System.out.println("- Ruta: " + origen + " -> " + destino);
        System.out.print("¿Confirmar venta? (S/N): ");
        String confirmar = sc.nextLine().trim().toUpperCase();

        if (confirmar.equals("S")) {
            boolean exito = ticketService.venderTicket(pasajero, vehiculo, origen, destino);
            if (!exito) {
                System.out.println("La venta no pudo concretarse debido a las reglas de negocio.");
            }
        } else {
            System.out.println("Venta cancelada por el usuario.");
        }
    }

    private void mostrarHistorial() {
        System.out.println("\n=== HISTORIAL DE VENTAS ===");
        ticketService.listarTickets(); 
    }

    private void anularTicket() {
        System.out.println("\n=== ANULACION DE TICKET ===");
        System.out.print("Ingrese la cedula del pasajero: ");
        String cedula = sc.nextLine().trim();

        System.out.print("Ingrese la fecha del ticket a anular (DD/MM/AAAA): ");
        String fechaStr = sc.nextLine().trim();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fecha = LocalDate.parse(fechaStr, formatter);

            // Verificamos estado actual
            System.out.println("Buscando ticket...");
            ticketService.consultarEstadoTicket(cedula, fecha);

            System.out.print("\n¿Esta seguro que desea ANULAR este ticket? (S/N): ");
            String confirmar = sc.nextLine().trim().toUpperCase();

            if (confirmar.equals("S")) {
                boolean anulado = ticketService.cambiarEstadoTicket(cedula, fecha, "Anulado");
                if (anulado) {
                    System.out.println("El ticket ha sido anulado exitosamente.");
                } else {
                    System.out.println("No se pudo cambiar el estado del ticket.");
                }
            } else {
                System.out.println("Operacion cancelada.");
            }

        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha incorrecto. Use DD/MM/AAAA.");
        }
    }
}
