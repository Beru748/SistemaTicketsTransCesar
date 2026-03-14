package Model;

public class Microbus extends Vehiculo{
    
    public Microbus(String placa, String modelo, boolean estado, String idConductor, String origen, String destino) {
    super(placa, modelo, 12, 2400.0, "MICROBUS", estado, idConductor, origen, destino);
}

    @Override
    public void imprimirDetalle(){
        System.out.println("Microbus: " + this.toString());
    }
}
