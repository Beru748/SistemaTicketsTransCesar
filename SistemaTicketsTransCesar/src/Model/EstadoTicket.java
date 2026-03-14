package Model;

public enum EstadoTicket {
    PAGADO,
    CANCELADO,
    ANULADO,
    PENDIENTE;
 
    
    public static EstadoTicket fromString(String valor) {
        try {
            return EstadoTicket.valueOf(valor.toUpperCase());
        } catch (IllegalArgumentException e) {
            return PENDIENTE;
        }
    }
}
