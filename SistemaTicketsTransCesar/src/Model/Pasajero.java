
package Model;
 
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
 
public abstract class Pasajero extends Persona {
 
    protected static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected LocalDate fechaNacimiento;

    public Pasajero(LocalDate fechaNacimiento, String cedula, String nombre) {
        super(cedula, nombre);
        this.fechaNacimiento = fechaNacimiento;
    }
 
    
 
    public abstract double calcularDescuento();
    public abstract String getTipoPasajero();
 
    public int getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }
 
    public boolean esAdultoMayor() {
        return getEdad() >= 60;
    }
 
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
}