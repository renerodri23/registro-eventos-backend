package SRET.example.registro_eveto_tecnologicos.service;

import SRET.example.registro_eveto_tecnologicos.application.port.in.command.User.CreateUserCommand;
import SRET.example.registro_eveto_tecnologicos.application.port.out.UserRepository;
import SRET.example.registro_eveto_tecnologicos.application.service.UserService;
import SRET.example.registro_eveto_tecnologicos.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser_success(){
        CreateUserCommand command = new CreateUserCommand(
                "John",
                "Doe",
                "joedoe@gmail.com",
                "joepassword"

        );
        when(userRepository.existsByEmail("joedoe@gmail.com")).thenReturn(false);
        when(passwordEncoder.encode("joepassword")).thenReturn("hashed1234");
        when(userRepository.save(org.mockito.ArgumentMatchers.any())).thenAnswer(invocation -> invocation.getArgument(0));

        User result = userService.createUser(command);

        assertNotNull(result);
        assertEquals("joedoe@gmail.com", result.email());
        verify(passwordEncoder).encode("joepassword");
    }
}
