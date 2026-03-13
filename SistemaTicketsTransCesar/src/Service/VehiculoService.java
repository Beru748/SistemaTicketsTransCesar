package Service;
import java.util.List;
import Model.Vehiculo;
import DAO.VehiculosDAO;

public class VehiculoService {
    private VehiculosDAO vehiculosDAO;

    public VehiculoService() {
        this.vehiculosDAO = new VehiculosDAO();
    }

    //Metodo para registrar el vehiculo solo si la plca no esta repetida

    public String registarVehiculo(Vehiculo v){
        if(vehiculosDAO.buscarPorPlaca(v.getPlaca()) != null){
            return "Error: ya existe un vehiculo con la placa " + v.getPlaca();
        }
        boolean guardado = vehiculosDAO.guardarVehiculo(v);
        return guardado ? "Vehiculo guardado exitosamente." : "Hubo un error al guardar el vehiculo.";
    }

    //Metodo para mostrar todos los vehiculos tanto los activos como los inactivos

    public List<Vehiculo> listarTodo(){
        return vehiculosDAO.listarVehiculos();
    }

    //Metodo para consultar su disponibilidad o buscar por placa

    public String consultarDisponibilidad(String placa){
        Vehiculo v = vehiculosDAO.buscarPorPlaca(placa);

        if(v == null){
            return "Vehiculo encontrado.";
        }

        if(!v.isActivo()){
            return "El vehiculo se encuentra ARCHIVADO (No disponible)";
        }

        if(v.tieneCupo()){
            int cuposLibre = v.getCapacidadMaxima() - v.getPasajerosActuales();
            return "Disponible, cupos libres: " + cuposLibre;
        }else{
            return "Vehiculo lleno. No hay cupos";
        }
    }
}
