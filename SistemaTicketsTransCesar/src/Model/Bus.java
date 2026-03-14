package Model;

public class Bus extends Vehiculo{
    
    public Bus(String placa, String modelo, boolean estado, String idConductor, Ruta rutaAsignada) {
    super(placa, modelo, 80, 10000.0, "BUS", estado, idConductor, rutaAsignada);
}

    @Override
    public void imprimirDetalle(){
        System.out.println("Bus: " + this.toString());
    }
}
