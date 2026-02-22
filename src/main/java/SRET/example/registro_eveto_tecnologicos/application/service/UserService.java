package SRET.example.registro_eveto_tecnologicos.application.service;

import SRET.example.registro_eveto_tecnologicos.application.port.in.UserUseCase;
import SRET.example.registro_eveto_tecnologicos.application.port.in.command.User.CreateUserCommand;
import SRET.example.registro_eveto_tecnologicos.application.port.in.command.User.UpdateUserCommand;
import SRET.example.registro_eveto_tecnologicos.application.port.out.UserRepository;
import SRET.example.registro_eveto_tecnologicos.domain.model.User;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class UserService implements UserUseCase {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public User createUser(CreateUserCommand command) {
        if (command.nombre() == null || command.nombre().isBlank()){
            log.info("[UserService] El nombre es obligatorio");
            throw new IllegalArgumentException("El nombre es obligatorio");}

        if (command.apellido() == null || command.apellido().isBlank()){
            log.info("[UserService] El apellido es obligatorio");
            throw new IllegalArgumentException("El apellido es obligatorio");}

        if (command.email() == null || command.email().isBlank()){
            log.info("[UserService] El email es obligatorio");
            throw new IllegalArgumentException("El email es obligatorio");}

        if (command.passwordHash() == null || command.passwordHash().isBlank()){
            log.info("[UserService] La contraseña es obligatoria");
            throw new IllegalArgumentException("La contraseña es obligatoria");}

        if (!command.email().matches("^[A-Za-z0-9+_.-]+@(.+)$")){
            log.info("[UserService] El email no es válido");
            throw new IllegalArgumentException("El email no es válido");}

        if (userRepository.existsByEmail(command.email().toLowerCase())) {
            log.info("[UserService] El email ya está registrado: {}", command.email());
            throw new IllegalArgumentException("El email ya está registrado");
        }
        String hashedPassword = passwordEncoder.encode(command.passwordHash());

        User user = new User(
                null,
                command.nombre(),
                command.apellido(),
                command.email(),
                hashedPassword
        );
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UpdateUserCommand command) {
        User u = userRepository.findById(command.userId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        if (command.email() != null && !command.email().equalsIgnoreCase(u.email())){
            if(userRepository.existsByEmail(command.email().toLowerCase())){
                log.info("[UserService] El email ya está registrados: {}", command.email());
                throw new IllegalArgumentException("El email ya está registrado");
            }
        }
        User updatedUser = new User(
                u.userId(),
                command.nombre() != null ? command.nombre() : u.nombre(),
                command.apellido() != null ? command.apellido() : u.apellido(),
                command.email() != null ? command.email() : u.email(),
                u.passwordHash()
        );
        log.info("[UserService] Updated user: {}", updatedUser);
        return userRepository.save(updatedUser);

    }
    public Optional<User> findById(UUID userId){
        if(userId==null){
            log.info("[UserService] El userId no puede ser nulo");
            throw new IllegalArgumentException("El userId no puede ser nulo");
        }
        return  userRepository.findById(userId);
    }

    public Optional<User> findByEmail(String email){
        if(email == null || email.isBlank()){
            log.info("[UserService] El email no puede ser nulo o vacío");
            throw new IllegalArgumentException("El email no puede ser nulo o vacío");
        }
        return userRepository.findByEmail(email.toLowerCase());
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public boolean existsByEmail(String email){
        if(email == null || email.isBlank()){
            log.info("[UserService] El email no puede ser nulo o vacíos");
            throw new IllegalArgumentException("El email no puede ser nulo o vacío");
        }
        return userRepository.existsByEmail(email.toLowerCase());
    }


}
