/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author COMPUMAX
 */
public class Tickets implements Imprimible, Calculable {
    private Pasajero pasajero;
    private Vehiculo vehiculo;
    private String fecha;
    private String origen;
    private String destino;
    private double valorFinal;

    public Ticket(Pasajero pasajero, Vehiculo vehiculo, String fecha, String origen, String destino) {
        this.pasajero = pasajero;
        this.vehiculo = vehiculo;
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
        this.valorFinal = calcularTotal();
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public String getFecha() {
        return fecha;
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

    
    public double calcularTotal() {

        double tarifa = vehiculo.getTarifaBase();
        double descuento = pasajero.calcularDescuento();

        return tarifa - (tarifa * descuento);
    }

    
    public void Imprimible() {

        System.out.println("----- TICKET -----");
        System.out.println("Pasajero: " + pasajero.getNombre());
        System.out.println("Vehiculo: " + vehiculo.getPlaca());
        System.out.println("Fecha: " + fecha);
        System.out.println("Origen: " + origen);
        System.out.println("Destino: " + destino);
        System.out.println("Valor Final: " + valorFinal);
    }
}
