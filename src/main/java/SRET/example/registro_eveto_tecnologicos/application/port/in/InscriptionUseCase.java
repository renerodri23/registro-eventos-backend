package SRET.example.registro_eveto_tecnologicos.application.port.in;

import SRET.example.registro_eveto_tecnologicos.application.port.in.command.Registro.CancelInscriptionCommand;
import SRET.example.registro_eveto_tecnologicos.application.port.in.command.Registro.CreateInscriptionCommand;
import SRET.example.registro_eveto_tecnologicos.domain.model.Inscription;

public interface InscriptionUseCase {
    /**
     * Crea una nueva inscripción para un evento en el sistema.
     * @param command
     * @return
     */
    Inscription createInscription(CreateInscriptionCommand command);

    /**
     * Elimina una inscripción existente para un evento en el sistema.
     * @param command
     * @return
     */
    Inscription cancelInscription(CancelInscriptionCommand command);
}
