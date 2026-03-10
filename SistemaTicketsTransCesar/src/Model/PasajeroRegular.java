/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class PasajeroRegular extends Pasajero {

    public PasajeroRegular(String cedula, String nombre, String apellido) {
        super(cedula, nombre, apellido);
    }

    public double calcularDescuento() {
        return 0.0;
    }

    public void Imprimible() {
        System.out.println("----- PASAJERO REGULAR -----");
        System.out.println("Cedula: " + cedula);
        System.out.println("Nombre: " + nombre);
        System.out.println("Apelido: " + apellido);
        System.out.println("Descuento: 0%");
    }
}
