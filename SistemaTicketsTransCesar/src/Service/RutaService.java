package Service;

import DAO.RutasDAO;
import Model.Ruta;
import java.util.List;

public class RutaService {
    private RutasDAO rutasDAO;

    public RutaService() {
        this.rutasDAO = new RutasDAO();
    }

    // Metodo para registrar una nueva ruta en el sistema
    public String registrarRuta(String codigo, String origen, String destino, double distancia, int tiempo) {
        if (buscarPorCodigo(codigo) != null) {
            return "Error: Ya existe una ruta registrada con el codigo " + codigo.toUpperCase();
        }

        Ruta nuevaRuta = new Ruta(codigo.toUpperCase(), origen, destino, distancia, tiempo);

        rutasDAO.guardarRuta(nuevaRuta);
        
        return "Ruta " + codigo.toUpperCase() + " (" + origen + " -> " + destino + ") registrada exitosamente.";
    }

    public List<Ruta> obtenerTodasLasRutas() {
        return rutasDAO.listarRutas();
    }

    public Ruta buscarPorCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return null;
        }
        return rutasDAO.buscarPorCodigo(codigo.toUpperCase());
    }

    public boolean hayRutasDisponibles() {
        List<Ruta> rutas = rutasDAO.listarRutas();
        return rutas != null && !rutas.isEmpty();
    }
}
