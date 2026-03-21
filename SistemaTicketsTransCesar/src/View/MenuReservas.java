package View;

import Util.MenuUtil;

import java.util.List;
import java.util.Scanner;

import Service.ReservaService;
import Service.PersonaService;
import DAO.VehiculosDAO;
import Model.Pasajero;
import Model.Vehiculo;
import Model.Reserva;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class MenuReservas {
    private Scanner sc;
    private ReservaService reservaService;
    private PersonaService personaService;
    private VehiculosDAO vehiculosDAO;

    public MenuReservas(ReservaService reservaService, PersonaService personaService, VehiculosDAO vehiculosDAO) {
        this.sc = new Scanner(System.in);
        this.reservaService = reservaService;
        this.personaService = personaService;
        this.vehiculosDAO = vehiculosDAO;
    }

    public void menuReservas(){
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
                    crearReservas();
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

    private void crearReservas(){
        System.out.println("\n=== CREACION DE RESERVAS ===");

        System.out.print("Ingrese la cedula del pasajero: ");
        String cedula = sc.nextLine().trim();
        Pasajero pasajero = personaService.buscarPasajeroPorCedula(cedula);
        if (pasajero == null) {
            System.out.println("Error: Pasajero no encontrado. Registrelo primero en el modulo de Personal.");
            return;
        }

        System.out.print("Ingrese la placa del vehiculo: ");
        String placa = sc.nextLine().trim().toUpperCase();
        Vehiculo vehiculo = vehiculosDAO.buscarPorPlaca(placa);
        if (vehiculo == null) {
            System.out.println("Error: Vehiculo no encontrado en el sistema.");
            return;
        }

        LocalDate fechaViajeDate = null;
        boolean fechaValida = false;
        
        while (!fechaValida) {
            System.out.print("Ingrese la Fecha del viaje (DD/MM/AAAA): ");
            String fechaViaje = sc.nextLine().trim();

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                fechaViajeDate = LocalDate.parse(fechaViaje, formatter);
                
                if (fechaViajeDate.isBefore(LocalDate.now())) {
                    System.out.println("Error: No puedes reservar para una fecha en el pasado.");
                } else {
                    fechaValida = true;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto. Use exactamente DD/MM/AAAA.");
            }
        }

        boolean exito = reservaService.crearReserva(pasajero, vehiculo, fechaViajeDate);

        if (!exito) {
            System.out.println("Hubo un problema al crear la reserva.");
        }
        MenuUtil.esperarEnter();
    }

    //Metodo del CASE 2 para cancelar las reservas

    private void cancelarReserva(){
        System.out.println("\n=== CANCELACION DE LA RESERVA ===");

            System.out.print("Ingrese el codigo de la reserva a cancerlar: ");
            String codigo = sc.nextLine().trim().toUpperCase();

            if (codigo.isEmpty()) {
                System.out.println("Si el campo esta vacio no se puede completar la cancelacion.");
            }

        System.out.print("Esta seguro que desea cancelar la reserva " + codigo +"? (S/N): ");
            String confirmacion = sc.nextLine().trim().toUpperCase();

        if (confirmacion.equals("S")) {
            reservaService.cancelarReserva(codigo); 
        } else {
            System.out.println("Abortando operacion.");
        }

        MenuUtil.esperarEnter();
    }

    //Metodo del CASE 3 para listar todas las reservas

    private void listarReservas(){
        System.out.println("\n=== LISTADO DE LAS RESERVAS ===");
        List<Reserva> activas = reservaService.listarReservasActivas();
        if (activas.isEmpty()){
            System.out.println("No hay reservas activas. Lista vacia.");
        }else{
            for (Reserva r : activas) {
                System.out.println(r.toString());
            }
        }

        System.out.println("Mostrando la Lista de las reservas activas.");
        MenuUtil.esperarEnter();
    }

    //Metodo del CASE 4 para listar las reservas pero por cada pasajero

    private void listarReservasPasajero(){
        System.out.println("\n=== LISTADO DE RESERVAS POR PASAJERO ===");
        System.out.print("Ingreese la cedula del pasajero: ");
        String cedula = sc.nextLine().trim();

        if (cedula.isEmpty()) {
            System.out.println("El campo de la cedula no puede estar vacio.");
            return;
        }

        List<Reserva> histrorial = reservaService.historialPorPasajero(cedula);
        if (histrorial.isEmpty()){
            System.out.println("Este pasajero no tiene un historial de reservas. Lista vacia.");
        }else{
            for (Reserva r : histrorial) {
                System.out.println(r.toString());
            }
        }

        System.out.println("Mostrando la Historial de reservas de la persona con la cedula [" + cedula+"]");
        MenuUtil.esperarEnter();
    }

    //Metodo del CASE 5 para convertir la reserva a un ticket y asi completar la compra

    private void convertirReserva(){
        System.out.println("\n=== CONVERTIR RESERVA A TICKET ===");
        System.out.print("Ingrese el codigo del ticket: ");
        String codigo = sc.nextLine().trim().toUpperCase();

        if (codigo.isEmpty()) {
            System.out.println("Si el campo esta vacio no se puede completar la cancelacion.");
        }

        System.out.print("Confirmar el pago y la conversion del ticket? (S/N): ");
        String confirmar = sc.nextLine().trim().toUpperCase();

        if (confirmar.equals("S")) {
            reservaService.convertirEnTicket(codigo);
        }else{
            System.out.println("Operacion cancelada.");
        }
        MenuUtil.esperarEnter();
    }

    //Metodo del CASE 6 para mostrar las reservas que estan vencidas

    private void verificarReservasVencidas(){
        System.out.println("\n=== AUDITORIA DE RESERVAS VENCIDAS ===");
        System.out.println("Buscando reservas que superen las 24 horas...");
        
        reservaService.verificarReservasVencidas();
        
        MenuUtil.esperarEnter();
    }
}
