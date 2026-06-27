package cl.duoc.mineria.turnos.exception;

public class ServicioExternoNoDisponibleException extends RuntimeException {
    public ServicioExternoNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}