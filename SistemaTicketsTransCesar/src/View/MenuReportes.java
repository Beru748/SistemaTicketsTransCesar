package View;
import java.util.List;
import java.util.Scanner;
import Service.ReporteService;

/*en esta clase se va a gestionar los reportes de las ventas como: la suma de todos los tickets vendidos
* y cuanto dinero generaron los vehiculos.
* dejo el menu para que vean mas o menos cuales son las opciones que van a tener y se hagan
* una idea de los metodos que necesito llamar para mostrar las cosas en consola.*/

public class MenuReportes {
    private Scanner sc;
    private ReporteService reporteService;

    public MenuReportes() {
        this.sc = new Scanner(System.in);
        this.reporteService = new ReporteService();
    }

    public void menuReportes(){
        int opcion;

        System.out.println("====================================================");
        System.out.println("| |            REPORTES Y ESTADISTICAS           | |");
        System.out.println("====================================================");
        System.out.println("| |1. Total recaudado.                           | |");
        System.out.println("| |2. Reporte de los vehiculos.                  | |");
        System.out.println("| |3. Salir.                                     | |");
        System.out.println("====================================================");
        System.out.println("Escoga una opcion: ");
        opcion = sc.nextInt();
        sc.nextLine();

        do {
            switch (opcion) {
                case 1:
                    //Muestra la suma de todos los tickets vendidos
                    break;
                case 2:
                    //Muestra cuanto dinero generaron los vehiculos
                    break;
                case 3:
                    System.out.println("Saliendo del menu...");
                    MenuUtil.esperarEnter();
                    break;

                default:
                    System.out.println("Esa opcion es invalida. Intentalo de nuevo.");
                    break;
            }
        } while (opcion != 3);
    }
}
