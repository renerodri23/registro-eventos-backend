package SRET.example.registro_eveto_tecnologicos.application.port.out;

import SRET.example.registro_eveto_tecnologicos.domain.model.Inscription;
import SRET.example.registro_eveto_tecnologicos.domain.model.enums.InscriptionStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InscriptionRepository {
    Inscription save(Inscription inscription);
    Optional <Inscription> findById(UUID id);
    List<Inscription> findAllByUserId(UUID userId);
    List<Inscription> findAllByEventId(UUID eventId);
    boolean existsByUserIdAndEventId(UUID userId, UUID eventId, InscriptionStatus status);

}
