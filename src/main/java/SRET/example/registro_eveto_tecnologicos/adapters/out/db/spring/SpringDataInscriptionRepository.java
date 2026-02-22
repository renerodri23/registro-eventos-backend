package SRET.example.registro_eveto_tecnologicos.adapters.out.db.spring;

import SRET.example.registro_eveto_tecnologicos.adapters.out.db.entity.InscriptionEntity;
import SRET.example.registro_eveto_tecnologicos.domain.model.enums.InscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataInscriptionRepository extends JpaRepository<InscriptionEntity, UUID> {

    List<InscriptionEntity> findByUserId(UUID userId);

    List<InscriptionEntity> findByEventId(UUID eventId);

    boolean existsByUserIdAndEventId(UUID userId, UUID eventId, InscriptionStatus status);
}
