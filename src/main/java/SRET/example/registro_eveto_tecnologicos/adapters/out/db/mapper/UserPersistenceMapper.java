package SRET.example.registro_eveto_tecnologicos.adapters.out.db.mapper;

import SRET.example.registro_eveto_tecnologicos.adapters.out.db.entity.UserEntity;
import SRET.example.registro_eveto_tecnologicos.domain.model.User;

public class UserPersistenceMapper {

    public static User toDomainModel(UserEntity entity){
        return new User(
                entity.getUserId(),
                entity.getNombre(),
                entity.getApellido(),
                entity.getEmail(),
                entity.getPasswordHash()
        );
    }
    public static UserEntity toEntity(User user){
        UserEntity entity = new UserEntity();
        entity.setUserId(user.userId());
        entity.setNombre(user.nombre());
        entity.setApellido(user.apellido());
        entity.setEmail(user.email());
        entity.setPasswordHash(user.passwordHash());
        return entity;
    }
}
