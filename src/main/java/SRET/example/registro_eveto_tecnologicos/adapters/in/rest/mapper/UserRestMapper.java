package SRET.example.registro_eveto_tecnologicos.adapters.in.rest.mapper;

import SRET.example.registro_eveto_tecnologicos.adapters.in.rest.dto.User.CreateUserRequest;
import SRET.example.registro_eveto_tecnologicos.adapters.in.rest.dto.User.UpdateUserRequest;
import SRET.example.registro_eveto_tecnologicos.adapters.in.rest.dto.User.UserResponse;
import SRET.example.registro_eveto_tecnologicos.domain.model.User;

import java.util.UUID;

public class UserRestMapper {
    public static User toUser(CreateUserRequest request){
        return new User(
                null,
                request.nombre(),
                request.apellido(),
                request.email(),
                request.password()
        );
    }

    public static User toUser(UUID userId, UpdateUserRequest request){
        return new User(
                userId,
                request.nombre(),
                request.apellido(),
                request.email(),
                null
        );
    }

    public static UserResponse toResponse(User user){
        return new UserResponse(
                user.userId(),
                user.nombre(),
                user.apellido(),
                user.email()
        );
    }
}
