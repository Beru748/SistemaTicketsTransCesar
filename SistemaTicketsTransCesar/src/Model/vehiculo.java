package Model;

public abstract class vehiculo implements Imprimible,Calculable{
    
    protected String placa;
    protected String modelo;
    protected int capacidadMaxima;
    protected int pasajerosActuales;
    protected double precioBaseTicket;
    
    public vehiculo(String placa, String modelo, int capacidadMaxima, int pasajerosActuales, double precioBaseTicket) {
        this.placa = placa;
        this.modelo = modelo;
        this.capacidadMaxima = capacidadMaxima;
        this.pasajerosActuales = 0;
        this.precioBaseTicket = precioBaseTicket;
    }

    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }
    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }
    public int getPasajerosActuales() {
        return pasajerosActuales;
    }
    public void setPasajerosActuales(int pasajerosActuales) {
        this.pasajerosActuales = pasajerosActuales;
    }
    public double getPrecioBaseTicket() {
        return precioBaseTicket;
    }
    public void setPrecioBaseTicket(double precioBaseTicket) {
        this.precioBaseTicket = precioBaseTicket;
    }

    //Este metodo lo puede usar david a la hora de vender un ticket
    public boolean tieneCupo(){
        return pasajerosActuales < capacidadMaxima;
    }

    public void ocuparAsiento(){
        if (tieneCupo()){
            pasajerosActuales++;
        }
    }

    @Override
    public String toString() {
        return "vehiculo" + 
        "placa: " + placa + 
        "modelo: " + modelo + 
        "capacidadMaxima: " + capacidadMaxima + 
        "pasajerosActuales: " + pasajerosActuales + 
        "precioBaseTicket: " + precioBaseTicket;
    }
}
