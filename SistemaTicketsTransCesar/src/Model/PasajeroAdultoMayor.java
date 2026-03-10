/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author COMPUMAX
 */
public class PasajeroAdultoMayor extends Pasajero {
    public PasajeroAdultoMayor(String cedula, String nombre, String apellido) {
        super(cedula, nombre);
    }

    
    public double calcularDescuento() {
        return 0.30;
    }

    
    public void imprimirDetalle() {
        System.out.println("----- PASAJERO ADULTO MAYOR -----");
        System.out.println("Cedula: " + cedula);
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Descuento: 30%");
    }
}
