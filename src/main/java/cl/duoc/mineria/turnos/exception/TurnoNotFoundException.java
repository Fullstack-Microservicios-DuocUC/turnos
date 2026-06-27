package cl.duoc.mineria.turnos.exception;

public class TurnoNotFoundException extends RuntimeException {
    public TurnoNotFoundException(String mensaje) {
        super(mensaje);
    }
}