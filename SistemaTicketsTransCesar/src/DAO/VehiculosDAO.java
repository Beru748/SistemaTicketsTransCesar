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

    private String determinarRuta(Vehiculo v){
        //Devuelve true si el objeto es de la clase especificada o una subclase (ej. perro instanceof Animal es true)
        if (v instanceof Bus){
            return RutasArchivos.BUS;
        }else if(v instanceof Buseta){
            return RutasArchivos.BUSETA;
        }else if(v instanceof Microbus){
            return RutasArchivos.MICROBUS;
        }
        return null;
    }
    public boolean guardarVehiculo(Vehiculo vehiculo){
        String rutaArchivo = determinarRuta(vehiculo);

        if(rutaArchivo == null){
            System.out.println("Error: Vehiculo desconocido.");
            return false;
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
                return true;

        } catch (IOException e) {
            System.out.println("Error al guardar en " + rutaArchivo + ": "+ e.getMessage());
            return false;
        }
    }

    //metodo para listar todos los vehiculos en el menu

    public List<Vehiculo> listarVehiculos(){
        List <Vehiculo> lista = new ArrayList<>();

        String[] rutas = { RutasArchivos.BUS, RutasArchivos.BUSETA, RutasArchivos.MICROBUS };

        for (String ruta : rutas) {
            try (BufferedReader br = new BufferedReader(new FileReader(ruta))){
            
                String linea;

                while ((linea = br.readLine()) != null) {
                    if(linea.trim().isEmpty()) continue;

                    String[] datos = linea.split("\\s*\\|\\s*");

                if (datos.length >= 9) {
                    //posiblemente te preguntes donde estan los demas datos faltantes pero, la cose es que
                    //como ya estan en el constructor definidas no es necesario ponerlas al leerlas

                    String tipo = datos[0]; // tipoVehiculo
                    String modelo = datos[1]; // modelo
                    String placa = datos[2]; // placa
                    String idConductor = datos[3]; // idConductor
                    // datos[4] es capacidadMaxima
                    // datos[5] es pasajerosActuales   a estas me refiero
                    // datos[6] es precioBaseTicket
                    boolean estado = Boolean.parseBoolean(datos[7]); // isActivo
                    String origen = datos[8]; // origen
                    String destino = datos[9]; // destino

                    Vehiculo v = null;

                    switch (tipo.toUpperCase()) {
                        case "BUS":
                            v = new Bus(placa, modelo, estado, idConductor, origen, destino);
                            break;
                        case "BUSETA":
                            v = new Buseta(placa, modelo, estado, idConductor, origen, destino);
                            break;
                        case "MICROBUS":
                            v = new Microbus(placa, modelo, estado, idConductor, origen, destino);
                            break;
                    }

                    if (v != null) {
                        lista.add(v);
                    }
                }
            }
            } catch (IOException e) {
                System.out.println("Error en listar los vehiculos: " + e.getMessage());
            }
        }
        return lista;
    }

    //Metodo para buscar por placa

    public Vehiculo buscarPorPlaca(String placa){
        List<Vehiculo> todos = listarVehiculos();

        for (Vehiculo vehiculo : todos) {
            if(vehiculo.getPlaca().equalsIgnoreCase(placa)){
                return vehiculo;
            }
        }
        return null;
    }

    //Metodo para archivar el vehiculo osea cambiar su estado a no disponible

    public void archivarVehiculo(String placa) {

        Vehiculo v = buscarPorPlaca(placa);
        if (v == null) {
            System.out.println("Error: Vehículo no encontrado.");
            return;
        }

        String ruta = determinarRuta(v);
        List<String> lineasActualizadas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("\\s*\\|\\s*");

                if (datos.length >= 10 && datos[2].equalsIgnoreCase(placa)) {
                    datos[7] = "No disponible";
                    linea = String.join(" | ", datos);
                }
                lineasActualizadas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(ruta, false))) {
            for (String l : lineasActualizadas) {
                pw.println(l);
            }
            System.out.println("Vehículo " + placa + " archivado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir: " + e.getMessage());
        }
    }
}