package Model;

public class Buseta extends Vehiculo{
    
    public Buseta(String placa, String modelo, boolean estado, String idConductor, String origen, String destino) {
    super(placa, modelo, 20, 4500.0, "BUSETA", estado, idConductor, origen, destino);
}

    @Override
    public void imprimirDetalle(){
        System.out.println("Buseta: " + this.toString());
    }
    
}
