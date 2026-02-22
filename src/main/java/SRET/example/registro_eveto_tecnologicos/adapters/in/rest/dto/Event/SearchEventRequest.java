package SRET.example.registro_eveto_tecnologicos.adapters.in.rest.dto.Event;

import java.time.LocalDateTime;

public record SearchEventRequest(
        String nombre,
        String organizador,
        String ubicacion,
        LocalDateTime fecha
) {
}
