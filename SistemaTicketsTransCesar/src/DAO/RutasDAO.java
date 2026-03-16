package DAO;

import Util.RutasArchivos;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import Model.Ruta;

public class RutasDAO {

public void guardarRuta(Ruta ruta) {
        try (FileWriter fw = new FileWriter(RutasArchivos.RUTAS, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
            
            out.println(ruta.getCodigoRuta() + " | " + 
                        ruta.getCiudadOrigen() + " | " + 
                        ruta.getCiudadDestino() + " | " +
                        ruta.getDistanciaKm() + " | " +
                        ruta.getTiempoEstimadoMinutos() + " | ");
            
        } catch (IOException e) {
            System.out.println("Error al guardar la ruta: " + e.getMessage());
        }
    }

    public List<Ruta> listarRutas() {
        List<Ruta> rutas = new ArrayList<>();
        File archivo = new File(RutasArchivos.RUTAS);

        if (!archivo.exists() || archivo.length() == 0) {
            Ruta r1 = new Ruta("R001", "Valledupar", "Barranquilla", 30000, 240);
            Ruta r2 = new Ruta("R002", "Valledupar", "Santa Marta", 25000, 180);
            
            guardarRuta(r1);
            guardarRuta(r2);
            
            rutas.add(r1);
            rutas.add(r2);
            return rutas;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split("\\s*\\|\\s*");
                if (d.length >= 5) {
                    rutas.add(new Ruta(d[0], d[1], d[2], Double.parseDouble(d[3]), Integer.parseInt(d[4])));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer rutas: " + e.getMessage());
        }
        return rutas;
    }

    public Ruta buscarPorCodigo(String codigo) {
        return listarRutas().stream()
                .filter(r -> r.getCodigoRuta().equalsIgnoreCase(codigo))
                .findFirst()
                .orElse(null);
    }
}
