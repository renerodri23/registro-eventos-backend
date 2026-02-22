package SRET.example.registro_eveto_tecnologicos.application.port.out;

import SRET.example.registro_eveto_tecnologicos.domain.model.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventRepository {
    Event save(Event event);
    List<Event> findByFilter(String nombre,String organizador,LocalDateTime fecha, String ubicacion);
    Optional<Event> findById(UUID eventId);
    List<Event> findAll();
    boolean existById(UUID eventId);


}
