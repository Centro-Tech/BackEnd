package school.sptech.projetoMima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.projetoMima.entity.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {
}
