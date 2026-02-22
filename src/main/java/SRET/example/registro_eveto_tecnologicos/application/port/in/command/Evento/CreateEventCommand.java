package SRET.example.registro_eveto_tecnologicos.application.port.in.command.Evento;


import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateEventCommand(
        String nombre,
        String organizador,
        String descripcion,
        LocalDateTime fecha,
        String ubicacion
) {
}
