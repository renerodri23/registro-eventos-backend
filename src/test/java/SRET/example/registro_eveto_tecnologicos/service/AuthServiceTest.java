package SRET.example.registro_eveto_tecnologicos.service;

import SRET.example.registro_eveto_tecnologicos.application.port.out.UserRepository;
import SRET.example.registro_eveto_tecnologicos.application.service.AuthService;
import SRET.example.registro_eveto_tecnologicos.application.service.JwtService;
import SRET.example.registro_eveto_tecnologicos.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthService authService;

    @Test
    void login_successful(){
        String email = "test@gmail.com";
        String password = "password123";
        User user = new User(null, "Test", "User", email, "hashedPassword");

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(password,"hashedPassword")).thenReturn(true);
        when(jwtService.generateToken(email)).thenReturn("mocked-jwt-token");

        String result = authService.login(email,password);

        assertEquals("mocked-jwt-token",result);
        verify(userRepository).findByEmail(email);
    }
}
