package Validaciones;


import java.time.LocalDate;
import java.time.LocalDateTime;

import exceptions.Mensajes.MensajesError;

public class Validacion {

    public static Long validarIdPositivo(Long id, String campo) {
        if (id == null) {
            throw new IllegalArgumentException(campo + " " + MensajesError.NOT_NULL);
        }
        if (id <= 0) {
            throw new IllegalArgumentException(campo+ ": " + MensajesError.VALUE_POSITIVE );
        }
        return id;
    }

    public static int validadCapacidad(int capacidad){
        if (capacidad <= 0){
            throw new IllegalArgumentException("Capacidad "+MensajesError.VALUE_POSITIVE);
        }
        return capacidad;
    }

    public static String validarTexto(String texto, String campo) {
        if (texto == null) {
            throw new IllegalArgumentException(campo + " " + MensajesError.NOT_NULL);
        }
        String limpio = texto.trim();
        if (limpio.isEmpty()) {
            throw new IllegalArgumentException(campo + " " + MensajesError.NOT_NULL);
        }
        return limpio;
    }

    public static void validarNoNulo(Object obj, String campo) {
        if (obj == null) {
            throw new IllegalArgumentException(campo + MensajesError.NOT_NULL);
        }
    }

    public static void validarFechaHoraConsistente(LocalDate fecha, LocalDateTime inicio, LocalDateTime fin) {
        if (!inicio.toLocalDate().equals(fecha) || !fin.toLocalDate().equals(fecha)) {
            throw new IllegalArgumentException("Las horas deben coincidir con la fecha indicada");
        }
        if (!inicio.isBefore(fin)) {
            throw new IllegalArgumentException(MensajesError.HORARIO_INVALIDO);
        }
    }
}
