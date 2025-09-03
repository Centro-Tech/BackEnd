package school.sptech.projetoMima.infrastructure.persistance.ClientePersistance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, Integer> {
    boolean existsByEmail(String email);
}
