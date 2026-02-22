package SRET.example.registro_eveto_tecnologicos.adapters.in.rest.dto.Inscription;

import java.util.UUID;

public record CancelInscriptionRequest(
        UUID inscriptionId,
        UUID userId
) {
}
