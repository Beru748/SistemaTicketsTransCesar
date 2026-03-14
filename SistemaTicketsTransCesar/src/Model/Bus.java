package Model;

public class Bus extends Vehiculo{
    
    public Bus(String placa, String modelo, boolean estado, String idConductor, String origen, String destino) {
    super(placa, modelo, 80, 10000.0, "BUS", estado, idConductor, origen, destino);
}

    @Override
    public void imprimirDetalle(){
        System.out.println("Bus: " + this.toString());
    }
}
