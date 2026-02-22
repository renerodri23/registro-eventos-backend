package SRET.example.registro_eveto_tecnologicos.adapters.in.rest.dto.User;

public record CreateUserRequest(
        String nombre,
        String apellido,
        String email,
        String password

) {
}
