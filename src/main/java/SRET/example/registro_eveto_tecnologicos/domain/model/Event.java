package SRET.example.registro_eveto_tecnologicos.domain.model;

import java.util.UUID;

/**
 * Esta clase representa a los eventos que se utilizaran con el sistema de registro
 *
 * @param eventId
 * @param nombre
 * @param descripcion
 * @param fecha
 * @param ubicacion
 */
public record Event(
        UUID eventId,
        String nombre,
        String organizador,
        String descripcion,
        java.time.LocalDateTime fecha,
        String ubicacion){
}
