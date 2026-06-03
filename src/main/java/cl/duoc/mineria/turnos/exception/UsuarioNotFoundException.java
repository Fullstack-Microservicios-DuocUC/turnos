package cl.duoc.mineria.turnos.exception;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(String mensaje) {
        super(mensaje);
    }
}