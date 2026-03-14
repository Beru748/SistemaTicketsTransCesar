package Service;

import DAO.ConductorDao;
import DAO.PasajeroDao;
import Model.Conductor;
import Model.Pasajero;
import Model.PasajeroAdultoMayor;
import Model.PasajeroEstudiante;
import Model.PasajeroRegular;
import java.util.List;

public class PersonaService {
    private final ConductorDao conductorDAO;
    private final PasajeroDao  pasajeroDAO;

    private List<Conductor> conductores;
    private List<Pasajero>  pasajeros;
    
    public PersonaService() {
        this.conductorDAO = new ConductorDao();
        this.pasajeroDAO  = new PasajeroDao();
        // Carga automática al iniciar: sesiones anteriores disponibles desde el primer momento
        this.conductores  = conductorDAO.cargarTodos();
        this.pasajeros    = pasajeroDAO.cargarTodos();
    }
    
    public boolean registrarConductor(String cedula, String nombre,
                                       String numeroLicencia, String categoria) {
        if (numeroLicencia == null || numeroLicencia.trim().isEmpty()) {
            System.out.println("[ERROR] No se puede registrar un conductor sin número de licencia.");
            return false;
        }

        Conductor nuevo = new Conductor(cedula, nombre, numeroLicencia, categoria);
        conductores.add(nuevo);
        conductorDAO.guardar(nuevo);
        System.out.println("[OK] Conductor registrado: " + nombre);
        return true;
    }
    
    public boolean conductorTieneLicencia(String cedula) {
        Conductor c = buscarConductorPorCedula(cedula);
        if (c == null) {
            System.out.println("[ERROR] No existe un conductor con cédula: " + cedula);
            return false;
        }
        if (!c.tieneLicencia()) {
            System.out.println("[ERROR] El conductor " + c.getNombre() + " no tiene licencia registrada.");
            return false;
        }
        return true;
    }
    
    public Conductor buscarConductorPorCedula(String cedula) {
        for (Conductor c : conductores) {
            if (c.getCedula().equals(cedula)) return c;
        }
        return null;
    }

    public List<Conductor> listarConductores() {
        return conductores;
    }
    
    
    public boolean registrarPasajero(String cedula, String nombre, String tipo) {
        Pasajero nuevo;

        switch (tipo) {
            case "Estudiante"  -> nuevo = new PasajeroEstudiante(cedula, nombre);
            case "AdultoMayor" -> nuevo = new PasajeroAdultoMayor(cedula, nombre);
            case "Regular"     -> nuevo = new PasajeroRegular(cedula, nombre);
            default -> {
                System.out.println("[ERROR] Tipo de pasajero inválido: " + tipo
                        + ". Use: Regular, Estudiante o AdultoMayor");
                return false;
            }
        }

        pasajeros.add(nuevo);
        pasajeroDAO.guardar(nuevo);
        System.out.println("[OK] Pasajero registrado: " + nombre + " (" + tipo + ")");
        return true;
    }
    
    public Pasajero buscarPasajeroPorCedula(String cedula) {
        for (Pasajero p : pasajeros) {
            if (p.getCedula().equals(cedula)) return p;
        }
        return null;
    }

    public List<Pasajero> listarPasajeros() {
        return pasajeros;
    }
}
