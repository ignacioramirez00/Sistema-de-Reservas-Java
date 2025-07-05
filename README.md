# 📅 Sistema de Gestión de Reservas de Salas

Este proyecto es una aplicación Java orientada a objetos para gestionar reservas de salas. Permite:

- ✅ Registrar reservas para salas específicas.
- 🔎 Consultar disponibilidad de salas.
- ❌ Cancelar reservas ya creadas.

---

## Requisitos

- Java 17 o superior
- IDE (recomendado: IntelliJ IDEA, Eclipse o VS Code con soporte Java)
- (Opcional) JUnit para pruebas

---

## Cómo ejecutar el proyecto

1. Cloná este repositorio:

````markdown
   ```bash
   git clone https://github.com/tu-usuario/sistema-reservas.git
   cd sistema-reservas
````

2. Abrí el proyecto con tu IDE preferido.

3. Ejecutá la clase principal 

---

## Funcionalidades principales

### 1. 📌 Realizar una reserva

Permite a un empleado registrar una reserva si la sala está disponible en el horario solicitado.

```java
public void realizarReserva(LocalDate fecha, LocalDateTime horaInicio, LocalDateTime horaFin, Long idSala, Long idEmpleado)
```

---

### 2. 🔍 Consultar salas disponibles

Muestra todas las salas que están disponibles en un rango de fecha y hora.

```java
public List<Sala> consultarSalasDisponibles(LocalDate fecha, LocalDateTime horaInicio, LocalDateTime horaFin)
```

---

### 3. ❌ Cancelar una reserva

Permite que un empleado cancele una reserva que él mismo creó.

```java
public void realizarCancelacion(Long idReserva, Long idEmpleado)
```

---

## 🧾 Ejemplos de uso

### 1. 📌 Realizar una reserva

```java
SistemaGestor sistema = new SistemaGestor();

sistema.realizarReserva(
    LocalDate.of(2025, 7, 10),
    LocalDateTime.of(2025, 7, 10, 14, 0),
    LocalDateTime.of(2025, 7, 10, 15, 30),
    1L,       // ID de la sala
    1001L     // ID del empleado
);
```

✅ Crea una nueva reserva si no hay conflicto horario.
❌ Lanza `ReservaSolapadaException` si hay una superposición con otra reserva.

---

### 2. 🔍 Consultar salas disponibles

```java
List<Sala> disponibles = sistema.consultarSalasDisponibles(
    LocalDate.of(2025, 7, 10),
    LocalDateTime.of(2025, 7, 10, 10, 0),
    LocalDateTime.of(2025, 7, 10, 11, 30)
);

for (Sala sala : disponibles) {
    System.out.println(sala.getNombre());
}
```

✅ Devuelve salas que están libres en el rango de fecha y hora.
❌ Lanza `NoExistenSalasException` si no hay salas o ninguna está disponible.

---

### 3. ❌ Cancelar una reserva

```java
sistema.realizarCancelacion(
    5L,     // ID de la reserva a cancelar
    1001L   // ID del empleado que creó la reserva
);
```

✅ Cancela la reserva si el empleado coincide.
❌ Lanza `OperacionNoAutorizadaException` si el empleado no creó la reserva.
❌ Lanza `InstanciaNoEncontradaException` si la reserva no existe.




Este proyecto está bajo la licencia MIT.
Hecho con ❤️ por Ignacio Ramírez.
