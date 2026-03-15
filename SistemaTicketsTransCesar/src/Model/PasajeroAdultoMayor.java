package Model;
 
import java.time.LocalDate;
 
public class PasajeroAdultoMayor extends Pasajero {
 
    public PasajeroAdultoMayor(String cedula, String nombre, LocalDate fechaNacimiento) {
    super(fechaNacimiento, cedula, nombre);
}
 
    @Override
    public double calcularDescuento() {
        return 0.30;
    }
 
    @Override
    public String getTipoPasajero() {
        return "AdultoMayor";
    }
 
    @Override
    public void imprimirDetalle() {
        System.out.println("========== PASAJERO ==========");
        System.out.println("Cédula    : " + cedula);
        System.out.println("Nombre    : " + nombre);
        System.out.println("Tipo      : Adulto Mayor");
        System.out.println("Descuento : 30%");
        System.out.println("Edad      : " + getEdad() + " años");
        System.out.println("==============================");
    }
 
    @Override
    public String toString() {
        return cedula + ";" + nombre + ";AdultoMayor;" + fechaNacimiento.format(FORMATO_FECHA);
    }
}