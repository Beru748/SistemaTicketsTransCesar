package Model;

public class Bus extends vehiculo{

    public Bus(String placa, String modelo, int capacidadMaxima, int pasajerosActuales, double precioBaseTicket) {
        super(placa, modelo, 80, pasajerosActuales, 10000);
    }
    
    @Override
    public void imprimirDetalle(){
        System.out.println("Bus: " + this.toString());
    }

    @Override
    public void calcularTotal(){
        double total = getPrecioBaseTicket() * getPasajerosActuales();

        System.out.println("Total recaudado del Bus: $" + total);
    }
}
