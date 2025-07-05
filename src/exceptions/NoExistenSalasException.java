package exceptions;

public class NoExistenSalasException extends RuntimeException{

    public NoExistenSalasException(String mensaje){
        super(mensaje);
    }
}
