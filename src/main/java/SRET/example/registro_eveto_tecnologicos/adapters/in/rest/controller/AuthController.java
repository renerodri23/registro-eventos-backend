package SRET.example.registro_eveto_tecnologicos.adapters.in.rest.controller;

import SRET.example.registro_eveto_tecnologicos.adapters.in.rest.dto.auth.LoginRequest;
import SRET.example.registro_eveto_tecnologicos.adapters.in.rest.dto.auth.LoginResponse;
import SRET.example.registro_eveto_tecnologicos.application.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/RegistroEventoTecnologicos/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        log.info("Login request received for email: {}",request.email());

        String token = authService.login(request.email(), request.password());

        return new LoginResponse(token, "Login successful");
    }
}
