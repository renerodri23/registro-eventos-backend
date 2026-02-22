package SRET.example.registro_eveto_tecnologicos.application.service;

import SRET.example.registro_eveto_tecnologicos.application.port.in.InscriptionUseCase;
import SRET.example.registro_eveto_tecnologicos.application.port.in.command.Registro.CancelInscriptionCommand;
import SRET.example.registro_eveto_tecnologicos.application.port.in.command.Registro.CreateInscriptionCommand;
import SRET.example.registro_eveto_tecnologicos.application.port.out.EventRepository;
import SRET.example.registro_eveto_tecnologicos.application.port.out.InscriptionRepository;
import SRET.example.registro_eveto_tecnologicos.application.port.out.UserRepository;
import SRET.example.registro_eveto_tecnologicos.domain.model.Inscription;
import SRET.example.registro_eveto_tecnologicos.domain.model.enums.InscriptionStatus;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class InscriptionService implements InscriptionUseCase {

    final private InscriptionRepository inscriptionRepository;
    final private EventRepository eventRepository;
    final private UserRepository userRepository;

    @Override
    public Inscription createInscription(CreateInscriptionCommand command) {
        log.info("[InscriptionService] createInscription: {}", command);
        try{
            if (command.userId() == null){
                log.warn("[InscriptionService] User ID is null in CreateInscriptionCommand: {}", command);
                throw new RuntimeException("User ID cannot be null");
            }
            if (command.eventId() == null){
                log.warn("[InscriptionService] Event ID is null in CreateInscriptionCommand: {}", command);
                throw new RuntimeException("Event ID cannot be null");
            }
            if (!eventRepository.existById(command.eventId())){
                log.warn("[InscriptionService] Event not found with ID: {}", command.eventId());
                throw new RuntimeException("Event not found with ID: " + command.eventId());
            }
            if(!userRepository.existById(command.userId())){
                log.warn("[InscriptionService] User not found with ID: {}", command.userId());
                throw new RuntimeException("User not found with ID: " + command.userId());
            }

            boolean alreadyInscribed = inscriptionRepository.existsByUserIdAndEventId(command.userId(), command.eventId(),InscriptionStatus.ACTIVE);

            if(alreadyInscribed){
                log.warn("[InscriptionService] User with ID: {} is already inscribed to event with ID: {}", command.userId(), command.eventId());
                throw new RuntimeException("User is already inscribed to this event");
            }
            Inscription inscription = new Inscription(
                    null,
                    command.userId(),
                    command.eventId(),
                    InscriptionStatus.ACTIVE,
                    LocalDateTime.now()

            );
            Inscription savedInscription = inscriptionRepository.save(inscription);
            log.info("[InscriptionService] Inscription created successfully: {}", savedInscription);

            return savedInscription;
        } catch (Exception e){
            log.error("[InscriptionService] Error creating inscription: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create inscription", e);
        }
    }


    @Override
    public Inscription cancelInscription(CancelInscriptionCommand command) {
        log.info("[InscriptionService] cancelInscription: {}", command);
        try {
            Inscription inscription = inscriptionRepository.findById(command.inscriptionId())
                    .orElseThrow(() -> new RuntimeException("Inscription not found with id: " + command.inscriptionId()
                    ));
            if (inscription.status() == InscriptionStatus.CANCELLED) {
                log.warn("[InscriptionService] Inscription already canceled: {}", inscription);
                throw new RuntimeException("Inscription is already canceled");
            }
            if(!inscription.usuarioId().equals(command.userId())){
                log.warn("[InscriptionService] User with ID: {} is not the owner of the inscription with ID: {}", command.userId(), command.inscriptionId());
                throw new RuntimeException("User is not the owner of the inscription");
            }

            Inscription cancelledInscription = new Inscription(
                    inscription.inscriptionId(),
                    inscription.usuarioId(),
                    inscription.eventId(),
                    InscriptionStatus.CANCELLED,
                    inscription.fechaRegistro()

            );
            return inscriptionRepository.save(cancelledInscription);

        } catch (Exception e) {
            log.error("[InscriptionService] Error canceling inscription: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to cancel inscription", e);
        }
    }
    public List<Inscription> getInscriptionByUserId(UUID userId) {
        log.info("[InscriptionService] getInscriptionByUserId: {}", userId);
        if (userId == null) {
            log.warn("[InscriptionService] User ID is null in getInscriptionByUserId: {}", userId);
            throw new RuntimeException("User ID cannot be null");
        } else{
            log.info("[InscriptionService] User ID is valid in getInscriptionByUserId: {}", userId);
        }
        if (!userRepository.existById(userId)) {
            log.warn("[InscriptionService] User not found with UserID: {}", userId);
            throw new RuntimeException("User not found with ID: " + userId);
        } else {
            log.info("[InscriptionService] User found with UserID: {}", userId);
        }
        List<Inscription> inscriptions = inscriptionRepository.findAllByUserId(userId);

        if (inscriptions.isEmpty()) {
            log.warn("[InscriptionService] No inscriptions found for user with ID: {}", userId);
            throw new RuntimeException("User ID cannot be null");
        } else {
            log.info("[InscriptionService] Found {} inscriptions for user with ID: {}", inscriptions.size(), userId);
        }
        return inscriptions;
    }

    public List<Inscription> getInscriptionByEventId(UUID eventId){
        log.info("[InscriptionService] getInscriptionByEventId: {}", eventId);
        if(eventId==null){
            log.warn("[InscriptionService] Event ID is null in getInscriptionByEventId: {}", eventId);
            throw new RuntimeException("Event ID cannot be null");
        } else {
            log.info("[InscriptionService] Event ID is valid in getInscriptionByEventId: {}", eventId);
        }
        if (!eventRepository.existById(eventId)){
            log.warn("[InscriptionService] Event not found with EventID: {}", eventId);
            throw new RuntimeException("Event not found with ID: " + eventId);
        } else {
            log.info("[InscriptionService] Event found with EventID: {}", eventId);
        }
        List<Inscription> inscriptions = inscriptionRepository.findAllByEventId(eventId);
        if (inscriptions.isEmpty()){
            log.warn("[InscriptionService] No inscriptions found for event with ID: {}", eventId);
            throw new RuntimeException("No inscriptions found for event with ID: " + eventId);
        } else {
            log.info("[InscriptionService] Found inscriptions for event with ID: {}, count: {}", eventId, inscriptions.size());
        }
        return inscriptions;
    }
}
