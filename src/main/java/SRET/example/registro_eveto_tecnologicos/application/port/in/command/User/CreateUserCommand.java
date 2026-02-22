package SRET.example.registro_eveto_tecnologicos.application.port.in.command.User;



public record CreateUserCommand (
        String nombre,
        String apellido,
        String email,
        String passwordHash
){
}
