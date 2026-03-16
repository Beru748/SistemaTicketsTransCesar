package View;
import java.util.Scanner;
import java.util.List;

import Service.RutaService;
import Service.VehiculoService;
import Model.Bus;
import Model.Buseta;
import Model.Microbus;
import Model.Vehiculo;
import Util.MenuUtil;


/*en esta clase se va a gestionar todos los tipos de vehiculos de la empresa.
* dejo el menu para que vean mas o menos cuales son las opciones que van a tener y se hagan
* una idea de los metodos que necesito llamar para mostrar las cosas en consola.*/

public class MenuVehiculos {
    private Scanner sc;
    private VehiculoService vehiculoService;
    private RutaService rutaService;

    public MenuVehiculos() {
        this.sc = new Scanner(System.in);
        this.vehiculoService = new VehiculoService();
        this.rutaService = new RutaService();
    }

    public void menuVehiculos(){
        int opcion;

        do{
            System.out.println("====================================================");
            System.out.println("| |             GESTION DE VEHICULOS             | |");
            System.out.println("====================================================");
            System.out.println("| |1. Registrar vehiculo.                        | |");
            System.out.println("| |2. Listado detallado.                         | |");
            System.out.println("| |3. Consultar disponibilidad.                  | |");
            System.out.println("| |4. Archivar vehiculo.                         | |");
            System.out.println("| |5. Registrar nueva ruta.                      | |");
            System.out.println("| |6. Salir.                                     | |");
            System.out.println("====================================================");
            System.out.println("Escoga una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            
                switch (opcion) {
                    case 1:
                        //Registrar un nuevo vehiculo
                        registrarVehiculo();
                        break;
                    case 2:
                        //Da una lista de la informacion de los vehiculos
                        mostrarListadoVehiculos();
                        break;
                    case 3:
                        //Consulta si el vehiculo esta activo
                        consultarDisponibilidadVehiculo();
                        break;
                    case 4:
                        //Le pongo "Archivar como para que tenga un nombre diferente a "Eliminar"
                        archivarVehiculo();
                        break;

                    case 5:
                        registrarNuevaRuta();
                        break;
                    case 6:
                        System.out.println("Saliendo de la gestion de vehiculos...");
                        MenuUtil.esperarEnter();
                        break;
                
                    default:
                        System.out.println("Esa opcion es invalida. Intentalo de nuevo.");
                        break;
                }
        }while (opcion != 6);
    }

    //Metodo para registrar un nuevo vehiculo
    private void registrarVehiculo(){
        System.out.println("\n=== REGISTRO DE NUEVO VEHICULO ===");
        
        if (!rutaService.hayRutasDisponibles()) {
            System.out.println("Error: No hay rutas registradas en el sistema.");
            System.out.println("El sistema ha cargado las rutas por defecto. Intente registrar nuevamente.");
            return; 
        }

        System.out.println("Seleccione el tipo de vehiculo:");
        System.out.println("1. Bus");
        System.out.println("2. Buseta");
        System.out.println("3. Microbus");
        System.out.print("Opcion: ");
        int tipOpt = sc.nextInt();
        sc.nextLine();

        if(tipOpt < 1 || tipOpt > 3){
            System.out.println("Tipo de vehiculo invalido. Cancelando registro.");
            return;
        }

        System.out.print("Ingrese la placa: ");
        String placa = sc.nextLine().trim().toUpperCase();
        System.out.print("Ingrese el modelo (Año): ");
        String modelo = sc.nextLine().trim();
        System.out.print("Ingrese el ID del conductor asignado: ");
        String idConductor = sc.nextLine().trim();

        System.out.println("\n=== SELECCIONE UNA RUTA ===");
        List<Model.Ruta> rutas = rutaService.obtenerTodasLasRutas();
        
        for (Model.Ruta r : rutas) {
            System.out.println("[" + r.getCodigoRuta() + "] " + r.getCiudadOrigen() + " -> " + r.getCiudadDestino());
        }

        System.out.print("Ingrese el codigo exacto de la ruta (Ej. R001): ");
        String codigo = sc.nextLine().trim();
        
        Model.Ruta rutaSeleccionada = rutaService.buscarPorCodigo(codigo);

        if (rutaSeleccionada == null) {
            System.out.println("Error: Codigo de ruta no valido o inexistente. Cancelando registro.");
            return;
        }

        Vehiculo vehiculoNuevo = null;
        switch (tipOpt) {
            case 1:
                vehiculoNuevo = new Bus(placa, modelo, true, idConductor, rutaSeleccionada);
                break;
            case 2:
                vehiculoNuevo = new Buseta(placa, modelo, true, idConductor, rutaSeleccionada);
                break;
            case 3:
                vehiculoNuevo = new Microbus(placa, modelo, true, idConductor, rutaSeleccionada);
                break;
        }

        String mensaje = vehiculoService.registarVehiculo(vehiculoNuevo);
        System.out.println("\n" + mensaje);
    }

    //Metodo para mostrar la lista de los vehiculos 

    private void mostrarListadoVehiculos(){
        System.out.println("\n=== LISTADO DETALLADO DE VEHICULOS ===");
        List<Vehiculo> lista = vehiculoService.listarTodo();

        if (lista.isEmpty()) {
            System.out.println("No hay vehiculos registrados en el sistema.");
            return;
        }

        
        System.out.printf("%-10s | %-10s | %-8s | %-12s | %-15s | %-10s\n", 
                        "TIPO", "PLACA", "MODELO", "CONDUCTOR", "RUTA (Ori-Des)", "ESTADO");
        System.out.println("====================================================================================");
        
        for (Vehiculo v : lista) {
        String rutaTexto = v.getRutaAsignada().getCiudadOrigen() + " - " + v.getRutaAsignada().getCiudadDestino();
        String estadoStr = v.isActivo() ? "ACTIVO" : "ARCHIVADO";
        
        System.out.printf("%-10s | %-10s | %-8s | %-12s | %-15s | %-10s\n", 
                v.getTipoVehiculo(), v.getPlaca(), v.getModelo(), v.getIdConductor(), rutaTexto, estadoStr);
    }
        System.out.println("====================================================================================");
        System.out.println("Total de vehiculos registrados: " + lista.size());
    }

    //Metodo para consultar la disponibilidad del vehiculo

    private void consultarDisponibilidadVehiculo() {
        System.out.println("\n=== CONSULTAR DISPONIBILIDAD ===");
        System.out.print("Ingrese la placa del vehiculo a consultar: ");
        String placa = sc.nextLine().trim().toUpperCase();

        String resultado = vehiculoService.consultarDisponibilidad(placa);
        System.out.println("\n" + resultado);
    }

    //Metodo para archivar el vehiculo
    
    private void archivarVehiculo(){
        System.out.println("\n=== ARCHIVAR VEHICULO ==");
        System.out.println("Nota: Un vehículo archivado no estara disponible para nuevas asignaciones.");
        System.out.print("Ingrese la placa del vehiculo a archivar: ");
        String placa = sc.nextLine().trim().toUpperCase();

        System.out.print("¿Está seguro de que desea archivar el vehiculo " + placa + "? (S/N): ");
        String confirmacion = sc.nextLine().trim().toUpperCase();

        if (confirmacion.equals("S")) {
            String resultado = vehiculoService.archivarVehiculo(placa);
            System.out.println("\n" + resultado);
        } else {
            System.out.println("Operacion cancelada.");
        }
    }

    // Metodo para capturar datos y registrar una ruta
    private void registrarNuevaRuta() {
        System.out.println("\n=== REGISTRAR NUEVA RUTA ===");
        
        System.out.print("Ingrese el codigo de la ruta: ");
        String codigo = sc.nextLine().trim();
        
        System.out.print("Ingrese la ciudad de origen: ");
        String origen = sc.nextLine().trim();
        
        System.out.print("Ingrese la ciudad de destino: ");
        String destino = sc.nextLine().trim();
        
        System.out.print("Ingrese la distancia en kilometros: ");
        double distancia = sc.nextDouble();
        
        System.out.print("Ingrese el tiempo estimado en minutos: ");
        int tiempo = sc.nextInt();
        sc.nextLine();
        
        String mensaje = rutaService.registrarRuta(codigo, origen, destino, distancia, tiempo);
        System.out.println("\n" + mensaje);
    }

}


