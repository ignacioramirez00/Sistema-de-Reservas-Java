package exceptions.Mensajes;

public class MensajesError {
    public static final String HORARIO_INVALIDO = "La hora de inicio debe ser anterior a la hora de fin.";
    public static final String INSTANCIA_EXISTE = "La instancia ya existe en el sistema";
    public static final String NOT_NULL = "No puede ser null";
    public static final String VALUE_POSITIVE = "Debe ser positivo";
    public static final String NO_AUTORIZADO = "El usuario no está autorizado para realizar la operación";
    public static final String SALAS_VACIAS = "No hay salas disponibles";
    public static final String RESERVA_SOLAPADA = "La reserva tiene conflicto de intersección con otra reserva hecha";
}
