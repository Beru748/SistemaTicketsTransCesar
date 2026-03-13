
package Model;


public class Conductor extends Persona {

    private String numeroLicencia;
    private String categoriaLicencia;

    public Conductor(String numeroLicencia, String categoriaLicencia, String cedula, String nombre) {
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
}