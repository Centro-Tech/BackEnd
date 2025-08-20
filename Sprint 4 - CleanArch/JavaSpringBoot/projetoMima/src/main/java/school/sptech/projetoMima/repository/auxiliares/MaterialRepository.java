package school.sptech.projetoMima.repository.auxiliares;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.projetoMima.core.domain.item.Material;

public interface MaterialRepository extends JpaRepository<Material, Integer> {
    boolean existsByNomeIgnoreCase(String nome);
}
