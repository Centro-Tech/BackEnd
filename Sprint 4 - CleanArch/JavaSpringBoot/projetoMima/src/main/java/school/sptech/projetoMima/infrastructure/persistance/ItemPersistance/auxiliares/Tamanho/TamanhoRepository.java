package school.sptech.projetoMima.infrastructure.persistance.ItemPersistance.auxiliares.Tamanho;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.projetoMima.core.domain.item.Tamanho;

import java.util.Optional;

public interface TamanhoRepository extends JpaRepository<Tamanho, Integer> {
    boolean existsByNomeIgnoreCase(String nome);
    Optional<Tamanho> findByNome(String nome);
}
