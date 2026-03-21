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
        System.out.println("\n=== CREACION DE RESERVAS ===");
        System.out.print("Ingrese la cedula del pasajero: ");
        String cedula = sc.nextLine().trim();
        System.out.print("Ingrese la placa del vehiculo: ");
        String placa = sc.nextLine().trim();

        boolean fechaValida = false;
        System.out.print("Ingrese la Fecha del viaje (DD/MM/AAAA): ");
        String fechaViaje = sc.nextLine().trim();

        try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate fechaIngresada = LocalDate.parse(fechaViaje, formatter);
                
                if (fechaIngresada.isBefore(LocalDate.now())) {
                    System.out.println("Error: No puedes reservar para una fecha en el pasado.");
                } else {
                    fechaValida = true;//aqui se valida la fecha del viaje, osea que si todo sale bien sale del ciclo
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto. Use exactamente DD/MM/AAAA.");
            }

            /*boolean exito = reservaService.crearReserva(cedula,placa,fechaViaje);
            if(!exito){
                System.out.println("Hubo un problema al crear la reserva.");
            }*/

            System.out.println("Dato capturados correctamente. Conectando con el Servicio...");
            MenuUtil.esperarEnter();
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
