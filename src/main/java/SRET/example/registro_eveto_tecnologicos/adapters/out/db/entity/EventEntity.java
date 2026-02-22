package SRET.example.registro_eveto_tecnologicos.adapters.out.db.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table (name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EventEntity {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id_events", columnDefinition = "BINARY(16)")
    private UUID eventId;

    @Column(name="nombre", nullable = false)
    private String nombre;

    @Column(name="organizador", nullable = false)
    private String organizador;

    @Column(name="descripcion", length = 2000, nullable = false)
    private String descripcion;

    @Column(name="fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "ubicacion", nullable = false)
    private String ubicacion;

    @PrePersist
    void onCreate(){
        if(this.eventId ==null)this.eventId =UUID.randomUUID();
    }
}
