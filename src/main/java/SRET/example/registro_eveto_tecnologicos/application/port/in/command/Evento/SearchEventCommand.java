package SRET.example.registro_eveto_tecnologicos.application.port.in.command.Evento;


import java.time.LocalDate;
import java.time.LocalDateTime;

public record SearchEventCommand(
        String nombre,
        String organizador,
        LocalDateTime fecha,
        String ubicacion
) {
}
