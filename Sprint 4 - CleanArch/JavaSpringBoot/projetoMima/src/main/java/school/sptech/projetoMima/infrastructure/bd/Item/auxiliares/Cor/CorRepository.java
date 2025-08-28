package school.sptech.projetoMima.infrastructure.bd.Item.auxiliares.Cor;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.projetoMima.core.domain.item.Cor;

public interface CorRepository extends JpaRepository<Cor, Integer> {
    boolean existsByNomeIgnoreCase(String nome);
}
