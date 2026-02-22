package SRET.example.registro_eveto_tecnologicos.application.port.in;

import SRET.example.registro_eveto_tecnologicos.application.port.in.command.Evento.CreateEventCommand;
import SRET.example.registro_eveto_tecnologicos.application.port.in.command.Evento.SearchEventCommand;
import SRET.example.registro_eveto_tecnologicos.application.port.in.command.Evento.UpdateEventCommand;
import SRET.example.registro_eveto_tecnologicos.domain.model.Event;

import java.util.List;

public interface EventUseCase {
    /**
     * Crea un nuevo evento en el sistema.
     * @param command
     * @return
     */
    Event createEvento(CreateEventCommand command);

    /**
     * Actualiza la información de un evento existente en el sistema.
     * @param command
     * @return
     */
    Event updateEvento(UpdateEventCommand command);

    /**
     * Busca un evento por su ID en el sistema.
     * @param command
     * @return
     */
    List<Event> searchEvent(SearchEventCommand command);

}
