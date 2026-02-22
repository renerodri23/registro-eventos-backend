package SRET.example.registro_eveto_tecnologicos.adapters.in.rest.mapper;

import SRET.example.registro_eveto_tecnologicos.adapters.in.rest.dto.Inscription.CreateInscriptionRequest;
import SRET.example.registro_eveto_tecnologicos.adapters.in.rest.dto.Inscription.InscriptionResponse;
import SRET.example.registro_eveto_tecnologicos.application.port.in.command.Registro.CancelInscriptionCommand;
import SRET.example.registro_eveto_tecnologicos.application.port.in.command.Registro.CreateInscriptionCommand;
import SRET.example.registro_eveto_tecnologicos.domain.model.Inscription;

public class InscriptionRestMapper {

    public static CreateInscriptionCommand toInscription(CreateInscriptionRequest request){
        return new CreateInscriptionCommand(
                request.userId(),
                request.eventId()
        );
    }
    public static CancelInscriptionCommand toCancelInscription(CancelInscriptionCommand request){
        return new CancelInscriptionCommand(
                request.inscriptionId(),
                request.userId()
        );
    }
    public static InscriptionResponse toResponse(Inscription inscription){
        return new InscriptionResponse(
                inscription.inscriptionId(),
                inscription.usuarioId(),
                inscription.eventId(),
                inscription.fechaRegistro(),
                inscription.status()
        );
    }
}
