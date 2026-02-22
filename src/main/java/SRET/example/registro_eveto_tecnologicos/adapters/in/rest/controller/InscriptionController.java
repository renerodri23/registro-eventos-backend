package SRET.example.registro_eveto_tecnologicos.adapters.in.rest.controller;

import SRET.example.registro_eveto_tecnologicos.adapters.in.rest.dto.Inscription.CancelInscriptionRequest;
import SRET.example.registro_eveto_tecnologicos.adapters.in.rest.dto.Inscription.CreateInscriptionRequest;
import SRET.example.registro_eveto_tecnologicos.adapters.in.rest.dto.Inscription.InscriptionResponse;
import SRET.example.registro_eveto_tecnologicos.adapters.in.rest.mapper.InscriptionRestMapper;
import SRET.example.registro_eveto_tecnologicos.application.port.in.command.Registro.CancelInscriptionCommand;
import SRET.example.registro_eveto_tecnologicos.application.port.in.command.Registro.CreateInscriptionCommand;
import SRET.example.registro_eveto_tecnologicos.application.service.InscriptionService;
import SRET.example.registro_eveto_tecnologicos.domain.model.Inscription;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/RegistroEventoTecnologicos/inscriptions")
public class InscriptionController {

    private final InscriptionService inscriptionService;

    @PostMapping("/create")
    public InscriptionResponse createInscription(@RequestBody CreateInscriptionRequest request){
        log.info("Received request to create inscription: {}", request);
        CreateInscriptionCommand command = new CreateInscriptionCommand(
                request.userId(),
                request.eventId()
        );
        Inscription inscription = inscriptionService.createInscription(command);
        log.info("Inscription created successfully: {}", inscription);
        return InscriptionRestMapper.toResponse(inscription);
    }

    @PostMapping("/cancel")
    public InscriptionResponse cancelInscription(@RequestBody CancelInscriptionRequest request){
        log.info("Received request to cancel inscription: {}", request);
        CancelInscriptionCommand command = new CancelInscriptionCommand(
                request.inscriptionId(),
                request.userId()
        );
        Inscription inscription = inscriptionService.cancelInscription(command);
        log.info("Inscription cancelled successfully: {}", inscription);
        return InscriptionRestMapper.toResponse(inscription);
    }

    @GetMapping("/find-user-inscriptions/{userId}")
    public List<InscriptionResponse> findUserInscriptions(@PathVariable UUID userId){
        log.info("Received request to find inscriptions for user: {}", userId);
        List<Inscription> inscriptions = inscriptionService.getInscriptionByUserId(userId);
        log.info("Found {} inscriptions for user {}", inscriptions.size(), userId);
        return inscriptions.stream()
                .map(InscriptionRestMapper::toResponse)
                .toList();
    }

    @GetMapping("/find-event-inscriptions/{eventId}")
    public List<InscriptionResponse> findEventInscriptions(@PathVariable UUID eventId) {
        log.info("Received request to find inscriptions for event: {}", eventId);
        List<Inscription> inscriptions = inscriptionService.getInscriptionByEventId(eventId);
        log.info("Found {} inscriptions for event {}", inscriptions.size(), eventId);
        return inscriptions.stream()
                .map(InscriptionRestMapper::toResponse)
                .toList();
    }
}

