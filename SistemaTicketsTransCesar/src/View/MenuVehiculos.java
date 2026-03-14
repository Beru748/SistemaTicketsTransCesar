package View;
import java.util.Scanner;
import java.util.List;
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

    public MenuVehiculos() {
        this.sc = new Scanner(System.in);
        this.vehiculoService = new VehiculoService();
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
            System.out.println("| |4. Archivar vehículo.                         | |");
            System.out.println("| |5. Salir.                                     | |");
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
                        System.out.println("Saliendo de la gestion de vehiculos...");
                        MenuUtil.esperarEnter();
                        break;
                
                    default:
                        System.out.println("Esa opcion es invalida. Intentalo de nuevo.");
                        break;
                }
        }while (opcion != 5);
    }

    //Metodo para registrar un nuevo vehiculo

    private void registrarVehiculo(){
        System.out.println("\n=== REGISTRO DE NUEVO VEHICULO ===");
        System.out.println("Seleccione el tipo de vehiculo:");
        System.out.println("1. Bus");
        System.out.println("2. Buseta");
        System.out.println("3. Microbus");
        System.out.print("Opcion: ");
        int tipOpt = sc.nextInt();
        sc.nextLine();

        if(tipOpt < 1 || tipOpt >3){
            System.out.println("Tipo de vehiculo invalido. Cancelando registro");
            return;
        }

        System.out.println("Ingrese la placa: ");
        String placa = sc.nextLine().trim().toUpperCase();
        System.out.println("Ingrese el modelo: ");
        String modelo = sc.nextLine().trim();
        System.out.println("ingrese el ID del conductor asignado: ");
        String idConductor = sc.nextLine().trim();
        System.out.println("Ingrese la ciudad de origen:");
        String origen = sc.nextLine().trim();
        System.out.println("Ingrese la ciudad de destino:");
        String destino = sc.nextLine().trim();

        Vehiculo vehiculoNuevo = null;

        switch (tipOpt) {
            case 1:
                vehiculoNuevo = new Bus(placa, modelo, true, idConductor, origen, destino);
                break;
            case 2:
                vehiculoNuevo = new Buseta(placa, modelo, true, idConductor, origen, destino);
                break;
            case 3:
                vehiculoNuevo = new Microbus(placa, modelo, true, idConductor, origen, destino);
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

        // Formato tipo tabla simple para mejor legibilidad en consola
        System.out.printf("%-10s | %-10s | %-8s | %-12s | %-15s | %-10s\n", 
                        "TIPO", "PLACA", "MODELO", "CONDUCTOR", "RUTA (Ori-Des)", "ESTADO");
        System.out.println("====================================================================================");
        
        for (Vehiculo v : lista) {
            String ruta = v.getOrigen() + " - " + v.getDestino();
            String estadoStr = v.isActivo() ? "ACTIVO" : "ARCHIVADO";
            
            System.out.printf("%-10s | %-10s | %-8s | %-12s | %-15s | %-10s\n", 
                    v.getTipoVehiculo(), v.getPlaca(), v.getModelo(), v.getIdConductor(), ruta, estadoStr);
        }
        System.out.println("====================================================================================");
        System.out.println("Total de vehiculos registrados: " + lista.size());
    }

}


