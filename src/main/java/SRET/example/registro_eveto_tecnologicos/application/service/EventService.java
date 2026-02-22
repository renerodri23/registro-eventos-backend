package SRET.example.registro_eveto_tecnologicos.application.service;

import SRET.example.registro_eveto_tecnologicos.application.port.in.EventUseCase;
import SRET.example.registro_eveto_tecnologicos.application.port.in.command.Evento.CreateEventCommand;
import SRET.example.registro_eveto_tecnologicos.application.port.in.command.Evento.SearchEventCommand;
import SRET.example.registro_eveto_tecnologicos.application.port.in.command.Evento.UpdateEventCommand;
import SRET.example.registro_eveto_tecnologicos.application.port.out.EventRepository;
import SRET.example.registro_eveto_tecnologicos.domain.model.Event;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class EventService implements EventUseCase {

    /**
     * Repositorio para acceder a los datos de eventos. Este repositorio se utiliza para realizar operaciones
     */
    private final EventRepository eventRepository;

    /**
     * Este método crea un nuevo evento utilizando los datos proporcionados en el comando CreateEventCommand.
     * Crea una instancia de Event con los datos del comando, la guarda en el repositorio y devuelve el evento creado.
     * En caso de error, se registra el error y se lanza la excepción.
     * @param command El comando que contiene los datos para crear el evento.
     * @return El evento creado.
     */
    @Override
    public Event createEvento(CreateEventCommand command) {
        log.info("Creando evento con el comando: {}", command);
        if(command.nombre()==null || command.nombre().isBlank()){
            log.info("[EventService] El nombre del evento es obligatorio");
            throw new IllegalArgumentException("El nombre del evento es obligatorio");
        }
        if(command.organizador()==null || command.organizador().isBlank()){
            log.info("[EventService] El organizador del evento es obligatorio");
            throw new IllegalArgumentException("El organizador del evento es obligatorio");
        }
        if(command.descripcion()==null || command.descripcion().isBlank()){
            log.info("[EventService] El descripcion del evento es obligatorio");
            throw new IllegalArgumentException("El descripcion del evento es obligatorio");
        }
        if(command.fecha()==null){
            log.info("[EventService] La fecha del evento es obligatorio");
            throw new IllegalArgumentException("La fecha del evento es obligatorio");
        }
        if(command.ubicacion()==null || command.ubicacion().isBlank()){
            log.info("[EventService] El ubicacion del evento es obligatorio");
            throw new IllegalArgumentException("El ubicacion del evento es obligatorio");
        }
        try{ Event event = new Event(
                null,
                command.nombre(),
                command.organizador(),
                command.descripcion(),
                command.fecha(),
                command.ubicacion()
        );
            Event savedEvent = eventRepository.save(event);
            log.info("Evento creado: {}", event);
            return savedEvent;
        }
        catch (Exception e){
            log.error("[EventService] Error al crear el evento: {}", e.getMessage());
            throw e;
        }


    }

    /**
     * Este método actualiza un evento existente utilizando los datos proporcionados en el comando UpdateEventCommand.
     * Primero, busca el evento existente por su ID. Si no se encuentra, lanza una excepción.
     * Luego, crea un nuevo objeto Event con los datos actualizados y lo guarda en el repositorio.
     * Finalmente, devuelve el evento actualizado. En caso de error, se registra el error y se lanza la excepción.
     * @param command El comando que contiene los datos para actualizar el evento.
     * @return El evento actualizado.
     */
    @Override
    public Event updateEvento(UpdateEventCommand command) {
        log.info("Actualizando evento con el comando: {}",command);
        try{
            Event existingEvent = eventRepository.findById(command.eventId())
                    .orElseThrow(()-> new RuntimeException("Evento no encontrado con Id: {}" + command.eventId()));

            Event updatedEvent = new Event(
                    existingEvent.eventId(),
                    command.nombre(),
                    command.organizador(),
                    command.descripcion(),
                    command.fecha(),
                    command.ubicacion()
            );
            Event savedEvent = eventRepository.save(updatedEvent);
            log.info("[EventService] Evento actualizado: {}", savedEvent);
            return savedEvent;
        }
        catch (Exception e){
            log.error("[EventService] Error al actualizar el evento: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Este método busca eventos basados en los filtros proporcionados en el comando SearchEventCommand.
     * Utiliza el repositorio de eventos para realizar la búsqueda y devuelve una lista de eventos que coinciden con los criterios.
     * Si no se encuentran eventos, se registra una advertencia. En caso de error, se registra el error y se lanza la excepción.
     * @param command El comando que contiene los filtros de búsqueda para los eventos.
     * @return Una lista de eventos que coinciden con los filtros proporcionados.
     */
    @Override
    public List<Event> searchEvent(SearchEventCommand command) {
        log.info("[EventService] Buscando eventos con filtros: {} ",command);
        try{
            List<Event> events = eventRepository.findByFilter(
                    command.nombre(),
                    command.organizador(),
                    command.fecha(),
                    command.ubicacion()


            );
            if(events.isEmpty()){
                log.warn("[EventService] No se encontraron eventos con los filtros proporcionados: {}",command);
            }else{
                log.info("[EventService] Eventos encontrados: {}", events);
            }
            return events;
        }
        catch(Exception e){
            log.error("[EventService] Error al buscar eventos: {}", e.getMessage());
            throw e;
        }
    }
    public Optional<Event> findById(UUID eventId){
        if (eventId==null){
            throw new IllegalArgumentException("El ID del evento no puede ser nulo");
        }
        log.info("[EventService] Buscando evento por ID: {}", eventId);
        return eventRepository.findById(eventId);
    }
    public List<Event> findAll(){
        return eventRepository.findAll();
    }
}
