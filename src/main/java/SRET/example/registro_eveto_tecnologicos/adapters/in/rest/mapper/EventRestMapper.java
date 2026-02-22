package SRET.example.registro_eveto_tecnologicos.adapters.in.rest.mapper;

import SRET.example.registro_eveto_tecnologicos.adapters.in.rest.dto.Event.CreateEventRequest;
import SRET.example.registro_eveto_tecnologicos.adapters.in.rest.dto.Event.EventResponse;
import SRET.example.registro_eveto_tecnologicos.adapters.in.rest.dto.Event.SearchEventRequest;
import SRET.example.registro_eveto_tecnologicos.adapters.in.rest.dto.Event.UpdateEventRequest;
import SRET.example.registro_eveto_tecnologicos.application.port.in.command.Evento.SearchEventCommand;
import SRET.example.registro_eveto_tecnologicos.domain.model.Event;

public class EventRestMapper {
    public static Event toEvent(CreateEventRequest request){
        return new Event(
                null,
                request.nombre(),
                request.organizador(),
                request.descripcion(),
                request.fecha(),
                request.ubicacion()
        );
    }

    public static Event toEvent(UpdateEventRequest request){
        return new Event(
                request.eventId(),
                request.nombre(),
                request.organizador(),
                request.descripcion(),
                request.fecha(),
                request.ubicacion()
        );
    }

    public static SearchEventCommand toSearchEvent(SearchEventRequest request){
        return new SearchEventCommand(
                request.nombre(),
                request.organizador(),
                request.fecha(),
                request.ubicacion()
        );
    }

    public static EventResponse toResponse(Event event){
        return new EventResponse(
                event.eventId(),
                event.nombre(),
                event.organizador(),
                event.descripcion(),
                event.fecha(),
                event.ubicacion()
        );
    }
}
