package SRET.example.registro_eveto_tecnologicos.adapters.in.rest.dto.User;

import java.util.UUID;

public record UserResponse(
        UUID userId,
        String nombre,
        String apellido,
        String email
) {
}
