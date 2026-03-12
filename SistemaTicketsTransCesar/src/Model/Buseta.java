package Model;

public class Buseta extends Vehiculo{
    
    public Buseta(String placa, String modelo, int capacidadMaxima, int pasajerosActuales, double precioBaseTicket,
            String tipoVehiculo, boolean estado, String idConductor, String origen, String destino) {
        super(placa, modelo, 20, pasajerosActuales, 4500, "BUSETA", estado, idConductor,
                origen, destino);
    }

    @Override
    public void imprimirDetalle(){
        System.out.println("Buseta: " + this.toString());
    }
    
}
