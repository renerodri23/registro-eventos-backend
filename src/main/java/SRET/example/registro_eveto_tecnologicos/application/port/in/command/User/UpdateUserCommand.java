package SRET.example.registro_eveto_tecnologicos.application.port.in.command.User;

import java.util.UUID;

public record UpdateUserCommand (
        UUID userId,
        String nombre,
        String apellido,
        String email
){
}
