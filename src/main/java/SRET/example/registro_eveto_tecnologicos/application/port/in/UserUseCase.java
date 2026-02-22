package SRET.example.registro_eveto_tecnologicos.application.port.in;

import SRET.example.registro_eveto_tecnologicos.application.port.in.command.User.CreateUserCommand;
import SRET.example.registro_eveto_tecnologicos.application.port.in.command.User.UpdateUserCommand;

import SRET.example.registro_eveto_tecnologicos.domain.model.User;

public interface UserUseCase {

    /**
     * Crea un nuevo usuario en el sistema.
     * @param command
     * @return
     */
    User createUser(CreateUserCommand command);

    /**
     * Actualiza la información de un usuario existente en el sistema.
     * @param command
     * @return
     */
    User updateUser(UpdateUserCommand command);



}
