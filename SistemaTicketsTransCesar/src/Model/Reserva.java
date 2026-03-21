package Model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reserva {
    private String Codigo;
    private Pasajero Pasajero;
    private Vehiculo Vehiculo;
    private LocalDateTime FechaCreacion;
    private LocalDate FechaViaje;
    private EstadoReserva Estado;

    public Reserva(String Codigo, Pasajero Pasajero, Vehiculo Vehiculo, LocalDateTime FechaCreacion, LocalDate FechaViaje, EstadoReserva Estado){
        this.Codigo = Codigo;
        this.Pasajero = Pasajero;
        this.Vehiculo = Vehiculo;
        this.FechaCreacion = FechaCreacion;
        this.FechaViaje = FechaViaje;
        this.Estado = EstadoReserva.ACTIVA;
    }

    public boolean EstaVencida(){
        return Duration.between(FechaCreacion, LocalDateTime.now()).toHours()>=24;
    }
//getters y setters

    public String getCodigo(){
        return Codigo;
    }

    public void setCodigo(String Codigo){
        this.Codigo=Codigo;
    }

    public Pasajero getPasajero(){
        return Pasajero;
    }

    public void SetPasajero(Pasajero Pasajero){
        this.Pasajero=Pasajero;
    }

    public Vehiculo getVehiculo(){
        return Vehiculo;
    }

    public void setVehiculo(Vehiculo Vehiculo){
        this.Vehiculo=Vehiculo;
    }

    public LocalDateTime getFechaCreacion(){
        return FechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime FechaCreacion){
        this.FechaCreacion=FechaCreacion;
    }

    public LocalDate getFechaViaje(){
        return FechaViaje;
    }

    public void setFechaViaje(LocalDate FechaViaje){
        this.FechaViaje=FechaViaje;
    }
}


