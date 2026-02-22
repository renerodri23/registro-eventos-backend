package SRET.example.registro_eveto_tecnologicos.application.port.out;

import SRET.example.registro_eveto_tecnologicos.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
        /**
        * Guarda un nuevo usuario en el sistema.
        * @param user
        * @return
        */
        User save(User user);

        /**
         * Busca un usuario por su ID.
         *
         * @param userId
         * @return
         */
        Optional<User> findById(UUID userId);
        Optional<User> findByEmail(String email);
        List<User> findAll();
        boolean existsByEmail(String email);
        boolean existById(UUID userId);

}
