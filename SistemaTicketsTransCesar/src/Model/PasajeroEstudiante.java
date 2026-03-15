package Model;
 
import java.time.LocalDate;
 
public class PasajeroEstudiante extends Pasajero {
 
    public PasajeroEstudiante(LocalDate fechaNacimiento, String cedula, String nombre) {
    super(fechaNacimiento, cedula, nombre);
}
 
    @Override
    public double calcularDescuento() {
        return 0.15;
    }
 
    @Override
    public String getTipoPasajero() {
        return "Estudiante";
    }
 
    @Override
    public void imprimirDetalle() {
        System.out.println("========== PASAJERO ==========");
        System.out.println("Cédula    : " + cedula);
        System.out.println("Nombre    : " + nombre);
        System.out.println("Tipo      : Estudiante");
        System.out.println("Descuento : 15%");
        System.out.println("Edad      : " + getEdad() + " años");
        System.out.println("==============================");
    }
 
    @Override
    public String toString() {
        return cedula + ";" + nombre + ";Estudiante;" + fechaNacimiento.format(FORMATO_FECHA);
    }
}
