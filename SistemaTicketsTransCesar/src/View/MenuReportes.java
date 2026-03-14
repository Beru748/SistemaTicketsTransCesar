package View;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import Service.TicketService;
import Util.MenuUtil;

/*en esta clase se va a gestionar los reportes de las ventas como: la suma de todos los tickets vendidos
* y cuanto dinero generaron los vehiculos.
* dejo el menu para que vean mas o menos cuales son las opciones que van a tener y se hagan
* una idea de los metodos que necesito llamar para mostrar las cosas en consola.*/

public class MenuReportes {
    private Scanner sc;
    private TicketService ticketService;

    public MenuReportes() {
        this.sc = new Scanner(System.in);
        this.ticketService = TicketService();
    }

    public void menuReportes(){
        int opcion;

        do {
            System.out.println("====================================================");
            System.out.println("| |            REPORTES Y ESTADISTICAS           | |");
            System.out.println("====================================================");
            System.out.println("| |1. Consultar tickets por fecha especifica.    | |");
            System.out.println("| |2. Consultar tickets por tipo de vehiculo.    | |");
            System.out.println("| |3. Consultar tickets por tipo de pasajero.    | |");
            System.out.println("| |4. Ver resumen del dia actual.                | |");
            System.out.println("| |5. Salir.                                     | |");
            System.out.println("====================================================");
            System.out.println("Escoga una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

                switch (opcion) {
                case 1:
                    consultarPorFecha();
                    break;
                case 2:
                    consultarPorTipoVehiculo();
                    break;
                case 3:
                    consultarPorTipoPasajero();
                    break;
                case 4:
                    verResumenDiaActual();
                    break;
                case 5:
                    System.out.println("Saliendo del menu de reportes...");
                    MenuUtil.esperarEnter();
                    break;
                default:
                    System.out.println("Esa opcion es invalida. Intentalo de nuevo.");
                    break;
            }
        } while (opcion != 5);
    }

    private void consultarPorFecha() {
        System.out.println("\n=== TICKETS POR FECHA ESPECIFICA ===");
        System.out.print("Ingrese la fecha a consultar (DD/MM/AAAA): ");
        String fechaStr = sc.nextLine().trim();
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fecha = LocalDate.parse(fechaStr, formatter);
            
            // aqui llama al metodo que muestra el ticket por la fecha
            ticketService.mostrarTicketsPorFecha(fecha);
            
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha incorrecto. Use DD/MM/AAAA.");
        }
    }

    private void consultarPorTipoVehiculo() {
        System.out.println("\n=== TICKETS POR TIPO DE VEHICULO ==");
        System.out.print("Ingrese el tipo (BUS, BUSETA, MICROBUS): ");
        String tipo = sc.nextLine().trim().toUpperCase();
        
        // aqui llama al metodo que muestra el ticket por tipo de vehiculo
        ticketService.mostrarTicketsPorTipoVehiculo(tipo);
    }

    private void consultarPorTipoPasajero() {
        System.out.println("\n=== TICKETS POR TIPO DE PASAJERO ===");
        System.out.println("Tipos validos: Regular, Estudiante, AdultoMayor");
        System.out.print("Ingrese el tipo de pasajero: ");
        String tipo = sc.nextLine().trim();
        
        //aqui llama al metodo que muestra el ticket por pasajero
        ticketService.mostrarTicketsPorTipoPasajero(tipo);
    }

    private void verResumenDiaActual() {
        System.out.println("\n=== RESUMEN DEL DIA ACTUAL ===");
        LocalDate hoy = LocalDate.now();
        
        // Llamada al metodo que muestre el resumen del dia
        ticketService.mostrarResumenDia(hoy);
    }
}
