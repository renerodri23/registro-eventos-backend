package SRET.example.registro_eveto_tecnologicos.domain.model;

import SRET.example.registro_eveto_tecnologicos.domain.model.enums.InscriptionStatus;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Esta clase representa el registro de un usuario a un evento tecnologico
 *
 * @param inscriptionId
 * @param usuarioId
 * @param eventId
 * @param fechaRegistro
 */
public record Inscription(
        UUID inscriptionId,
        UUID usuarioId,
        UUID eventId,
        InscriptionStatus status,
        LocalDateTime fechaRegistro) {
}
