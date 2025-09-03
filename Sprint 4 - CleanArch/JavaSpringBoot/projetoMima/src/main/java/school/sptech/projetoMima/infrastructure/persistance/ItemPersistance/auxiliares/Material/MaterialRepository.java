package school.sptech.projetoMima.infrastructure.persistance.ItemPersistance.auxiliares.Material;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.projetoMima.core.domain.item.Material;

public interface MaterialRepository extends JpaRepository<Material, Integer> {
    boolean existsByNomeIgnoreCase(String nome);
}
