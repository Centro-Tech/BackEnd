package school.sptech.projetoMima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.projetoMima.entity.Estoque;

public interface EstoqueRepository extends JpaRepository <Estoque, Integer> {
}
