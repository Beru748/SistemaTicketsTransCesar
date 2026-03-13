
package Model;

public abstract class Pasajero extends Persona{

    public Pasajero(String cedula, String nombre) {
        super(cedula, nombre);
    }
    
    public abstract double calcularDescuento();
    public abstract String getTipoPasajero();
}
