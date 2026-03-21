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

    //Aqui el metodo del CASE 1 para crear un anueva reserva

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

    //Metodo del CASE 2 para cancelar las reservas
    private void cancelarReserva(){
        String codigo = "";
        System.out.println("\n=== CANCELACION DE LA RESERVA ===");

        while (codigo.isEmpty()) {
            System.out.print("Ingrese el codigo de la reserva a cancerlar: ");
            codigo = sc.nextLine().trim().toUpperCase();

            if (codigo.isEmpty()) {
                System.out.println("Si el campo esta vacio no se puede completar la cancelacion.");
            }
        }

        System.out.print("Esta seguro que desea cancelar la reserva " + codigo +"? (S/N): ");
            String confirmacion = sc.nextLine().trim().toUpperCase();

        if (confirmacion.equals("S")) {
            /*boolean exito = reservaService.cancelarReserva();
            if(!exito){
                System.out.println("Hubo un problema el cancerlar la reserva.");}*/
            System.out.println("Reserva " + codigo + " cancelada.");
        }else{
            System.out.println("Abortando operacion.");
        }

        MenuUtil.esperarEnter();
    }

    //Metodo del CASE 3 para listar todas las reservas

    private void listarReservas(){
        System.out.println("\n=== LISTADO DE LAS RESERVAS ===");
        /*List<Reservas> reservas = reservaService.listarReservasActivas();
        if (reservas.isEmpty){System.out.println("No hay reservas activas. Lista vacia.");
            return;}*/

        System.out.println("Mostrando la Lista de las reservas activas.");
        MenuUtil.esperarEnter();
    }

    //Metodo del CASE 4 para listar las reservas pero por cada pasajero

    private void listarReservasPasajero(){

    }

    //Metodo del CASE 5 para convertir la reserva a un ticket y asi completar la compra

    private void convertirReserva(){

    }

    //Metodo del CASE 6 para mostrar las reservas que estan vencidas

    private void verificarReservasVencidas(){

    }
}
