package DAO;
import Service.PersonaService;
import Service.VehiculoService;
import Model.Reserva;
import Model.Pasajero;
import Model.Vehiculo;
import Model.Reserva.EstadoReserva;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import Util.RutasArchivos;
public class ReservaDAO {

    private PersonaService personaService = new PersonaService();
    private VehiculoService vehiculoService = new VehiculoService();


    public void guardar(Reserva reserva) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(RutasArchivos.RESERVAS, true))) {

            writer.write(reserva.toString());
            writer.newLine();

        } catch (IOException e) {
            System.out.println("[ERROR] No se pudo guardar la reserva: " + e.getMessage());
        }
    }
}