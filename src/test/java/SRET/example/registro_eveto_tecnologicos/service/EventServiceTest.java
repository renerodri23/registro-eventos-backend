package SRET.example.registro_eveto_tecnologicos.service;

import SRET.example.registro_eveto_tecnologicos.application.port.in.command.Evento.CreateEventCommand;
import SRET.example.registro_eveto_tecnologicos.application.port.out.EventRepository;
import SRET.example.registro_eveto_tecnologicos.application.service.EventService;
import SRET.example.registro_eveto_tecnologicos.domain.model.Event;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @Test
    void createEvento_success(){
        CreateEventCommand command = new CreateEventCommand(
                "Evento de Prueba",
                "Organizador de Prueba",
                "Descripción de Prueba",
                LocalDateTime.now(),
                "Ubicación de Prueba"
        );

        when(eventRepository.save(org.mockito.ArgumentMatchers.any(Event.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Event result = eventService.createEvento(command);

        assertNotNull(result);
        assertEquals("Evento de Prueba", result.nombre());
    }

    @Test
    void createEvento_missingName(){
        CreateEventCommand command = new CreateEventCommand(
                null,
                "Organizador de Prueba",
                "Descripción de Prueba",
                LocalDateTime.now(),
                "Ubicación de Prueba"
        );
        assertThrows(IllegalArgumentException.class, () -> eventService.createEvento(command));
    }
}
