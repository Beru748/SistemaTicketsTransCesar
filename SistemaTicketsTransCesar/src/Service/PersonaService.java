package Service;

import DAO.ConductorDAO;
import DAO.PasajeroDAO;
import Model.Conductor;
import Model.Pasajero;
import Model.PasajeroAdultoMayor;
import Model.PasajeroEstudiante;
import Model.PasajeroRegular;
import java.time.LocalDate;   
import java.time.Period;  
import java.util.List;

public class PersonaService {
    private final ConductorDAO conductorDAO;
    private final PasajeroDAO  pasajeroDAO;
    private List<Conductor> conductores;
    private List<Pasajero>  pasajeros;
    
    public PersonaService() {
        this.conductorDAO = new ConductorDAO();
        this.pasajeroDAO  = new PasajeroDAO();
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
        if (c.getCedula().trim().equalsIgnoreCase(cedula.trim())) {
            return c;
        }
    }
    return null;
}

    public List<Conductor> listarConductores() {
        return conductores;
    }
    
    // ← MÉTODO ACTUALIZADO REQ 1: ahora recibe fechaNacimiento
    // y determina automáticamente si es AdultoMayor
    public boolean registrarPasajero(String cedula, String nombre,
                                      LocalDate fechaNacimiento, String tipo) {
        
        if (buscarPasajeroPorCedula(cedula) != null) {
    System.out.println("[ERROR] Ya existe un pasajero con esa cédula.");
    return false;
}
        Pasajero nuevo;

        // Si tiene 60 años o más el sistema lo clasifica automáticamente
        if (Period.between(fechaNacimiento, LocalDate.now()).getYears() >= 60) {
            nuevo = new PasajeroAdultoMayor(cedula, nombre, fechaNacimiento);
            System.out.println("[INFO] Clasificado automáticamente como Adulto Mayor (30% descuento).");
        } else {
            switch (tipo) {
                case "Estudiante" -> nuevo = new PasajeroEstudiante(cedula, nombre, fechaNacimiento);
                case "Regular"    -> nuevo = new PasajeroRegular(cedula, nombre, fechaNacimiento);
                default -> {
                    System.out.println("[ERROR] Tipo inválido: " + tipo
                            + ". Use: Regular o Estudiante.");
                    return false;
                }
            }
        }

        pasajeros.add(nuevo);
        pasajeroDAO.guardar(nuevo);
        System.out.println("[OK] Pasajero registrado: " + nombre
                + " | Tipo: " + nuevo.getTipoPasajero()
                + " | Edad: " + nuevo.getEdad() + " años");
        return true;
    }
    
    public Pasajero buscarPasajeroPorCedula(String cedula) {
    for (Pasajero p : pasajeros) {
        if (p.getCedula().trim().equalsIgnoreCase(cedula.trim())) {
            return p;
        }
    }
    return null;
}

    public List<Pasajero> listarPasajeros() {
        return pasajeros;
    }
}