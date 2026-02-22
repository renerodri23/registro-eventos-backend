package SRET.example.registro_eveto_tecnologicos.adapters.out.db.mapper;

import SRET.example.registro_eveto_tecnologicos.adapters.out.db.entity.EventEntity;
import SRET.example.registro_eveto_tecnologicos.domain.model.Event;


public class EventPersistenceMapper {

    public static Event toDomainModel(EventEntity entity){
        return new Event(
                entity.getEventId(),
                entity.getNombre(),
                entity.getOrganizador(),
                entity.getDescripcion(),
                entity.getFecha(),
                entity.getUbicacion()
        );

    }

    public static EventEntity toEntity(Event event){
        EventEntity entity = new EventEntity();
        entity.setEventId(event.eventId());
        entity.setNombre(event.nombre());
        entity.setOrganizador((event.organizador()));
        entity.setDescripcion(event.descripcion());
        entity.setFecha(event.fecha());
        entity.setUbicacion(event.ubicacion());
        return entity;
    }
}
