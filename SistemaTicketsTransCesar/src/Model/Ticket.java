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
    private String estado;
 
    /**
     * Constructor para venta nueva sin festivo.
     */
    public Ticket(Pasajero pasajero, Vehiculo vehiculo, String origen, String destino) {
        this.pasajero    = pasajero;
        this.vehiculo    = vehiculo;
        this.origen      = origen;
        this.destino     = destino;
        this.fechaCompra = LocalDate.now();
        this.valorFinal  = vehiculo.getPrecioBaseTicket() * (1 - pasajero.calcularDescuento());
        this.estado      = "PAGADO";
    }
 
    /**
     * Constructor para venta con tarifa ajustada (festivo).
     */
    public Ticket(Pasajero pasajero, Vehiculo vehiculo, String origen,
                  String destino, double tarifaAplicada) {
        this.pasajero    = pasajero;
        this.vehiculo    = vehiculo;
        this.origen      = origen;
        this.destino     = destino;
        this.fechaCompra = LocalDate.now();
        this.valorFinal  = tarifaAplicada * (1 - pasajero.calcularDescuento());
        this.estado      = "PAGADO";
    }
 
    /**
     * Constructor para cargar desde archivo tickets.txt.
     */
    public Ticket(Pasajero pasajero, Vehiculo vehiculo, LocalDate fechaCompra,
                  String origen, String destino, double valorFinal, String estado) {
        this.pasajero    = pasajero;
        this.vehiculo    = vehiculo;
        this.fechaCompra = fechaCompra;
        this.origen      = origen;
        this.destino     = destino;
        this.valorFinal  = valorFinal;
        this.estado      = estado.toUpperCase();
    }
 
    
    public boolean cambiarEstado(String nuevoEstado) {
        // BUG CORREGIDO: antes this.estado.equals("ANULADO") fallaba si el estado
        // estaba guardado como "Anulado". Ahora usamos equalsIgnoreCase().
        if (this.estado.equalsIgnoreCase("ANULADO")) {
            System.out.println("[ERROR] El ticket ya está ANULADO y no puede modificarse.");
            return false;
        }
 
        String estadoNormalizado = nuevoEstado.toUpperCase();
 
        if (!estadoNormalizado.equals("PAGADO") && !estadoNormalizado.equals("CANCELADO")
                && !estadoNormalizado.equals("ANULADO") && !estadoNormalizado.equals("PENDIENTE")) {
            System.out.println("[ERROR] Estado inválido. Use: PAGADO, CANCELADO, ANULADO o PENDIENTE.");
            return false;
        }
 
        // Guardamos siempre en mayúsculas para consistencia
        this.estado = estadoNormalizado;
        System.out.println("[OK] Estado actualizado a: " + this.estado);
        return true;
    }
 
    @Override
    public void calcularTotal() {
        System.out.println("Valor total del ticket: $" + valorFinal);
    }
 
    @Override
    public void imprimirDetalle() {
        System.out.println("========== TICKET ==========");
        System.out.println("Pasajero  : " + pasajero.getNombre() + " (C.C. " + pasajero.getCedula() + ")");
        System.out.println("Tipo      : " + pasajero.getTipoPasajero());
        System.out.println("Vehículo  : " + vehiculo.getPlaca() + " - " + vehiculo.getRutaAsignada());
        System.out.println("Origen    : " + origen);
        System.out.println("Destino   : " + destino);
        System.out.println("Fecha     : " + fechaCompra.format(FORMATO_FECHA));
        System.out.printf ("Descuento : %.0f%%%n", pasajero.calcularDescuento() * 100);
        System.out.printf ("Valor     : $%.0f%n", valorFinal);
        System.out.println("Estado    : " + estado);
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
             + String.format("%.0f", valorFinal) + ";"
             + estado;
    }
 
    public Pasajero getPasajero()     { return pasajero; }
    public Vehiculo getVehiculo()     { return vehiculo; }
    public LocalDate getFechaCompra() { return fechaCompra; }
    public String getOrigen()         { return origen; }
    public String getDestino()        { return destino; }
    public double getValorFinal()     { return valorFinal; }
    public String getEstado()         { return estado; }
}