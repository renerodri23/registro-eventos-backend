package SRET.example.registro_eveto_tecnologicos.adapters.in.rest.dto.Inscription;

import java.util.UUID;

public record CreateInscriptionRequest(
        UUID userId,
        UUID eventId
) {
}
