package SRET.example.registro_eveto_tecnologicos.adapters.out.db.spring;

import SRET.example.registro_eveto_tecnologicos.adapters.out.db.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findById(UUID userid);

    boolean existsByEmail(String email);
}
