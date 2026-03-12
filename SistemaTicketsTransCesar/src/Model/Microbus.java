package Model;

public class Microbus extends Vehiculo{
    
    public Microbus(String placa, String modelo, int capacidadMaxima, int pasajerosActuales, double precioBaseTicket,
            String tipoVehiculo, boolean estado, String idConductor, String origen, String destino) {
        super(placa, modelo, 12, pasajerosActuales, 2400, "MICROBUS", estado, idConductor,
                origen, destino);
    }

    @Override
    public void imprimirDetalle(){
        System.out.println("Microbus: " + this.toString());
    }
}
