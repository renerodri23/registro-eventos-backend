package SRET.example.registro_eveto_tecnologicos.adapters.in.rest.dto.Event;

import java.time.LocalDateTime;
import java.util.UUID;

public record UpdateEventRequest(
        UUID eventId,
        String nombre,
        String organizador,
        String descripcion,
        LocalDateTime fecha,
        String ubicacion
) {
}
