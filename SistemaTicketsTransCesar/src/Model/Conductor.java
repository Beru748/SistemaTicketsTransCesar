
import modelo.Persona;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author COMPUMAX
 */
public class Conductor extends Persona {
    private String numeroLicencia;
    private String categoriaLicencia;

    public Conductor(String numeroLicencia, String categoriaLicencia) {
        this.numeroLicencia = numeroLicencia;
        this.categoriaLicencia = categoriaLicencia;
    }

    public Conductor(String numeroLicencia, String categoriaLicencia, String id, String nombre, String apellido) {
        super(id, nombre, apellido);
        this.numeroLicencia = numeroLicencia;
        this.categoriaLicencia = categoriaLicencia;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public String getCategoriaLicencia() {
        return categoriaLicencia;
    }

    public void setCategoriaLicencia(String categoriaLicencia) {
        this.categoriaLicencia = categoriaLicencia;
    }

    public void imprimirDetalle() {
        System.out.println("----- CONDUCTOR -----");
        System.out.println("Cedula: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Numero Licencia: " + numeroLicencia);
        System.out.println("Categoria Licencia: " + categoriaLicencia);
    }
    
    
}
