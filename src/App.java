import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import Empleado.Empleado;
import Sala.Sala;
import SistemaGestor.SistemaGestor;

public class App {
    public static void main(String[] args) {

        SistemaGestor gestorReservas = new SistemaGestor();

        try {
            // Crear empleados
            Empleado empleado1 = new Empleado(1L, "Juan", "Perez");
            Empleado empleado2 = new Empleado(2L, "Ana", "Gomez");

            // Agregar empleados
            gestorReservas.agregarEmpleado(empleado1);
            gestorReservas.agregarEmpleado(empleado2);

            // Crear salas con equipamiento
            List<String> eqSalaA = Arrays.asList("Proyector", "Pizarra", "HDMI");
            List<String> eqSalaB = Arrays.asList("Pantalla", "Webcam", "Microfono");

            Sala salaA = new Sala(1L, "Sala A", 10, eqSalaA);
            Sala salaB = new Sala(2L, "Sala B", 8, eqSalaB);

            // Agregar salas
            System.out.println("SALAS DE LA EMPRESA:");
            gestorReservas.agregarSala(salaA);
            gestorReservas.agregarSala(salaB);
            gestorReservas.mostrarSalas();

            // Definir fecha y horarios para reserva
            LocalDate fechaReserva = LocalDate.of(2025, 7, 4);
            LocalDateTime inicio1 = LocalDateTime.of(2025, 7, 4, 10, 0);
            LocalDateTime fin1 = LocalDateTime.of(2025, 7, 4, 11, 0);

            LocalDateTime inicio2 = LocalDateTime.of(2025, 7, 4, 11, 30);
            LocalDateTime fin2 = LocalDateTime.of(2025, 7, 4, 12, 30);

            // Consultar salas disponibles
            System.out.println("\nSalas disponibles de 10:00 a 11:00:");
            List<Sala> disponibles = gestorReservas.consultarSalasDisponibles(fechaReserva, inicio1, fin1);
            disponibles.forEach(System.out::println);

            // Realizar reservas
            System.out.println("\nReservando Sala A para Juan...");
            gestorReservas.realizarReserva(fechaReserva, inicio1, fin1, salaA.getIdSala(), empleado1.getNroLegajo());
            gestorReservas.realizarReserva(fechaReserva,
                    LocalDateTime.of(2025, 7, 4, 12, 0),
                    LocalDateTime.of(2025, 7, 4, 15, 45),
                    salaA.getIdSala(),
                    empleado1.getNroLegajo());

            System.out.println("\nReservando Sala B para Ana...");
            gestorReservas.realizarReserva(fechaReserva, inicio2, fin2, salaB.getIdSala(), empleado2.getNroLegajo());
            gestorReservas.realizarReserva(fechaReserva,
                    LocalDateTime.of(2025, 7, 4, 15, 30),
                    LocalDateTime.of(2025, 7, 4, 16, 0),
                    salaB.getIdSala(),
                    empleado2.getNroLegajo());

            // Mostrar reservas
            System.out.println("\nReservas en Sala A:");
            salaA.getReservas().forEach(System.out::println);

            System.out.println("\nReservas en Sala B:");
            salaB.getReservas().forEach(System.out::println);

            // Verificar salas disponibles nuevamente
            System.out.println("\nSalas disponibles de 10:00 a 11:00 después de reservar:");
            List<Sala> disponiblesDespues = gestorReservas.consultarSalasDisponibles(fechaReserva, inicio1, fin1);
            disponiblesDespues.forEach(System.out::println);

            // Cancelación válida
            System.out.println("\nCancelando reserva hecha por Juan...");
            gestorReservas.realizarCancelacion(23L, 2L);

            // Error esperado: sala duplicada
            // System.out.println("\nIntentando agregar sala repetida (Sala A)...");
            // gestorReservas.agregarSala(new Sala(1L, "Sala A", 10, eqSalaA));

            // Error esperado: Reserva con fecha incosistente
            /**
             * System.out.println("\nIntentando reservar con fechas inconsistentes...");
             * gestorReservas.realizarReserva(LocalDate.of(2025, 7, 4),
             * LocalDateTime.of(2025, 7, 5, 10, 0),
             * LocalDateTime.of(2025, 7, 5, 11, 0),
             * salaB.getIdSala(),
             * empleado2.getNroLegajo());
             */

            // Error esperado: La fecha de fin es menor al inicio
            /**
             * System.out.println("\nIntentando reservar con hora de inicio mayor a la de
             * fin...");
             * gestorReservas.realizarReserva(fechaReserva,
             * LocalDateTime.of(2025, 7, 4, 14, 0),
             * LocalDateTime.of(2025, 7, 4, 13, 0),
             * salaB.getIdSala(),
             * empleado2.getNroLegajo());
             */

            // Error esperado: cancelando una reserva que no existe
            // System.out.println("\nIntentando cancelar una reserva inexistente...");
            // gestorReservas.realizarCancelacion(99L, 1L);

            // Error esperado: Consultar salas cuando no existe ninguno
            // System.out.println("\nConsultando disponibilidad con sistema sin salas...");
            // SistemaGestor sistemaVacio = new SistemaGestor();
            // List<Sala> vacio = sistemaVacio.consultarSalasDisponibles(fechaReserva,
            // inicio1, fin1);
            // System.out.println(vacio);

            // Error esperado: se crea un empleado con datos invalidos
            // System.out.println("\nIntentando crear empleado con datos inválidos...");
            // Empleado invalido = new Empleado(1L, "", null);
            // System.out.println(invalido);

            // Error esperado: no se puede crear un salon con capacidad negativa
            /*
             * System.out.println("\nIntentando crear sala con capacidad negativa...");
             * Sala malaSala = new Sala(3L, "Sala C", -5, eqSalaA);
             * System.out.println(malaSala);
             */

            // Error esperado: empleado duplicado
            // System.out.println("\nIntentando agregar empleado repetido (Juan)...");
            // gestorReservas.agregarEmpleado(new Empleado(1L, "Juan", "Perez"));

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
