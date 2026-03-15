package Model;
 
import java.time.LocalDate;
 
public class PasajeroRegular extends Pasajero {
 
    public PasajeroRegular(String cedula, String nombre, LocalDate fechaNacimiento) {
    super(fechaNacimiento, cedula, nombre);
}
 
    @Override
    public double calcularDescuento() {
        return 0.0;
    }
 
    @Override
    public String getTipoPasajero() {
        return "Regular";
    }
 
    @Override
    public void imprimirDetalle() {
        System.out.println("========== PASAJERO ==========");
        System.out.println("Cédula    : " + cedula);
        System.out.println("Nombre    : " + nombre);
        System.out.println("Tipo      : Regular");
        System.out.println("Descuento : 0%");
        System.out.println("Edad      : " + getEdad() + " años");
        System.out.println("==============================");
    }
 
    @Override
    public String toString() {
        return cedula + ";" + nombre + ";Regular;" + fechaNacimiento.format(FORMATO_FECHA);
    }
}