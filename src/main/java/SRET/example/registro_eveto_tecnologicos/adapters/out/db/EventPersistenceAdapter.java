package SRET.example.registro_eveto_tecnologicos.adapters.out.db;

import SRET.example.registro_eveto_tecnologicos.adapters.out.db.entity.EventEntity;
import SRET.example.registro_eveto_tecnologicos.adapters.out.db.mapper.EventPersistenceMapper;
import SRET.example.registro_eveto_tecnologicos.adapters.out.db.spring.SpringDataEventRepository;
import SRET.example.registro_eveto_tecnologicos.application.port.out.EventRepository;
import SRET.example.registro_eveto_tecnologicos.domain.model.Event;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static SRET.example.registro_eveto_tecnologicos.adapters.out.db.mapper.EventPersistenceMapper.toDomainModel;
import static SRET.example.registro_eveto_tecnologicos.adapters.out.db.mapper.EventPersistenceMapper.toEntity;

@Component
@Repository
@Transactional
@RequiredArgsConstructor
public class EventPersistenceAdapter implements EventRepository {
    private final SpringDataEventRepository eventRepository;

    @Override
    public Event save(Event event) {
        if(event.eventId() == null){
            EventEntity entity = toEntity(event);
            EventEntity savedEntity = eventRepository.save(entity);
            return toDomainModel(savedEntity);

        }
        else{
            EventEntity entity = eventRepository.findById(event.eventId()).orElseThrow(() -> new RuntimeException("Event not found with id: " + event.eventId()));{
                entity.setNombre(event.nombre());
                entity.setOrganizador(event.organizador());
                entity.setDescripcion(event.descripcion());
                entity.setFecha(event.fecha());
                entity.setUbicacion(event.ubicacion());
            }
            EventEntity savedEntity = eventRepository.save(entity);
            return toDomainModel(savedEntity);
        }
    }

    @Override
    public List<Event> findByFilter(String nombre, String organizador, LocalDateTime fecha,String ubicacion) {
        return eventRepository.search(nombre,organizador,fecha,ubicacion)
                .stream()
                .map(EventPersistenceMapper::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Event> findById(UUID eventId) {
        return eventRepository.findById(eventId).map(EventPersistenceMapper::toDomainModel);
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll()
                .stream()
                .map(EventPersistenceMapper::toDomainModel)
                .toList();
    }

    @Override
    public boolean existById(UUID eventId) {
        return eventRepository.existsById(eventId);

    }
}
