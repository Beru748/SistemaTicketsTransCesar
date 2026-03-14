package DAO;

import Util.RutasArchivos;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import Model.Ruta;

public class RutasDAO {
    public List<Ruta> listarRutas() {
        List<Ruta> rutas = new ArrayList<>();
        File archivo = new File(RutasArchivos.RUTAS);

        if (!archivo.exists()) {
            rutas.add(new Ruta("R001", "Valledupar", "Barranquilla", 300, 240));
            rutas.add(new Ruta("R002", "Valledupar", "Santa Marta", 230, 180));
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
