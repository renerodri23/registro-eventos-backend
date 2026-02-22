package SRET.example.registro_eveto_tecnologicos.adapters.in.rest.dto.Event;

import java.time.LocalDateTime;

public record CreateEventRequest(
        String nombre,
        String organizador,
        String descripcion,
        LocalDateTime fecha,
        String ubicacion
) {
}
