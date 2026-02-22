package SRET.example.registro_eveto_tecnologicos.adapters.out.db;

import SRET.example.registro_eveto_tecnologicos.adapters.out.db.entity.UserEntity;
import SRET.example.registro_eveto_tecnologicos.adapters.out.db.mapper.UserPersistenceMapper;
import SRET.example.registro_eveto_tecnologicos.adapters.out.db.spring.SpringDataUserRepository;
import SRET.example.registro_eveto_tecnologicos.application.port.out.UserRepository;
import SRET.example.registro_eveto_tecnologicos.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Repository
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepository {

    private final SpringDataUserRepository springDataUserRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User save(User user) {
        UserEntity entity = UserPersistenceMapper.toEntity(user);
        UserEntity saved = springDataUserRepository.save(entity);
        return UserPersistenceMapper.toDomainModel(saved);
    }

    @Override
    public Optional<User> findById(UUID userId) {
        return springDataUserRepository.findById(userId)
                .map(UserPersistenceMapper::toDomainModel);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return springDataUserRepository.findByEmail(email)
                .map(UserPersistenceMapper::toDomainModel);
    }

    @Override
    public List<User> findAll() {
        return springDataUserRepository.findAll()
                .stream()
                .map(UserPersistenceMapper::toDomainModel)
                .toList();
    }

    @Override
    public boolean existsByEmail(String email) {
        return springDataUserRepository.existsByEmail(email.toLowerCase());
    }

    @Override
    public boolean existById(UUID userId) {
        return springDataUserRepository.existsById(userId);
    }
}
