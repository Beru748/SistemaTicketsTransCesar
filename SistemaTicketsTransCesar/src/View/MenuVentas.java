package View;
import java.util.List;
import java.util.Scanner;
import Service.TicketsService;
import Model.Tickets;
import Util.MenuUtil;

/*en esta clase se va a gestionar las ventas de los tickts. 
* dejo el menu para que vean mas o menos cuales son las opciones que van a tener y se hagan
* una idea de los metodos que necesito llamar para mostrar las cosas en consola.*/

public class MenuVentas {
    private Scanner sc;
    private TicketsService ticketsService;

    public MenuVentas() {
        this.sc = new Scanner(System.in);
        this.ticketsService = new TicketsService();
    }

    public void menuVentas(){
        int opcion;

        System.out.println("====================================================");
        System.out.println("| |            OPERACIONES DE VENTAS             | |");
        System.out.println("====================================================");
        System.out.println("| |1. Venta de Ticket.                           | |");
        System.out.println("| |2. Historial de ventas.                       | |");
        System.out.println("| |3. Anulación de Ticket.                       | |");
        System.out.println("| |4. Salir.                                     | |");
        System.out.println("====================================================");
        System.out.println("Escoga una opcion: ");
        opcion = sc.nextInt();
        sc.nextLine();

        do {
            switch (opcion) {
                case 1:
                    //Venta de Ticket o el Registro de los tickets
                    break;
                case 2:
                    //Da una lista de la informacion de los tickets vendidos
                    break;
                case 3:
                    //Para cambiar el estado del ticket a "Cancelado"
                    break;
                case 4:
                    System.out.println("Saliendo del menu...");
                    MenuUtil.esperarEnter();
                    break;

                default:
                    System.out.println("Esa opcion es invalida. Intentalo de nuevo.");
                    break;
            }
        } while (opcion != 4);
    }
}
