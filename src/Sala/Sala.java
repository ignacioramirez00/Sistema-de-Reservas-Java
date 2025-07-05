package Sala;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Errores.InstanciaNoEncontradaException;
import Errores.InstanciaRepetidaException;
import Errores.Mensajes.MensajesError;
import Reserva.Reserva;
import Validaciones.Validacion;

public class Sala {

    /**
     * Cada sala tiene un nombre,
     * una capacidad máxima de personas, y puede contar con equipamiento
     * como proyector, pantalla, etc.
     */

    private Long idSala;
    private String nombre;
    private int capacidadMaxima;
    private List<String> equipamiento;
    private List<Reserva> reservas;

    public Sala(Long idSala, String nombre, int capacidadMaxima, List<String> equipamiento) {
        this.idSala = Validacion.validarIdPositivo(idSala,"Sala id");
        this.nombre = Validacion.validarTexto(nombre,"Nombre");
        this.capacidadMaxima = Validacion.validadCapacidad(capacidadMaxima);
        this.equipamiento = equipamiento;
        this.reservas = new ArrayList<>();
    }

    public void agregarReserva(Reserva reserva) {
        if(existeReserva(reserva.getIdReserva()).isPresent()){
            throw new InstanciaRepetidaException(MensajesError.INSTANCIA_EXISTE);
        }
        reservas.add(reserva);
        System.out.println("Reserva exitosa");
    }

    public Optional<Reserva> existeReserva(Long idReserva){
        return reservas.stream()
                        .filter( r -> r.getIdReserva().equals(idReserva))
                        .findFirst();
    }

    public boolean estaDisponible(LocalDate fecha, LocalDateTime horaInicioReserva, LocalDateTime horaFinReserva) {
        return reservas.stream()
                .filter(r -> r.getFecha().equals(fecha))
                .noneMatch(r -> r.reservaConflicto(fecha, horaInicioReserva, horaFinReserva));
    }

    public Long getIdSala() {
        return idSala;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public void agregarEquipamiento(String equipamiento) {
        this.equipamiento.add(equipamiento);
    }

    public List<String> getEquipamiento() {
        return equipamiento;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void borrarReserva(Long idReserva) {
        boolean borrado = reservas.removeIf(r -> r.getIdReserva().equals(idReserva));
        if(!borrado){
            throw new InstanciaNoEncontradaException("Reserva",idReserva);
        }
    }


    @Override
    public String toString() {
        return "Sala id: " + idSala + " Nombre: " + nombre + " Capacidad: " + capacidadMaxima + " Equipamiento: "
                + this.getEquipamiento();
    }
}
