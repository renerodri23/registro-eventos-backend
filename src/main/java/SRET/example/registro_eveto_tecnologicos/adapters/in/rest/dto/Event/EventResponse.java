package SRET.example.registro_eveto_tecnologicos.adapters.in.rest.dto.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record EventResponse(
        UUID eventoId,
        String nombre,
        String organizador,
        String descripcion,
        LocalDateTime fecha,
        String ubicacion

) {

}
