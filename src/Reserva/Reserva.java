package Reserva;

import java.time.LocalDate;
import java.time.LocalDateTime;

import Empleado.Empleado;
import Sala.Sala;

public class Reserva {

    private final Long idReserva;
    private LocalDate fecha;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;
    private Empleado empleado;
    private Sala sala;

    public Reserva(Long idReserva, LocalDate fecha, LocalDateTime horaInicio, LocalDateTime horaFin, Empleado empleado,
            Sala sala) {
        this.idReserva = idReserva;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.empleado = empleado;
        this.sala = sala;
    }

    public boolean reservaConflicto(LocalDate fechaNueva, LocalDateTime bInicio, LocalDateTime bFin) {
        if (!this.fecha.equals(fechaNueva))
            return false;
        return horaInicio.isBefore(bFin) && bInicio.isBefore(horaFin);
    }

    public Long getIdReserva() {
        return idReserva;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalDateTime getFechaHoraInicio() {
        return horaInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return horaFin;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public Sala getSala() {
        return sala;
    }

    public String toString() {
        return "Reserva [idReserva=" + idReserva + ", fecha=" + fecha + ", Hora inicio=" + horaInicio + ", Hora fin="
                + horaFin + ", empleado=" + empleado.getNombre() + ", sala=" + sala.getIdSala() + "]";
    }
}
