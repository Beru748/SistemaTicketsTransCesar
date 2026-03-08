
import modelo.Persona;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author COMPUMAX
 */
public abstract class Pasajero extends Persona{

    public Pasajero() {
    }

    public Pasajero(String id, String nombre, String apellido) {
        super(id, nombre, apellido);
    }
    
    public abstract double calcularDescuento();
}
