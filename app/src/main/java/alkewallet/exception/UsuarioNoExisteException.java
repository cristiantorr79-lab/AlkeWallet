package alkewallet.exception;

public class UsuarioNoExisteException extends RuntimeException {
    public UsuarioNoExisteException(String mensaje) {
        super(mensaje);
    }
}