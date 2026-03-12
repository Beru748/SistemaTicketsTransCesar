package View;
import java.util.Scanner;
import java.util.List;
import Service.ConductorService;
import Service.PasajeroService;
import Model.Pasajero;
import Model.Conductor;
import Util.MenuUtil;

/*en esta clase se va a gestionar tanto al personal de la empresa como a los pasajero. 
* dejo el menu para que vean mas o menos cuales son las opciones que van a tener y se hagan
* una idea de los metodos que necesito llamar para mostrar las cosas en consola.*/

public class MenuPersonal {
    private Scanner sc;
    private PasajeroService pasajeroService;
    private ConductorService conductorService;

    public MenuPersonal() {
        this.sc = new Scanner(System.in);
        this.pasajeroService = new PasajeroService();
        this.conductorService = new ConductorService();
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
                    break;
                case 2:
                    //Registrar pasajero
                    break;
                case 3:
                    //Listar personal activo
                    break;
                case 4:
                    //Buscar por la cedula al personal y a los pasjero por igual
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

}
