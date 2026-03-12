package Model;

public class Microbus extends vehiculo{
    public Microbus(String placa, String modelo, int capacidadMaxima, int pasajerosActuales, double precioBaseTicket) {
        super(placa, modelo, 12, pasajerosActuales, 2400);
    }
    
    @Override
    public void imprimirDetalle(){
        System.out.println("Microbus: " + this.toString());
    }

    @Override
    public void calcularTotal(){
        double total = getPrecioBaseTicket() * getPasajerosActuales();

        System.out.println("Total recaudado del Microbus: $" + total);
    }
}
