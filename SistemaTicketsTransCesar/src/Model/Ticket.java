package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ticket implements Imprimible, Calculable {
private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    private Pasajero pasajero;
    private Vehiculo vehiculo;
    private LocalDate fechaCompra;
    private String origen;
    private String destino;
    private double valorFinal;

    public Ticket(Pasajero pasajero, Vehiculo vehiculo, String origen, String destino) {
        this.pasajero = pasajero;
        this.vehiculo = vehiculo;
        this.fechaCompra = fechaCompra;
        this.origen = origen;
        this.destino = destino;
        this.valorFinal = valorFinal;
    }

    public Ticket(Pasajero pasajero, Vehiculo vehiculo, LocalDate fechaCompra, String origen, String destino, double valorFinal) {
        this.pasajero = pasajero;
        this.vehiculo = vehiculo;
        this.fechaCompra = LocalDate.now();
        this.origen = origen;
        this.destino = destino;
        this.valorFinal = calcularTotal();
    }

    
    public double calcularTotal() {
        double tarifaBase = vehiculo.getTarifaBase();
        double descuento  = pasajero.calcularDescuento();
        return tarifaBase * (1 - descuento);
    }
    
    @Override
    public void imprimirDetalle() {
        System.out.println("========== TICKET ==========");
        System.out.println("Pasajero  : " + pasajero.getNombre() + " (C.C. " + pasajero.getCedula() + ")");
        System.out.println("Tipo      : " + pasajero.getTipoPasajero());
        System.out.println("Vehículo  : " + vehiculo.getPlaca() + " - " + vehiculo.getRuta());
        System.out.println("Origen    : " + origen);
        System.out.println("Destino   : " + destino);
        System.out.println("Fecha     : " + fechaCompra.format(FORMATO_FECHA));
        System.out.printf ("Descuento : %.0f%%%n", pasajero.calcularDescuento() * 100);
        System.out.printf ("Valor     : $%.0f%n", valorFinal);
        System.out.println("============================");
    }
}