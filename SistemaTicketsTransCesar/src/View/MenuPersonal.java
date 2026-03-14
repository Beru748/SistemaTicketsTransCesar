package View;
import java.util.Scanner;
import java.util.List;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Service.PersonaService;
import Model.Pasajero;
import Model.Conductor;
import Util.MenuUtil;

/*en esta clase se va a gestionar tanto al personal de la empresa como a los pasajero. 
* dejo el menu para que vean mas o menos cuales son las opciones que van a tener y se hagan
* una idea de los metodos que necesito llamar para mostrar las cosas en consola.*/

public class MenuPersonal {
    private Scanner sc;
    private PersonaService personaService;

    public MenuPersonal() {
        this.sc = new Scanner(System.in);
        this.personaService = new PersonaService();
    }

    public void menuPersonal(){
        int opcion;

        System.out.println("====================================================");
        System.out.println("| |             GESTION DEL PERSONAL             | |");
        System.out.println("====================================================");
        System.out.println("| |1. Registrar conductor.                       | |");
        System.out.println("| |2. Registrar pasajero.                        | |");
        System.out.println("| |3. Listar personal activo.                    | |");
        System.out.println("| |4. Buscar por cedula.                         | |");
        System.out.println("| |5. Salir.                                     | |");
        System.out.println("====================================================");
        System.out.println("Escoga una opcion: ");
        opcion = sc.nextInt();
        sc.nextLine();

        do {
            switch (opcion) {
                case 1:
                    //Registrar conductor
                    registrarConductor();
                    break;
                case 2:
                    //Registrar pasajero
                    registrarPasajero();
                    break;
                case 3:
                    //Listar personal activo
                    listarPersonal();
                    break;
                case 4:
                    //Buscar por la cedula al personal y a los pasjero por igual
                    buscarPorCedula();
                    break;
                case 5:
                    System.out.println("Saliendo del menu...");
                    MenuUtil.esperarEnter();
                    break;
            
                default:
                    System.out.println("Esa opcion es invalida. Intentalo de nuevo.");
                    break;
            }
        } while (opcion != 5);
    }
private void registrarConductor() {
        System.out.println("\n=== REGISTRAR CONDUCTOR ===");
        System.out.print("Ingrese cedula: ");
        String cedula = sc.nextLine().trim();
        System.out.print("Ingrese nombre completo: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Ingrese numero de licencia: ");
        String licencia = sc.nextLine().trim();
        System.out.print("Ingrese categoria de licencia: ");
        String categoria = sc.nextLine().trim();

        boolean exito = personaService.registrarConductor(cedula, nombre, licencia, categoria);
        if (!exito) {
            System.out.println("Hubo un problema al registrar el conductor.");
        }
    }

    private void registrarPasajero() {
        System.out.println("\n=== REGISTRAR PASAJERO ===");
        System.out.print("Ingrese cédula: ");
        String cedula = sc.nextLine().trim();
        System.out.print("Ingrese nombre completo: ");
        String nombre = sc.nextLine().trim();

        // REQUERIMIENTO 1: Logica de validacion de edad y tipo de pasajero
        System.out.print("¿El pasajero es estudiante activo? (S/N): ");
        String esEstudiante = sc.nextLine().trim().toUpperCase();

        String tipoPasajero = "Regular";

        if (esEstudiante.equals("S")) {
            tipoPasajero = "Estudiante";
        } else {
            System.out.print("Ingrese la fecha de nacimiento (DD/MM/AAAA): ");
            String fechaStr = sc.nextLine().trim();
            
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate fechaNacimiento = LocalDate.parse(fechaStr, formatter);
                LocalDate fechaActual = LocalDate.now();
                
                int edad = Period.between(fechaNacimiento, fechaActual).getYears();
                System.out.println("Edad calculada: " + edad + " años.");

                if (edad >= 60) {
                    tipoPasajero = "AdultoMayor";
                    System.out.println("Se asignará automaticamente la categoría: Adulto Mayor.");
                } else {
                    System.out.println("Se asignara la categoria: Regular.");
                }

            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto. El registro ha sido cancelado.");
                return; // Cortamos la ejecución si la fecha está mal
            }
        }

        boolean exito = personaService.registrarPasajero(cedula, nombre, tipoPasajero);
        if (!exito) {
            System.out.println("Hubo un problema al registrar el pasajero.");
        }
    }

    private void listarPersonal() {
        System.out.println("\n=== LISTADO DE CONDUCTORES ===");
        List<Conductor> conductores = personaService.listarConductores();
        if (conductores == null || conductores.isEmpty()) {
            System.out.println("No hay conductores registrados.");
        } else {
            for (Conductor c : conductores) {
                System.out.println("- " + c.toString()); 
            }
        }

        System.out.println("\n=== LISTADO DE PASAJEROS ===");
        List<Pasajero> pasajeros = personaService.listarPasajeros();
        if (pasajeros == null || pasajeros.isEmpty()) {
            System.out.println("No hay pasajeros registrados.");
        } else {
            for (Pasajero p : pasajeros) {
                System.out.println("- " + p.toString());
            }
        }
        
        System.out.println("(Listado de pasajeros pendiente de conexion con el Service)");
    }

    private void buscarPorCedula() {
        System.out.println("\n=== BUSCAR POR CEDULA ===");
        System.out.print("Ingrese la cedula a buscar: ");
        String cedula = sc.nextLine().trim();

        Conductor c = personaService.buscarConductorPorCedula(cedula);
        if (c != null) {
            System.out.println("Se encontro un CONDUCTOR:");
            System.out.println(c.toString());
            return;
        }

        Pasajero p = personaService.buscarPasajeroPorCedula(cedula);
        if (p != null) {
            System.out.println("Se encontro un PASAJERO:");
            System.out.println(p.toString());
            return;
        }
        

        System.out.println("No se encontro a ninguna persona (Conductor/Pasajero) con esa cedula.");
    }
}