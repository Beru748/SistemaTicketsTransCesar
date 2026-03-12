package Model;

public class Bus extends Vehiculo{
    
    public Bus(String placa, String modelo, int capacidadMaxima, int pasajerosActuales, double precioBaseTicket,
            String tipoVehiculo, boolean estado, String idConductor, String origen, String destino) {
        super(placa, modelo, 80, pasajerosActuales, 10000, "BUS", estado, idConductor,
                origen, destino);
    }

    @Override
    public void imprimirDetalle(){
        System.out.println("Bus: " + this.toString());
    }
}
