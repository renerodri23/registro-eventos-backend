package SRET.example.registro_eveto_tecnologicos.adapters.in.rest.controller;

import SRET.example.registro_eveto_tecnologicos.adapters.in.rest.dto.User.*;
import SRET.example.registro_eveto_tecnologicos.adapters.in.rest.mapper.UserRestMapper;
import SRET.example.registro_eveto_tecnologicos.application.port.in.command.User.CreateUserCommand;
import SRET.example.registro_eveto_tecnologicos.application.port.in.command.User.UpdateUserCommand;
import SRET.example.registro_eveto_tecnologicos.application.service.UserService;
import SRET.example.registro_eveto_tecnologicos.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/RegistroEventoTecnologicos/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public UserResponse createUser(@RequestBody CreateUserRequest request){
        log.info("Received request to create user: {}", request);
        CreateUserCommand command = new CreateUserCommand(
                request.nombre(),
                request.apellido(),
                request.email(),
                request.password()
        );

        User user = userService.createUser(command);
        log.info("User created successfully: {}", user);
        return UserRestMapper.toResponse(user);
    }

    @PostMapping("/update")
    public UserResponse updateUser(@RequestBody UpdateUserRequest request){
        log.info("Received request to update user: {}", request);
        UpdateUserCommand command = new UpdateUserCommand(
                request.userId(),
                request.nombre(),
                request.apellido(),
                request.email()
        );
        User user = userService.updateUser(command);
        log.info("User updated successfully: {}", user);
        return UserRestMapper.toResponse(user);
    }

    @GetMapping("find-by-id/{userId}")
    public UserResponse findUserByid(@PathVariable UUID userId){
        log.info("Received request to find user by id: {}",userId);
        return userService.findById(userId)
                .map(UserRestMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }

    @GetMapping("/find-by-email/{email}")
    public UserResponse findUserByEmail(@PathVariable String email){
        log.info("Received request to find user by email: {}",email);
        return userService.findByEmail(email)
                .map(UserRestMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    @GetMapping("/get-all-users")
    public List<UserResponse> getAllUsers(){
        log.info("Received request to get all users");
        return userService.findAll()
                .stream()
                .map(UserRestMapper::toResponse)
                .toList();
    }

    @PostMapping("/exists-by-email")
    public ExistsResponse existsByEmail(@RequestBody ExistsByEmailRequest request){
        log.info("Received request to check if user exists by email: {}", request.email());
        boolean exists = userService.existsByEmail(request.email());
        return new ExistsResponse(exists);
    }


}
