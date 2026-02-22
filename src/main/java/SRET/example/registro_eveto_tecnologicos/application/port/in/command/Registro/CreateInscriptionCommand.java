package SRET.example.registro_eveto_tecnologicos.application.port.in.command.Registro;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateInscriptionCommand(
        UUID userId,
        UUID eventId
) {
}
