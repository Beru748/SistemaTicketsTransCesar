package service;

import DAO.ConductorDao;
import DAO.PasajeroDao;
import Model.Conductor;
import Model.Pasajero;
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
}
