package DAO;
import Model.Vehiculo;
import Model.Bus;
import Model.Buseta;
import Model.Microbus;
import Util.RutasArchivos;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class VehiculosDAO {

    //investigue y creo que es mejor hacer una sola clase donde se guarde cualquier tipo de vehiculo
    public void guardarVehiculo(Vehiculo vehiculo){
        String rutaArchivo = "";

        if (vehiculo instanceof Bus){
            rutaArchivo = RutasArchivos.BUS;
        }else if(vehiculo instanceof Buseta){
            rutaArchivo = RutasArchivos.BUSETA;
        }else if(vehiculo instanceof Microbus){
            rutaArchivo = RutasArchivos.MICROBUS;
        }else{
            System.out.println("Error: Vehiculo desconocido.");
        }

        try (FileWriter fw = new FileWriter(rutaArchivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter salida = new PrintWriter(bw)){
            
                String linea = vehiculo.getTipoVehiculo() + " | " +
                vehiculo.getModelo() + " | " +
                vehiculo.getPlaca() + " | " +
                vehiculo.getIdConductor() + " | " +
                vehiculo.getCapacidadMaxima() + " | " +
                vehiculo.getPasajerosActuales() + " | " +
                vehiculo.getPrecioBaseTicket() + " | " +
                vehiculo.isActivo() + " | " +
                vehiculo.getOrigen() + " | " +
                vehiculo.getDestino();
            
                salida.println(linea);

        } catch (IOException e) {
            System.out.println("Error al guardar en " + rutaArchivo + ": "+ e.getMessage());
        }
    }
}