package SRET.example.registro_eveto_tecnologicos.service;

import SRET.example.registro_eveto_tecnologicos.application.port.in.command.Registro.CreateInscriptionCommand;
import SRET.example.registro_eveto_tecnologicos.application.port.out.EventRepository;
import SRET.example.registro_eveto_tecnologicos.application.port.out.InscriptionRepository;
import SRET.example.registro_eveto_tecnologicos.application.port.out.UserRepository;
import SRET.example.registro_eveto_tecnologicos.application.service.InscriptionService;
import SRET.example.registro_eveto_tecnologicos.domain.model.Inscription;
import SRET.example.registro_eveto_tecnologicos.domain.model.enums.InscriptionStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InscriptionServiceTest {

    @Mock
    private InscriptionRepository inscriptionRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private InscriptionService inscriptionService;

    @Test
    void createInscription_success(){
        UUID userId = UUID.randomUUID();
        UUID eventId = UUID.randomUUID();

        CreateInscriptionCommand command =
                new CreateInscriptionCommand(userId, eventId);

        when(eventRepository.existById(eventId)).thenReturn(true);
        when(userRepository.existById(userId)).thenReturn(true);
        when(inscriptionRepository.existsByUserIdAndEventId(userId, eventId, InscriptionStatus.ACTIVE)).thenReturn(false);
        when(inscriptionRepository.save(org.mockito.ArgumentMatchers.any())).thenAnswer(invocation -> invocation.getArgument(0));

        Inscription result = inscriptionService.createInscription(command);

        assertNotNull(result);
        assertEquals(userId,result.usuarioId());
    }

}
