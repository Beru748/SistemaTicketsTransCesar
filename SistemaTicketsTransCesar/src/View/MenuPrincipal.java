package View;
import java.util.Scanner;

import DAO.VehiculosDAO;
import Service.PersonaService;
import Service.TicketService;
import Service.ReservaService;
import Util.MenuUtil;

public class MenuPrincipal {
    private Scanner sc;
    private MenuVehiculos menuVehiculos;
    private MenuPersonal menuPersonal;
    private MenuReportes menuReportes;
    private MenuVentas menuVentas;
    private MenuReservas menuReservas;

    public MenuPrincipal() {
        this.sc = new Scanner(System.in);
        
        PersonaService personaService = new PersonaService();
        VehiculosDAO vehiculosDAO = new VehiculosDAO();
        TicketService ticketService = new TicketService(personaService.listarPasajeros(), vehiculosDAO.listarVehiculos());
        
        this.menuVehiculos = new MenuVehiculos();
        this.menuPersonal = new MenuPersonal(personaService);
        this.menuReportes = new MenuReportes(ticketService);
        this.menuVentas = new MenuVentas(ticketService, personaService, vehiculosDAO);
        
        ReservaService reservaService = new ReservaService(personaService.listarPasajeros(), vehiculosDAO.listarVehiculos(), ticketService);
        this.menuReservas = new MenuReservas(reservaService, personaService, vehiculosDAO);
    }

    public void menuPrincipal(){
        int opcion;

        do {
            System.out.println("====================================================");
            System.out.println("| |                 MENU PRINCIPAL               | |");
            System.out.println("====================================================");
            System.out.println("| |1. Gestion de vehiculos.                      | |");
            System.out.println("| |2. Gestion de personal.                       | |");
            System.out.println("| |3. Operaciones de ventas.                     | |");
            System.out.println("| |4. Reportes y estadisticas.                   | |");
            System.out.println("| |5. Reservas de viaje.                         | |");
            System.out.println("| |6. Salir.                                     | |");
            System.out.println("====================================================");
            System.out.println("Escoga una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

                switch (opcion) {
                    case 1:
                        //Gestion de vehiculos
                        menuVehiculos.menuVehiculos();
                        break;
                    case 2:
                        //Gestion de personal
                        menuPersonal.menuPersonal();
                        break;
                    case 3:
                        //Operaciones de ventas
                        menuVentas.menuVentas();
                        break;
                    case 4:
                        //Reportes y estadisticas
                        menuReportes.menuReportes();
                        break;
                    case 5:
                        //Reservas de los viajes
                        menuReservas.menuReservas();
                        break;
                    case 6:
                        System.out.println("Guardando los cambios y saliendo del menu...");
                        MenuUtil.esperarEnter();
                        break;
                
                    default:
                        System.out.println("Esa opcion es invalida. Intentalo de nuevo.");
                        break;
            }
        } while (opcion != 6);
    }
}
