package View;
import java.util.Scanner;
import Util.MenuUtil;

public class MenuPrincipal {
    private Scanner sc;
    private MenuVehiculos menuVehiculos;
    private MenuPersonal menuPersonal;
    private MenuReportes menuReportes;
    private MenuVentas menuVentas;


    public MenuPrincipal() {
        this.sc = new Scanner(System.in);
        this.menuVehiculos = new MenuVehiculos();
        this.menuPersonal = new MenuPersonal();
        this.menuReportes = new MenuReportes();
        this.menuVentas = new MenuVentas();
    }

    public void menuPrincipal(){
        int opcion;

        System.out.println("====================================================");
        System.out.println("| |                 MENU PRINCIPAL               | |");
        System.out.println("====================================================");
        System.out.println("| |1. Gestion de vehiculos.                      | |");
        System.out.println("| |2. Gestion de personal.                       | |");
        System.out.println("| |3. Operaciones de ventas.                     | |");
        System.out.println("| |4. Reportes y estadisticas.                   | |");
        System.out.println("| |5. Salir.                                     | |");
        System.out.println("====================================================");
        System.out.println("Escoga una opcion: ");
        opcion = sc.nextInt();
        sc.nextLine();

        do {
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
