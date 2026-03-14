package Model;

public class Buseta extends Vehiculo{
    
    public Buseta(String placa, String modelo, boolean estado, String idConductor, Ruta rutaAsignada) {
    super(placa, modelo, 20, 4500.0, "BUSETA", estado, idConductor, rutaAsignada);
}

    @Override
    public void imprimirDetalle(){
        System.out.println("Buseta: " + this.toString());
    }
    
}
