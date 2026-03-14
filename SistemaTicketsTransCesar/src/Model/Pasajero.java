
package Model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public abstract class Pasajero extends Persona{

    protected static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected LocalDate fechaNacimiento;
 
    public Pasajero(String cedula, String nombre, LocalDate fechaNacimiento) {
        super(cedula, nombre);
        this.fechaNacimiento = fechaNacimiento;
    }
 
    /**
     * Retorna el porcentaje de descuento aplicable.
     * Cada subclase lo implementa con polimorfismo.
     * @return 
     */
    public abstract double calcularDescuento();
 
    /**
     * Retorna el tipo de pasajero como texto.
     * @return 
     */
    public abstract String getTipoPasajero();
 
    /**
     * REQ 1: Calcula la edad actual a partir de la fecha de nacimiento.
     * @return 
     */
    public int getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }
 
    /**
     * REQ 1: Determina si el pasajero tiene 60 años o más.
     * El sistema lo calcula automáticamente sin que el usuario lo seleccione.
     * @return 
     */
    public boolean esAdultoMayor() {
        return getEdad() >= 60;
    }
 
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
}
