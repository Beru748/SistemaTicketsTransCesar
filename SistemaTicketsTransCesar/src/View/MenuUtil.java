package View;

import java.util.Scanner;

public class MenuUtil {
    private static final Scanner sc = new Scanner(System.in);

    public static void esperarEnter() {
        System.out.println("\n[Presione ENTER para continuar...]");
        sc.nextLine();
    }
    
    // Ya que estás aquí, puedes agregar el de limpiar pantalla
    public static void limpiarPantalla() {
        for(int i = 0; i < 50; i++) System.out.println();
    }
}
