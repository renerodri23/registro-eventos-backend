package SRET.example.registro_eveto_tecnologicos.adapters.out.db;

import SRET.example.registro_eveto_tecnologicos.adapters.out.db.entity.InscriptionEntity;
import SRET.example.registro_eveto_tecnologicos.adapters.out.db.mapper.InscriptionPersistencMapper;
import SRET.example.registro_eveto_tecnologicos.adapters.out.db.spring.SpringDataInscriptionRepository;
import SRET.example.registro_eveto_tecnologicos.application.port.out.InscriptionRepository;
import SRET.example.registro_eveto_tecnologicos.domain.model.Inscription;
import SRET.example.registro_eveto_tecnologicos.domain.model.enums.InscriptionStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static SRET.example.registro_eveto_tecnologicos.adapters.out.db.mapper.InscriptionPersistencMapper.toDomain;
import static SRET.example.registro_eveto_tecnologicos.adapters.out.db.mapper.InscriptionPersistencMapper.toEntity;

@Transactional
@Repository
@RequiredArgsConstructor
public class InscriptionPersistenceAdapter implements InscriptionRepository {
    private final SpringDataInscriptionRepository springDataInscriptionRepository;

    @Override
    public Inscription save(Inscription inscription) {
        InscriptionEntity entity = toEntity(inscription);

        InscriptionEntity savedEntity = springDataInscriptionRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Inscription> findById(UUID id) {
        return springDataInscriptionRepository.findById(id)
                .map(InscriptionPersistencMapper::toDomain);
    }

    @Override
    public List<Inscription> findAllByUserId(UUID userId) {
        return springDataInscriptionRepository.findByUserId(userId)
                .stream()
                .map(InscriptionPersistencMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Inscription> findAllByEventId(UUID eventId) {
        return springDataInscriptionRepository.findByEventId(eventId)
                .stream()
                .map(InscriptionPersistencMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByUserIdAndEventId(UUID userId, UUID eventId, InscriptionStatus status) {
        return springDataInscriptionRepository.existsByUserIdAndEventId(userId,eventId,status);
    }
}
