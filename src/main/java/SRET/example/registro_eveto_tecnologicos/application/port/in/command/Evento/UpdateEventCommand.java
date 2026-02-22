package SRET.example.registro_eveto_tecnologicos.application.port.in.command.Evento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record UpdateEventCommand(
        UUID eventId,
        String nombre,
        String organizador,
        String descripcion,
        LocalDateTime fecha,
        String ubicacion
) {
}
