package View;

import Util.MenuUtil;
import java.util.Scanner;
import java.util.List;
//import Service.ReservaService;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class MenuReservas {
    private Scanner sc;
    //private ReservaService reservaService;

    public MenuReservas() {
        this.sc = new Scanner(System.in);
        //this.reservaService =new ReservaService();
    }

    private void menuReservas(){
        int opcion;

    do {
        System.out.println("\n====================================================");
            System.out.println("| |               MENU DE RESERVAS               | |");
            System.out.println("====================================================");
            System.out.println("| |1. Crear una nueva reserva.                   | |");
            System.out.println("| |2. Cancelar una reserva existente.            | |");
            System.out.println("| |3. Listar todas las reservas activas.         | |");
            System.out.println("| |4. Listar historial de pasajero.              | |");
            System.out.println("| |5. Convertir una reserva en ticket.           | |");
            System.out.println("| |6. Verificar reservas vencidas.               | |");
            System.out.println("| |7. Salir.                                     | |");
            System.out.println("====================================================");
            System.out.print("Escoja una opcion: ");
        opcion = sc.nextInt();
        sc.nextLine();

            switch (opcion) {
                case 1:
                    //Crear una reserva
                    crearReserva();
                    break;
                case 2:
                    //Cancelar las reservas
                    cancelarReserva();
                    break;
                case 3:
                    //Listar todas las reservas
                    listarReservas();
                    break;
                case 4:
                    //Listar reservas por persona 
                    listarReservasPasajero();
                    break;
                case 5:
                    //Cambiar de estado las reservas
                    convertirReserva();
                    break;
                case 6:
                    //Verifica y muestra un historial de las resrervas que estan vencidas
                    verificarReservasVencidas();
                    break;    
                case 7:
                    System.out.println("Saliendo del menu de reservas... ");
                    MenuUtil.esperarEnter();
                    break;
            
                default:
                    System.out.println("Esa opcion es invalida. Intentalo de nuevo.");
                    break;
            }
        } while (opcion != 7);
    }

    private void crearReserva(){

    }

    private void cancelarReserva(){

    }

    private void listarReservas(){

    }

    private void listarReservasPasajero(){

    }

    private void convertirReserva(){

    }

    private void verificarReservasVencidas(){

    }
}
