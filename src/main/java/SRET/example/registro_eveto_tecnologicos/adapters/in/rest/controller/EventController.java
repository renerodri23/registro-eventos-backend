package SRET.example.registro_eveto_tecnologicos.adapters.in.rest.controller;

import SRET.example.registro_eveto_tecnologicos.adapters.in.rest.dto.Event.CreateEventRequest;
import SRET.example.registro_eveto_tecnologicos.adapters.in.rest.dto.Event.EventResponse;
import SRET.example.registro_eveto_tecnologicos.adapters.in.rest.dto.Event.SearchEventRequest;
import SRET.example.registro_eveto_tecnologicos.adapters.in.rest.dto.Event.UpdateEventRequest;
import SRET.example.registro_eveto_tecnologicos.adapters.in.rest.mapper.EventRestMapper;
import SRET.example.registro_eveto_tecnologicos.application.port.in.command.Evento.CreateEventCommand;
import SRET.example.registro_eveto_tecnologicos.application.port.in.command.Evento.SearchEventCommand;
import SRET.example.registro_eveto_tecnologicos.application.port.in.command.Evento.UpdateEventCommand;
import SRET.example.registro_eveto_tecnologicos.application.service.EventService;
import SRET.example.registro_eveto_tecnologicos.domain.model.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static jakarta.persistence.GenerationType.UUID;
import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/RegistroEventoTecnologicos/evento")
public class EventController {
    private final EventService eventService;

    @PostMapping("/create")
    public EventResponse createEvent(@RequestBody CreateEventRequest request){
        log.info("Received request to create event: {}", request);
        CreateEventCommand command = new CreateEventCommand(
                request.nombre(),
                request.organizador(),
                request.descripcion(),
                request.fecha(),
                request.ubicacion()
        );
        Event event = eventService.createEvento(command);
        log.info("Event created successfully with ID: {}", event.eventId());
        return EventRestMapper.toResponse(event);
    }

    @PostMapping("/update")
    public EventResponse updateEvent(@RequestBody UpdateEventRequest request){
        log.info("Received request to update event: {}", request);
        UpdateEventCommand command = new UpdateEventCommand(
                request.eventId(),
                request.nombre(),
                request.organizador(),
                request.descripcion(),
                request.fecha(),
                request.ubicacion()
        );
        Event event = eventService.updateEvento(command);
        log.info("Event updated successfully with ID: {}", event.eventId());
        return EventRestMapper.toResponse(event);
    }

    @PostMapping("search-event")
    public ResponseEntity<List<Event>> searchEvent(@RequestBody(required = false) SearchEventRequest request){
        log.info("Received request to search event: {}", request);
        var cmd = new SearchEventCommand(
                Optional.ofNullable(request).map(SearchEventRequest::nombre).orElse(null),
                Optional.ofNullable(request).map(SearchEventRequest::organizador).orElse(null),
                Optional.ofNullable(request).map(SearchEventRequest::fecha).orElse(null),
                Optional.ofNullable(request).map(SearchEventRequest::ubicacion).orElse(null)

        );
        return ok(eventService.searchEvent(cmd));
    }

    @GetMapping("find-by-id/{eventId}")
    public EventResponse findEventById(@PathVariable UUID eventId){
        log.info("Received request to find event by ID: {}", eventId);
        return eventService.findById(eventId)
                .map(EventRestMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + eventId));
    }

    @GetMapping("/get-all-events")
    public List<EventResponse> getAllEvents(){
        log.info("Received request to get all events");
        return eventService.findAll().stream()
                .map(EventRestMapper::toResponse)
                .toList();
    }

}
