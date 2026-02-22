package SRET.example.registro_eveto_tecnologicos.adapters.out.db.entity;

import SRET.example.registro_eveto_tecnologicos.domain.model.enums.InscriptionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "registro")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class InscriptionEntity {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id_registro",columnDefinition = "BINARY(16)", nullable = false, unique = true)
    private UUID inscriptionId;


    @EqualsAndHashCode.Include
    @Column(name = "id_users",columnDefinition = "BINARY(16)", nullable = false, unique = true)
    private UUID userId;


    @EqualsAndHashCode.Include
    @Column(name="id_events",columnDefinition = "BINARY(16)", nullable = false, unique = true)
    private UUID eventId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private InscriptionStatus status;

    @Column(name = "fecha_registro", nullable = false, length = 20)
    private LocalDateTime fechaInscripcion;

    @PrePersist
    void onCreate() {
        if (inscriptionId == null) {
            inscriptionId = UUID.randomUUID();
        }
    }
}
