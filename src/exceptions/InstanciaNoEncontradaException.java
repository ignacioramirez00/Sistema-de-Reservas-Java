package exceptions;

public class InstanciaNoEncontradaException extends RuntimeException {

    public InstanciaNoEncontradaException(String entidad, Object identificador) {
        super(generarMensaje(entidad, identificador));
    }

    private static String generarMensaje(String entidad, Object identificador) {
        return String.format("No se encontro la instancia de '%s' con el identificador: %s", entidad, identificador);
    }
}
