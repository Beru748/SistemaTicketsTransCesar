package Model;

public abstract class Vehiculo implements Imprimible,Calculable{
    
    protected String placa, modelo, tipoVehiculo, idConductor, origen, destino;
    protected int capacidadMaxima, pasajerosActuales;
    protected double precioBaseTicket;
    protected boolean estado;

    public Vehiculo(String placa, String modelo, int capacidadMaxima, double precioBaseTicket,
                    String tipoVehiculo, boolean estado, String idConductor, String origen, String destino) {
        this.placa = placa;
        this.modelo = modelo;
        this.capacidadMaxima = capacidadMaxima;
        this.pasajerosActuales = 0; 
        this.precioBaseTicket = precioBaseTicket;
        this.tipoVehiculo = tipoVehiculo;
        this.estado = estado;
        this.idConductor = idConductor;
        this.origen = origen;
        this.destino = destino;
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

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public boolean isActivo() {
        return estado;
    }

    public void setActivo(boolean estado) {
        this.estado = estado;
    }

    public String getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(String idConductor) {
        this.idConductor = idConductor;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
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
    public void calcularTotal(){
        double total = getPrecioBaseTicket() * getPasajerosActuales();

        System.out.println("Total recaudado del Vehiculo: $" + total);
    }

    @Override
    public String toString() {
        return "vehiculo [placa=" + placa + ", modelo=" + modelo + ", capacidadMaxima=" + capacidadMaxima
                + ", pasajerosActuales=" + pasajerosActuales + ", precioBaseTicket=" + precioBaseTicket
                + ", tipoVehiculo=" + tipoVehiculo + ", estado=" + estado + ", idConductor=" + idConductor + ", origen="
                + origen + ", destino=" + destino + "]";
    }

    
}
