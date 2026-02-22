package SRET.example.registro_eveto_tecnologicos.application.service;

import SRET.example.registro_eveto_tecnologicos.application.port.out.UserRepository;
import SRET.example.registro_eveto_tecnologicos.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String login(String email, String password){
        log.info("[AuthService] login attempt for email:{}",email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if(!passwordEncoder.matches(password, user.passwordHash())){
            log.warn("[AuthService] Invalid password for email: {}",email);
            throw  new RuntimeException("Invalid email or password");
        }
        String token = jwtService.generateToken(user.email());
        log.info("[AuthService] login successful for email: {}",email);

        return token;
    }
}
