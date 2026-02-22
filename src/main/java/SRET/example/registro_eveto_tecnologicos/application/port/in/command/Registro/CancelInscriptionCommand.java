package SRET.example.registro_eveto_tecnologicos.application.port.in.command.Registro;

import java.util.UUID;

public record CancelInscriptionCommand(
        UUID inscriptionId,
        UUID userId
) {
}
