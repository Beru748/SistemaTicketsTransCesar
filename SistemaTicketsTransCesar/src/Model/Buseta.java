package Model;

public class Buseta extends vehiculo{

    public Buseta(String placa, String modelo, int capacidadMaxima, int pasajerosActuales, double precioBaseTicket) {
        super(placa, modelo, 12, pasajerosActuales, 4500);
    }
    
    @Override
    public void imprimirDetalle(){
        System.out.println("Buseta: " + this.toString());
    }

    @Override
    public void calcularTotal(){
        double total = getPrecioBaseTicket() * getPasajerosActuales();

        System.out.println("Total recaudado de la Buseta: $" + total);
    }
    
}
