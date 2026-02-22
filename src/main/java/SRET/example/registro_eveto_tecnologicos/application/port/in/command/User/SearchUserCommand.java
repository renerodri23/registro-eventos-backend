package SRET.example.registro_eveto_tecnologicos.application.port.in.command.User;

public record SearchUserCommand(
        String nombre,
        String apellido,
        String email
) {
}
