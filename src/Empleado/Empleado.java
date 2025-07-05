package Empleado;
import java.time.LocalDate;
import java.time.LocalDateTime;

import Errores.OperacionNoAutorizadaException;
import Errores.ReservaSolapadaException;
import Errores.Mensajes.MensajesError;
import Reserva.Reserva;
import Sala.Sala;
import Validaciones.Validacion;

public class Empleado {

    private Long nroLegajo;
    private String nombre;
    private String apellido;

    public Empleado(Long nroLegajo, String nombre, String apellido) {
        this.nroLegajo = Validacion.validarIdPositivo(nroLegajo,"nroLegajo");
        this.nombre = Validacion.validarTexto(nombre,"Nombre");
        this.apellido = Validacion.validarTexto(apellido,"Apellido");
    }

    public Reserva crearReserva(Sala sala, LocalDate fecha, LocalDateTime inicio, LocalDateTime fin,Long id) {
        Validacion.validarFechaHoraConsistente(fecha, inicio, fin);
        if (!sala.estaDisponible(fecha, inicio, fin)) {
            throw new ReservaSolapadaException(MensajesError.RESERVA_SOLAPADA);
        }
        return new Reserva(id, fecha, inicio, fin, this, sala);
    }

    public void cancelarReserva(Reserva reserva) {
        if (!this.nroLegajo.equals(reserva.getEmpleado().getNroLegajo())) {
            throw new OperacionNoAutorizadaException(MensajesError.NO_AUTORIZADO);
        }
        reserva.getSala().borrarReserva(reserva.getIdReserva());
    }

    public Long getNroLegajo() {
        return nroLegajo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setNroLegajo(Long nroLegajo) {
        this.nroLegajo = Validacion.validarIdPositivo(nroLegajo,"NroLegajo");
    }

    public void setNombre(String nombre) {
        this.nombre = Validacion.validarTexto(nombre,"Nombre");
    }

    public void setApellido(String apellido) {
        this.apellido = Validacion.validarTexto(apellido, "Apellido");
    }

    public String toString() {
        return "Empleado [nroLegajo=" + nroLegajo + ", nombre=" + nombre + ", apellido=" + apellido + "]";
    }
}
