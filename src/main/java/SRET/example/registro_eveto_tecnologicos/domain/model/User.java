package SRET.example.registro_eveto_tecnologicos.domain.model;

import java.util.UUID;

/**
 * Esta clase representa a los usuarios que se registraran en los eventos tecnologicos
 * @param userId
 * @param nombre
 * @param apellido
 * @param email
 * @param passwordHash
 */
public record User(
        UUID userId,
        String nombre,
        String apellido,
        String email,
        String passwordHash
) {
}
