package SRET.example.registro_eveto_tecnologicos.adapters.out.db.mapper;

import SRET.example.registro_eveto_tecnologicos.adapters.out.db.entity.InscriptionEntity;
import SRET.example.registro_eveto_tecnologicos.domain.model.Inscription;

public class InscriptionPersistencMapper {
    public static Inscription toDomain(InscriptionEntity entity){
        return new Inscription(
                entity.getInscriptionId(),
                entity.getUserId(),
                entity.getEventId(),
                entity.getStatus(),
                entity.getFechaInscripcion()
        );
    }

    public static InscriptionEntity toEntity(Inscription inscription){
        InscriptionEntity entity = new InscriptionEntity();
        entity.setInscriptionId(inscription.inscriptionId());
        entity.setUserId(inscription.usuarioId());
        entity.setEventId(inscription.eventId());
        entity.setStatus(inscription.status());
        entity.setFechaInscripcion(inscription.fechaRegistro());
        return entity;
    }
}
