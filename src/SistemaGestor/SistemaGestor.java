package SistemaGestor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import Empleado.Empleado;
import Reserva.Reserva;
import Sala.Sala;
import Validaciones.Validacion;
import exceptions.InstanciaNoEncontradaException;
import exceptions.NoExistenSalasException;
import exceptions.Mensajes.MensajesError;

public class SistemaGestor {

    private List<Sala> salas;
    private List<Empleado> empleados;
    private Long contadorReservas;

    public SistemaGestor() {
        this.salas = new ArrayList<>();
        this.empleados = new ArrayList<>();
        this.contadorReservas = 0L;
    }

    public Optional<Sala> buscarSalaId(Long idSala) {
        return salas.stream()
                .filter(sala -> sala.getIdSala().equals(idSala))
                .findFirst();
    }

    public Optional<Empleado> buscarEmpleadoId(Long idEmpleado) {
        return empleados.stream()
                .filter(e -> e.getNroLegajo().equals(idEmpleado))
                .findFirst();
    }

    public void agregarSala(Sala salaNueva) {
        Optional<Sala> existeSala = buscarSalaId(salaNueva.getIdSala());
        if (existeSala.isPresent()) {
            throw new InstanciaNoEncontradaException("Sala", salaNueva.getIdSala());
        } else {
            salas.add(salaNueva);
        }
    }

    public void agregarEmpleado(Empleado empleadoNuevo) {
        if (buscarEmpleadoId(empleadoNuevo.getNroLegajo()).isPresent()) {
            throw new InstanciaNoEncontradaException("Empleado", empleadoNuevo.getNroLegajo());
        }
        empleados.add(empleadoNuevo);
    }

    public void mostrarSalas() {
        this.salas.forEach(System.out::println);
    }

    public void mostrarEmpleados() {
        this.empleados.forEach(System.out::println);
    }

    public List<Sala> consultarSalasDisponibles(LocalDate fecha, LocalDateTime horaInicio, LocalDateTime horaFin) {
        Validacion.validarFechaHoraConsistente(fecha, horaInicio, horaFin);
        if (salas.isEmpty()) {
            throw new NoExistenSalasException(MensajesError.SALAS_VACIAS);
        }
        List<Sala> salas_disponilbles = new ArrayList<>();
        for (Sala iterador : salas) {
            if (iterador.estaDisponible(fecha, horaInicio, horaFin)) {
                salas_disponilbles.add(iterador);
            }
        }
        if(salas_disponilbles.isEmpty()){
            throw new NoExistenSalasException(MensajesError.SALAS_VACIAS);
        }
        return salas_disponilbles;
    }

    public void realizarReserva(LocalDate fecha, LocalDateTime hora_inicio, LocalDateTime hora_fin, Long idSala,
            Long idEmpleado) {
        Empleado empleado = this.buscarEmpleadoId(idEmpleado)
                .orElseThrow(() -> new InstanciaNoEncontradaException("Empleado", idEmpleado));
        Sala sala = this.buscarSalaId(idSala)
                .orElseThrow(() -> new InstanciaNoEncontradaException("Sala", idSala));

        Reserva reserva = empleado.crearReserva(sala, fecha, hora_inicio, hora_fin, contadorReservas++);
        sala.agregarReserva(reserva);
    }

    public void realizarCancelacion(Long idReserva, Long idEmpleado) {
        Empleado empleado = this.buscarEmpleadoId(idEmpleado)
                .orElseThrow(() -> new InstanciaNoEncontradaException("Empleado", idEmpleado));
        Reserva busquedaReserva = buscarReservaPorId(idReserva)
                .orElseThrow(() -> new InstanciaNoEncontradaException("Reserva",idReserva));
        empleado.cancelarReserva(busquedaReserva);
        System.out.println("Cancelacion existosa");
    }

    public Optional<Reserva> buscarReservaPorId(Long idReserva) {
        return salas.stream()
                .flatMap(sala -> sala.existeReserva(idReserva).stream())
                .findFirst();
    }
}
