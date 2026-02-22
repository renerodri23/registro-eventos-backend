package SRET.example.registro_eveto_tecnologicos.adapters.in.rest.dto.Inscription;

import SRET.example.registro_eveto_tecnologicos.domain.model.enums.InscriptionStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record InscriptionResponse(
        UUID inscriptionId,
        UUID userId,
        UUID eventoId,
        LocalDateTime fechaInscripcion,
        InscriptionStatus status
) {
}
