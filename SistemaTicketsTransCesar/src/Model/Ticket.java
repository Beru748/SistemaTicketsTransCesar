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
    private EstadoTicket estado;

    public Ticket(Pasajero pasajero, Vehiculo vehiculo, String origen, String destino) {
        this.pasajero = pasajero;
        this.vehiculo = vehiculo;
        this.fechaCompra = LocalDate.now();
        this.origen = origen;
        this.destino = destino;
        this.valorFinal = vehiculo.getTarifaBase() * (1 - pasajero.calcularDescuento());
        this.estado      = EstadoTicket.PAGADO;
    }

    public Ticket(Pasajero pasajero, Vehiculo vehiculo, LocalDate fechaCompra, String origen, String destino, double valorFinal, EstadoTicket estado) {
        this.pasajero = pasajero;
        this.vehiculo = vehiculo;
        this.fechaCompra = fechaCompra;
        this.origen = origen;
        this.destino = destino;
        this.valorFinal  = valorFinal;
        this.estado      = estado;
    }

    
@Override
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
    
    @Override
    public String toString() {
        return pasajero.getCedula() + ";"
             + pasajero.getNombre() + ";"
             + pasajero.getTipoPasajero() + ";"
             + vehiculo.getPlaca() + ";"
             + fechaCompra.format(FORMATO_FECHA) + ";"
             + origen + ";"
             + destino + ";"
             + String.format("%.0f", valorFinal);
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public double getValorFinal() {
        return valorFinal;
    }

}