package school.sptech.projetoMima.infrastructure.bd.Item.auxiliares.Tamanho;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.projetoMima.core.domain.item.Tamanho;

public interface TamanhoRepository extends JpaRepository<Tamanho, Integer> {
    boolean existsByNomeIgnoreCase(String nome);
}
