
package Model;


public class Conductor extends Persona {

     private String numeroLicencia;
    private String categoriaLicencia;
 
    public Conductor(String cedula, String nombre, String numeroLicencia, String categoriaLicencia) {
        super(cedula, nombre);
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
 
    public boolean tieneLicencia() {
        return numeroLicencia != null && !numeroLicencia.trim().isEmpty();
    }
 
    @Override
    public void imprimirDetalle() {
        System.out.println("========== CONDUCTOR ==========");
        System.out.println("Cédula          : " + cedula);
        System.out.println("Nombre          : " + nombre);
        System.out.println("N° Licencia     : " + (tieneLicencia() ? numeroLicencia : "Sin licencia registrada"));
        System.out.println("Categoría       : " + (categoriaLicencia != null ? categoriaLicencia : "N/A"));
        System.out.println("================================");
    }
 
    
    @Override
    public String toString() {
        return cedula + ";" + nombre + ";" + numeroLicencia + ";" + categoriaLicencia;
    }
}

