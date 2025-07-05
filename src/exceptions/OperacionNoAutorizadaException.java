package exceptions;

public class OperacionNoAutorizadaException extends RuntimeException {
    
    public OperacionNoAutorizadaException(String mensaje){
        super(mensaje);
    }
}
