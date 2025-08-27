package school.sptech.projetoMima.infrastructure.bd.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, Integer> {
    boolean existsByEmail(String email);
}
