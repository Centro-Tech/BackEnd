package school.sptech.projetoMima.infrastructure.persistance.auxiliares;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.projetoMima.core.domain.item.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    boolean existsByNomeIgnoreCase(String nome);

}
