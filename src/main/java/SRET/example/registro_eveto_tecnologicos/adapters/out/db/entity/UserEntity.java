package SRET.example.registro_eveto_tecnologicos.adapters.out.db.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserEntity {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id_users", columnDefinition = "BINARY(16)", nullable = false)
    private UUID userId;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellidos", nullable = false)
    private String apellido;

    @Column(name = "email", nullable = false)
    private String email;

    @EqualsAndHashCode.Include
    @Column(name = "password", nullable = false)
    private String passwordHash;

    @PrePersist
    void onCreate(){
        if (this.userId == null) this.userId = UUID.randomUUID();
    }
}
