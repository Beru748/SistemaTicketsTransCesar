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

    public void sobrescribir(List<Reserva> reservas) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(RutasArchivos.RESERVAS))) {

            for (Reserva r : reservas) {
                writer.write(r.toString());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("[ERROR] No se pudo actualizar reservas: " + e.getMessage());
        }
    }

    public List<Reserva> CargarTodos(List<Pasajero> Pasajeros, List<Vehiculo> Vehiculos){

        List<Reserva> Lista = new ArrayList<>();
        File archivo = new File(RutasArchivos.RESERVAS);

        if(!archivo.exists()) return Lista;

        try(BufferedReader reader = new BufferedReader(new FileReader(archivo))){

            String Linea;

            while((Linea = reader.readLine()) != null){

                Linea = Linea.trim();

                if(!Linea.isEmpty()){

                    String[] campos = Linea.split(";");

                    if(campos.length == 6){

                        String Codigo = campos[0];
                        String CedulaPasajero = campos[1];
                        String PlacaVehiculo = campos[2];
                        LocalDateTime FechaCreacion = LocalDateTime.parse(campos[3]);
                        LocalDate FechaViaje = LocalDate.parse(campos[4]);
                        EstadoReserva Estado = EstadoReserva.valueOf(campos[5]);

                        Pasajero pasajero = personaService.buscarPasajeroPorCedula(Pasajeros, CedulaPasajero);
                        Vehiculo vehiculo = vehiculoService.buscarVehiculoPorPlaca(Vehiculos, PlacaVehiculo);

                        if(pasajero != null && vehiculo != null){

                            Reserva r = new Reserva(
                                    Codigo,
                                    pasajero,
                                    vehiculo,
                                    FechaCreacion,
                                    FechaViaje,
                                    Estado
                            );

                            Lista.add(r);
                        }
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("[ERROR] No se pudo leer reservas: " + e.getMessage());
        }

        return Lista;
    }


}